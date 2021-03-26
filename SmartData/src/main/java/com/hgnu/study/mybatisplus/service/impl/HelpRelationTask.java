package com.hgnu.study.mybatisplus.service.impl;

import com.hgnu.study.mybatisplus.entity.HelpRelation;
import com.hgnu.study.mybatisplus.service.HelpRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 15:16
 */

@Component
public class HelpRelationTask implements Callable<List<HelpRelation>> {
    
    @Autowired
    HelpRelationService helpRelationService;
    
    @Override
    public List<HelpRelation> call() {
        Integer offset = 1;
        Integer pagesize = 300;
        boolean lookup = true;
        List<HelpRelation> allHistoryList = new ArrayList<>();
        List<HelpRelation> helpRelations;
        while (lookup) {

            helpRelations = helpRelationService.selectByCondition(offset, pagesize);
            offset++;
            offset = (offset - 1) * pagesize;
            allHistoryList.addAll(helpRelations);
            if (helpRelations.size() < pagesize) {
                lookup = false;
            }
        }
        return allHistoryList;
    }
}
