package com.hgnu.study.controller.flowable;

import com.hgnu.study.core.Result;
import com.hgnu.study.exception.TipException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author zj
 * @Description
 * @Date 2021/3/23 10:10
 */

@Controller
@RequestMapping(value = "expense")
@Api(value = "flowable自定义报销流程示例",tags = "flowable工作流demo示例")
public class ExpenseController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 添加报销
     *
     * @param userId    用户Id
     * @param money     报销金额
     * @param descption 描述
     */
    @PostMapping(value = "add")
    @ResponseBody
    @ApiOperation(value="开始流程")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id"),
            @ApiImplicitParam(name="money",value="报销金额"),
            @ApiImplicitParam(name="descption",value="描述")
    })
    public Result addExpense(String userId, Integer money, String descption) {
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", userId);
        map.put("money", money);
        map.put("descption", descption);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Expense", map);
        return Result.success("流水号id为:"+processInstance.getId());
    }

    /**
     * 获取审批管理列表
     */
    @PostMapping(value = "/list")
    @ResponseBody
    @ApiOperation(value="查询流程列表,待办列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="userId",value="用户id")
    })
    public Result list(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        tasks.stream().forEach(e -> System.out.println(e.toString()));
        return Result.success(tasks.toString());
    }

    /**
     * 批准
     *
     * @param taskId 任务ID
     */
    @PostMapping(value = "apply")
    @ResponseBody
    @ApiOperation(value="批准同意")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId",value="任务id")
    })
    public Result apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new TipException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return Result.success();
    }

    /**
     * 拒绝
     */
    @ResponseBody
    @PostMapping(value = "reject")
    @ApiOperation(value="拒绝不同意")
    @ApiImplicitParams({
            @ApiImplicitParam(name="taskId",value="任务id")
    })
    public Result reject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        taskService.complete(taskId, map);
        return Result.success("驳回,reject!!!");
    }

    /**
     * 生成流程图
     *
     * @param processId 任务ID
     */
    @GetMapping(value = "processDiagram")
    @ApiOperation(value="生成流水图")
    @ApiImplicitParams({
            @ApiImplicitParam(name="processId",value="流水号id")
    })
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel,"png",activityIds,flows,engconf.getActivityFontName(),engconf.getLabelFontName(),engconf.getAnnotationFontName(),engconf.getClassLoader(),1.0,true);

        //设置响应的类型格式为图片
        httpServletResponse.setContentType("image/png");
        //禁止图像缓存
        httpServletResponse.setHeader("Pragma","no-cache");
        httpServletResponse.setHeader("Cache-Control","no-cache");
        httpServletResponse.setDateHeader("Expires",0);

        //写出
        OutputStream out = null;
        try {
            out = httpServletResponse.getOutputStream();
            BufferedImage image = null;
            image = ImageIO.read(in);
            ImageIO.write(image,"png",out);
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
