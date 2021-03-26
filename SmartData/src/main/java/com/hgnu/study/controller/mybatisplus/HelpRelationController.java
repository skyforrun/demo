package com.hgnu.study.controller.mybatisplus;

import com.hgnu.study.core.Result;
import com.hgnu.study.mybatisplus.entity.HelpRelation;
import com.hgnu.study.mybatisplus.service.impl.HelpRelationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 15:40
 */
@RequestMapping("/helpRelation")
@RestController
public class HelpRelationController {
    
    @Autowired
    HelpRelationTask helpRelationTask;
    
    @PostMapping("/deal")
    public Result dsadsqw() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(4);
        Future<List<HelpRelation>> submit = threadPool.submit(helpRelationTask);
        return Result.success(submit.get());
    }
}
