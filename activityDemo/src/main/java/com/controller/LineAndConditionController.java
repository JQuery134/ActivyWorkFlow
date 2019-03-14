package com.controller;


import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 连线,条件
 */
@RestController
@RequestMapping(value = "lineAndCondition")
public class LineAndConditionController {

    private Logger logger = LoggerFactory.getLogger(LineAndConditionController.class);
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/deploy")
    public String deploy() {
        //部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test1.bpmn").deploy();
        logger.info("deploymentId:"+deployment.getId());
        //开始流程
        ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1");
        logger.info("ProcessId:"+myProcess_1.getId());
        logger.info("ActivityId:"+myProcess_1.getActivityId());
        //查询流程
        List<Task> list = taskService.createTaskQuery().processInstanceId(myProcess_1.getId()).list();
        //遍历流程并完成
        Map<String,Object> var=new HashMap<String,Object>();
        var.put("message",2);
        Utils.traverseAndComplete(list,var);
        list = taskService.createTaskQuery().processInstanceId(myProcess_1.getId()).list();
        //遍历流程并完成
        Utils.traverseAndComplete(list,null);
        return "OK";
    }

}
