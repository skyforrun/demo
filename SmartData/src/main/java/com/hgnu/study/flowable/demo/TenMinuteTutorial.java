package com.hgnu.study.flowable.demo;

import org.flowable.engine.*;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.task.api.Task;

import java.util.List;

/**
 * @Author zj
 * @Description
 * 有⼀个公司，叫做BPMCorp。在BPMCorp中，由会计部门负责，每⽉需要为投资⼈撰写⼀份报告。
 * 在报告完成后，需要⾼层经理中的⼀⼈进⾏审核，然后才能 发给所有投资⼈
 * 请注意第⼀个任务分配给accountancy组，⽽第⼆个任务分配给management组
 * @Date 2021/3/23 17:48
 */
public class TenMinuteTutorial {
    public static void main(String[] args) {
        // 创建Flowable流程引擎
        ProcessEngine processEngine = ProcessEngineConfiguration
                .createStandaloneProcessEngineConfiguration()
                .buildProcessEngine();

        // 获取Flowable服务
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        // 部署流程定义
        repositoryService.createDeployment()
                .addClasspathResource("FinancialReportProcess.bpmn20.xml")
                .deploy();

        // 启动流程实例
        String procId = runtimeService.startProcessInstanceByKey("financialReport").getId();

        // 获取第⼀个任务
        /**
         * 因为我们使⽤与演⽰配置中相同的数据库配置 ProcessEngine ，因此可以直接登录Flowable IDM (http://localhost:8080/flowable-idm/)。
         * 使⽤admin/test登录，创建两个新⽤ 户kermit与fozzie，并将Access the workflow application(访问⼯作流应⽤)权限授予他们。
         * 然后创建两个组，命名为accountancy与management，并将fozzie添加⾄ accountancy组，将kermit添加⾄management组。
         * 然后以fozzie登录Flowable task (http://localhost:8080/flowable-task/)应⽤。
         * 选择Task应⽤，再选择其Processes页⾯，选 择'Monthly financial report （⽉度财务报告）'，这样就可以启动我们的业务流程
         */
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
            // 申领任务
            taskService.claim(task.getId(), "fozzie");
        }

        // 验证Fozzie获取了任务
        /**
         * 会计师（accountancy组的成员）现在需要申领任务（claim）。申领任务后，这个⽤户会成为任务的执⾏⼈（assignee），
         * 这个任务也会从accountancy组的其他成员的任 务列表中消失
         */
        tasks = taskService.createTaskQuery().taskAssignee("fozzie").list();
        for (Task task : tasks) {
            System.out.println("Task for fozzie: " + task.getName());
            // 完成任务
            taskService.complete(task.getId());
        }
        System.out.println("Number of tasks for fozzie: " + taskService.createTaskQuery().taskAssignee("fozzie").count());

        // 获取并申领第⼆个任务
        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for management group: " + task.getName());
            taskService.claim(task.getId(), "kermit");
        }

        // 完成第⼆个任务并结束流程
        /**
         * 会计师（accountancy组的成员）现在需要开始撰写财务报告了。完成报告后，他就可以完成任务（complete），代表任务的所有⼯作都已完成
         * 因为Fozzie不是经理，我们需要登出Flowable Task应⽤，并⽤kermit（他是经理）登录。这样就可以在 未分配任务列表中看到第⼆个任务
         */
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }

        // 验证流程已经结束
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(procId).singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
    }
}
