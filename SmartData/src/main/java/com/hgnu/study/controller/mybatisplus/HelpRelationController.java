package com.hgnu.study.controller.mybatisplus;

import com.hgnu.study.core.Result;
import com.hgnu.study.mybatisplus.entity.HelpRelation;
import com.hgnu.study.mybatisplus.service.HelpRelationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


/**
 * @Author zj
 * @Description
 * @Date 2021/3/26 15:40
 */
@RequestMapping("/helpRelation")
@RestController
@Api(tags = "线程池异步处理任务")
public class HelpRelationController {
    
    @Autowired
    HelpRelationService helpRelationService;
    
    @PostMapping("/deal")
    @ApiOperation("线程池分批查询全表数据")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "page",value = "那一页开始",defaultValue = "1",required = true),
        @ApiImplicitParam(name = "pageSize",value = "每页的数量",defaultValue = "300",required = true)
    })
    public Result asyncWithTask(Integer page,Integer pageSize) throws ExecutionException, InterruptedException {
        Future<List<HelpRelation>> listFuture = helpRelationService.selectByCondition(page, pageSize);
        return Result.success(listFuture.get());
    }
}
