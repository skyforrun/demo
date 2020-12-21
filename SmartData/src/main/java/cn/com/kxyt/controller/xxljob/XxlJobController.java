package cn.com.kxyt.controller.xxljob;


import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/xxl-job")
@Api(value = "xxl-job定时任务接口",tags = "用api对定时任务进行crud，避免后台操作")
public class XxlJobController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String baseUri = "http://localhost:8080/xxl-job-admin";
    /**
     * 任务界面
     */
    private final static String JOB_INFO_URI = "/jobinfo";
    /**
     * 执行器界面
     */
    private final static String JOB_GROUP_URI = "/jobgroup";

    /**
     * 任务组列表，xxl-job叫做触发器列表
     */
    @GetMapping("/group")
    @ApiOperation(value="定时任务执行器数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="哪一页开始，默认第一页",defaultValue = "0"),
            @ApiImplicitParam(name="size",value="展示的数据量，默认10",defaultValue = "10"),
            @ApiImplicitParam(name="appname",value="执行器名字",defaultValue = ""),
            @ApiImplicitParam(name="title",value="执行器描述",defaultValue = "")
    })
    public String xxlJobGroup(Integer page,Integer size,String appname,String title) {
        //请求参数拼接
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("start",page);
        jobInfo.put("length",size);
        //执行器名字
        jobInfo.put("appname",appname);
        //执行器描述
        jobInfo.put("title",title);
        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_GROUP_URI + "/pageList").form(jobInfo).execute();
        System.out.println(baseUri + JOB_GROUP_URI + "/pagelist");
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 分页任务列表
     *
     * @param page 当前页，第一页 -> 0
     * @param size 每页条数，默认10
     * @return 分页任务列表
     */
    @GetMapping("/list")
    @ApiOperation(value="定时任务展示列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value="哪一页开始，默认第一页",defaultValue = "0"),
            @ApiImplicitParam(name="size",value="展示的数据量，默认10",defaultValue = "10"),
            @ApiImplicitParam(name="jobGroup",value="执行器id",required = true)
    })
    public String xxlJobList(Integer page, Integer size,Integer jobGroup) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("start",page);
        jobInfo.put("length",size);
        jobInfo.put("jobGroup",jobGroup);
        jobInfo.put("triggerStatus", -1);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/pageList").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 测试手动保存任务
     */
    @GetMapping("/add")
    @ApiOperation(value="添加定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="jobGroup",value="任务执行器id",required = true),
            @ApiImplicitParam(name="jobCron",value="定时任务cron表达式",required = true),
            @ApiImplicitParam(name="jobDesc",value="定时任务描述",required = true),
            @ApiImplicitParam(name="author",value="定时任务负责人",required = true),
            @ApiImplicitParam(name="scheduleType",value="定时任务调度类型",required = true,defaultValue = "CRON"),
            @ApiImplicitParam(name="alarmEmail",value="定时报警邮箱",defaultValue = "1262345715@qq.com"),
            @ApiImplicitParam(name="executorHandler",value="定时任务bean名称",required = true),
            @ApiImplicitParam(name="executorRouteStrategy",value="定时任务路由策略",required = true,defaultValue = "ROUND"),
            @ApiImplicitParam(name="executorBlockStrategy",value="定时任务阻塞处理策略，非特殊不用改",required = true,defaultValue = "SERIAL_EXECUTION"),
            @ApiImplicitParam(name="executorParam",value="定时任务任务参数，可不填"),
            @ApiImplicitParam(name="misfireStrategy",value="定时任务调度过期策略，非特殊不用改",defaultValue = "DO_NOTHING"),
            @ApiImplicitParam(name="glueType",value="定时任务运行模式，非特殊不用改",required = true,defaultValue = "BEAN")
    })
    public String xxlJobAdd(Integer jobGroup,String jobCron,String scheduleType,String jobDesc,String author,
                            String alarmEmail,String executorHandler,String executorRouteStrategy,String misfireStrategy,
                            String executorBlockStrategy,String executorParam,String glueType) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("jobGroup", jobGroup);
        jobInfo.put("scheduleType",scheduleType);
        jobInfo.put("scheduleConf", jobCron);
        jobInfo.put("jobDesc", jobDesc);
        jobInfo.put("author", author);
        jobInfo.put("alarmEmail", alarmEmail);
        jobInfo.put("executorRouteStrategy", executorRouteStrategy);
        jobInfo.put("executorHandler", executorHandler);
        jobInfo.put("executorParam", executorParam);
        jobInfo.put("misfireStrategy", misfireStrategy);
        jobInfo.put("executorBlockStrategy", executorBlockStrategy);
        jobInfo.put("glueType", glueType);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/add").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 测试手动触发一次任务
     */
    @GetMapping("/trigger")
    @ApiOperation(value="手动触发定时任务，执行一次")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="定时任务id",required = true),
            @ApiImplicitParam(name="executorParam",value="任务参数"),
            @ApiImplicitParam(name="addressList",value="机器地址")
    })
    public String xxlJobTrigger(Integer id,String executorParam,String addressList) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("id", id);
        jobInfo.put("executorParam", executorParam);
        jobInfo.put("addressList", addressList);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/trigger").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 测试手动删除任务
     */
    @GetMapping("/remove")
    @ApiOperation(value="手动删除定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="定时任务id",required = true)
    })
    public String xxlJobRemove(Integer id) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("id", id);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/remove").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 测试手动停止任务
     */
    @GetMapping("/stop")
    @ApiOperation(value="手动停止定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="定时任务id",required = true)
    })
    public String xxlJobStop(Integer id) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("id", id);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/stop").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }

    /**
     * 测试手动启动任务
     */
    @GetMapping("/start")
    @ApiOperation(value="手动启动定时任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id",value="定时任务id",required = true)
    })
    public String xxlJobStart(Integer id) {
        Map<String, Object> jobInfo = Maps.newHashMap();
        jobInfo.put("id", id);

        HttpResponse execute = HttpUtil.createGet(baseUri + JOB_INFO_URI + "/start").form(jobInfo).execute();
        logger.info("【execute】= {}", execute);
        return execute.body();
    }
}
