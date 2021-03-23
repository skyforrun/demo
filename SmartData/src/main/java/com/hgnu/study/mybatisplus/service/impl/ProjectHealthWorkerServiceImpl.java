package com.hgnu.study.mybatisplus.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.*;
import com.hgnu.study.mybatisplus.mapper.ProjectHealthWorkerMapper;
import com.hgnu.study.mybatisplus.service.IProjectHealthWorkerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/15 14:11
 */

@Service
@Slf4j
public class ProjectHealthWorkerServiceImpl extends ServiceImpl<ProjectHealthWorkerMapper, ProjectHealthWorker> implements IProjectHealthWorkerService {

    @Autowired
    ProjectHealthWorkerMapper projectHealthWorkerMapper;

    @Override
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public void projectBindHealthWorker(Long projectId, List<ProjectHealthWorkerDto> projectHealthWorkerDtos) {
        Long accountId = IdUtil.createSnowflake(1,1).nextId();

        //查询该项目的绑定关系
        QueryWrapper<ProjectHealthWorker> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", EnableEnum.ENABLE.getValue());
        queryWrapper.eq("project_id", projectId);
        List<ProjectHealthWorker> projectHealthWorkers = baseMapper.selectList(queryWrapper);
        Map<Long, List<ProjectHealthWorker>> idCollect = new HashMap<>();
        if(CollectionUtil.isNotEmpty(projectHealthWorkers)){
            idCollect = projectHealthWorkers.stream().collect(Collectors.groupingBy(e -> e.getId()));
        }

        for (ProjectHealthWorkerDto dto : projectHealthWorkerDtos) {
            dto.setProjectId(projectId);
            if(dto.getEnable() == null){
                dto.setEnable(EnableEnum.ENABLE.getValue());
            }
            // 验重
            if(!checkBindExist(dto)){
                continue;
            }

            Long id = dto.getId();
            if(id == null){
                addBind(dto, accountId);
                /**
                 * log------------
                 */
            }else{
                if(idCollect.containsKey(id)){
                    idCollect.remove(id);
                }
                updateBind(dto, accountId);
                /**
                 * log------------
                 */
            }
        }
        // 删除未传过来的id记录
        if(CollectionUtil.isNotEmpty(idCollect)){
            for (List<ProjectHealthWorker> deleteDatas : idCollect.values()) {
                ProjectHealthWorker deleteData = deleteDatas.get(0);
                deleteBind(deleteData.getId(), accountId);
                /**
                 * log------------
                 */
            }
        }
    }

    @Override
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public void projectUnsetHealthWorker(List<ProjectHealthWorkerDto> projectHealthWorkerDtos) {
        for (int i = 0; i < projectHealthWorkerDtos.size(); i++) {
            baseMapper.deleteById(projectHealthWorkerDtos.get(i).getId());
        }
    }

    @Override
    public List queryBind(List<ProjectHealthWorkerDto> projectHealthWorkerDtos) {
        List list = projectHealthWorkerMapper.queryBind(projectHealthWorkerDtos);
        return list;
    }

    @Override
    public List<ProjectHealthWorker> queryAll() {
        List<ProjectHealthWorker> projectHealthWorkers = baseMapper.selectList(null);
        return projectHealthWorkers;
    }

    /**
     * 逻辑删除绑定关系
     * @param id 项目药房id
     * @param accountId 用户id
     * @return
     */
    private ProjectHealthWorker deleteBind(Long id, Long accountId) {
        ProjectHealthWorker projectHealthWorker = baseMapper.selectById(id);
        projectHealthWorker.setEnable(EnableEnum.DELETED.getValue());
        projectHealthWorker.setUpdateTime(LocalDateTime.now());
        projectHealthWorker.setUpdateAccount(accountId);
        baseMapper.updateById(projectHealthWorker);
        return projectHealthWorker;
    }

    private ProjectHealthWorker updateBind(ProjectHealthWorkerDto dto, Long accountId) {
        ProjectHealthWorker projectHealthWorker = baseMapper.selectById(dto.getId());
        projectHealthWorker.setProjectId(dto.getProjectId());
        projectHealthWorker.setHealthworkerId(dto.getHealthworkerId());
        projectHealthWorker.setUpdateTime(LocalDateTime.now());
        projectHealthWorker.setUpdateAccount(accountId);
        baseMapper.updateById(projectHealthWorker);
        return projectHealthWorker;
    }

    private ProjectHealthWorker addBind(ProjectHealthWorkerDto dto, Long accountId) {
        ProjectHealthWorker projectHealthWorker = new ProjectHealthWorker();
        projectHealthWorker.setId(IdUtil.createSnowflake(2,1).nextId());
        projectHealthWorker.setProjectId(dto.getProjectId());
        projectHealthWorker.setHealthworkerId(dto.getHealthworkerId());
        projectHealthWorker.setEnable(EnableEnum.ENABLE.getValue());
        LocalDateTime now = LocalDateTime.now();
        projectHealthWorker.setCreateTime(now);
        projectHealthWorker.setUpdateTime(now);
        projectHealthWorker.setCreateAccount(accountId);
        projectHealthWorker.setUpdateAccount(accountId);
        baseMapper.insert(projectHealthWorker);
        return projectHealthWorker;
    }

    private boolean checkBindExist(ProjectHealthWorkerDto dto) {
        if(dto.getEnable() == EnableEnum.ENABLE.getValue()){
            QueryWrapper<ProjectHealthWorker> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("enable", EnableEnum.ENABLE.getValue());
            queryWrapper.eq("project_id", dto.getProjectId());
            queryWrapper.eq("healthworker_id", dto.getHealthworkerId());
            Integer count = baseMapper.selectCount(queryWrapper);
            if(count > 0){
                log.warn("项目:{}已经绑定了医护工作者:{},请不要重复绑定",dto.getProjectId(),dto.getHealthworkerId());
                throw new TipException("项目重复绑定了 医护工作者");
                //return false;
            }
        }
        return true;
    }
}
