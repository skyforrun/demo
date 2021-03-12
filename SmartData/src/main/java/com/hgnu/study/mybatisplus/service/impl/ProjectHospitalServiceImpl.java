package com.hgnu.study.mybatisplus.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.EnableEnum;
import com.hgnu.study.mybatisplus.entity.ProjectHospital;
import com.hgnu.study.mybatisplus.entity.ProjectHospitalDto;
import com.hgnu.study.mybatisplus.mapper.ProjectHospitalMapper;
import com.hgnu.study.mybatisplus.service.IProjectHospitalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @Author zj
 * @Description
 * @Date 2021/3/12 16:05
 */

@Service
public class ProjectHospitalServiceImpl extends ServiceImpl<ProjectHospitalMapper,ProjectHospital> implements IProjectHospitalService {

    @Override
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public void projectBindHospital(Long projectId, List<ProjectHospitalDto> projectHospitals) {
        LocalDateTime now = LocalDateTime.now();
        Long accountId = IdUtil.createSnowflake(1,1).nextId();

        // 查出该基金会的绑定关系
        QueryWrapper<ProjectHospital> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("enable", EnableEnum.ENABLE.getValue());
        queryWrapper.eq("project_id", projectId);
        List<ProjectHospital> databasefoundationCommissioners = baseMapper.selectList(queryWrapper);
        Map<Long, List<ProjectHospital>> idCollect = new HashMap<>();
        if(CollectionUtil.isNotEmpty(databasefoundationCommissioners)){
            idCollect = databasefoundationCommissioners.stream().collect(Collectors.groupingBy(e -> e.getId()));
        }

        for (ProjectHospitalDto dto : projectHospitals) {
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
            for (List<ProjectHospital> deleteDatas : idCollect.values()) {
                ProjectHospital deleteData = deleteDatas.get(0);
                deleteBind(deleteData.getId(), accountId);
                /**
                 * log------------
                 */
            }
        }
    }

    private ProjectHospital deleteBind(Long id, Long accountId) {
        ProjectHospital foundationCommissioner = baseMapper.selectById(id);
        foundationCommissioner.setEnable(EnableEnum.DELETED.getValue());
        foundationCommissioner.setUpdateTime(LocalDateTime.now());
        foundationCommissioner.setUpdateAccount(accountId);
        baseMapper.updateById(foundationCommissioner);
        return foundationCommissioner;
    }

    private ProjectHospital updateBind(ProjectHospitalDto dto, Long accountId) {
        ProjectHospital projectHospital = baseMapper.selectById(dto.getId());
        projectHospital.setProjectId(dto.getProjectId());
        projectHospital.setHospitalId(dto.getHospitalId());
        projectHospital.setUpdateTime(LocalDateTime.now());
        projectHospital.setUpdateAccount(accountId);
        baseMapper.updateById(projectHospital);
        return projectHospital;
    }

    private ProjectHospital addBind(ProjectHospitalDto dto, Long accountId) {
        ProjectHospital projectHospital = new ProjectHospital();
        projectHospital.setId(IdUtil.createSnowflake(2,1).nextId());
        projectHospital.setProjectId(dto.getProjectId());
        projectHospital.setHospitalId(dto.getHospitalId());
        projectHospital.setEnable(EnableEnum.ENABLE.getValue());
        LocalDateTime now = LocalDateTime.now();
        projectHospital.setCreateTime(now);
        projectHospital.setUpdateTime(now);
        projectHospital.setCreateAccount(accountId);
        projectHospital.setUpdateAccount(accountId);
        baseMapper.insert(projectHospital);
        return projectHospital;
    }

    private boolean checkBindExist(ProjectHospitalDto dto) {
        Long id = dto.getId();
        if(id == null && dto.getEnable() == EnableEnum.ENABLE.getValue()){
            QueryWrapper<ProjectHospital> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("enable", EnableEnum.ENABLE.getValue());
            queryWrapper.eq("project_id", dto.getProjectId());
            queryWrapper.eq("hospital_id", dto.getHospitalId());
            if(id != null){
                queryWrapper.ne("id", id);
            }
            Integer count = baseMapper.selectCount(queryWrapper);
            if(count > 0){
                return false;
            }
        }
        return true;
    }
}
