package com.hgnu.study.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hgnu.study.exception.TipException;
import com.hgnu.study.mybatisplus.entity.HelpRelation;
import com.hgnu.study.mybatisplus.mapper.HelpRelationMapper;
import com.hgnu.study.mybatisplus.service.HelpRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.Future;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 13:44
 */

@Service
public class HelpRelationServiceImpl extends ServiceImpl<HelpRelationMapper, HelpRelation> implements HelpRelationService {

    @Autowired
    HelpRelationMapper helpRelationMapper;

    /**
     * 异步调用返回future
     * 对于返回值是future(非void),不会被AsyncUncaughtExceptionHandler处理,需要我们在方法中捕获异常并处理
     * 或者再调用方法在调用future.get()时捕获异常进行处理
     * @param current
     * @param pagesize
     * @return
     */
    @Override
    @Transactional(value = "dataSourceTransactionManager2",rollbackFor = TipException.class)
    @Async("taskExcutor")
    public Future<List<HelpRelation>> selectByCondition(Integer current, Integer pagesize){
        boolean lookup = true;
        List<HelpRelation> allHistoryList = new ArrayList<>();
        List<HelpRelation> historyList;
        Future<List<HelpRelation>> listFuture = new AsyncResult<>(allHistoryList);
        Map map = new HashMap(4);
        while (lookup){
            Integer index = (current-1) * pagesize;
            map.put("current",index);
            map.put("pagesize",pagesize);
            historyList = baseMapper.queryPages();
            if (historyList.size() < pagesize) {
                lookup = false;
            }
            current++;
            allHistoryList.addAll(historyList);
        }
        return listFuture;
    }
}
