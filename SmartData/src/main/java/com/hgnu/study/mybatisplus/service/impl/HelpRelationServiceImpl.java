package com.hgnu.study.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.HelpRelation;
import com.hgnu.study.mybatisplus.mapper.HelpRelationMapper;
import com.hgnu.study.mybatisplus.service.HelpRelationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 13:44
 */

@Service
public class HelpRelationServiceImpl extends ServiceImpl<HelpRelationMapper, HelpRelation> implements HelpRelationService {

    @Override
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    public List<HelpRelation> selectByCondition(Integer current,Integer size){
        IPage<HelpRelation> page = new Page(current,size);
        IPage selectPage = baseMapper.selectPage(page, null);
        List<HelpRelation> records = selectPage.getRecords();
        return records  ;
    }
}
