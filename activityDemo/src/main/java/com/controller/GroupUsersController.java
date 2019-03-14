package com.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组任务
 */
@RestController
@RequestMapping(value = "groupUsers")
public class GroupUsersController {

    Logger logger = LoggerFactory.getLogger(GroupUsersController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "deploy")
    public String deploy(){
        //部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test2.bpmn").deploy();
        logger.info("deploymentId:"+deployment.getId());
        //开始流程
        ProcessInstance myProcess_2 = runtimeService.startProcessInstanceByKey("myProcess_2");
        logger.info("ProcessId:"+myProcess_2.getId());
        //查询组任务列表
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("a").list();
        //该任务的组成员
        Utils.getUsersList(list,taskService);
        return "OK";
    }

    /**
     * 使用变量定义组成员
     * @return
     */
    @RequestMapping(value = "deploy2")
    public String deploy2(){
        //部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test2_2.bpmn").deploy();
        logger.info("deploymentId:"+deployment.getId());
        //启动流程
        ProcessInstance myProcess_2_2 = runtimeService.startProcessInstanceByKey("myProcess_2_2");
        logger.info("ProcessId:"+myProcess_2_2.getId());
        List<Task> list = taskService.createTaskQuery().taskCandidateUser("zhangsan").list();
        Utils.getUsersList(list,taskService);
        return "OK";
    }

}
