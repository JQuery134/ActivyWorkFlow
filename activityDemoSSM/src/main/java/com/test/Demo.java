package com.test;

import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {

    public static void main(String[] args) {
//        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
//        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
//        RepositoryService repositoryService = processEngine.getRepositoryService();
//        DeploymentBuilder deployment = repositoryService.createDeployment();
//        DeploymentBuilder deploymentBuilder = deployment.addClasspathResource("processes/test1.bpmn");
//        deploymentBuilder.deploy();

//        test1();
            test2();
    }


    public static void test1(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> varMap = new HashMap<String,Object>();
        varMap.put("userId","包华杰");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process", varMap);
        System.out.println(processInstance.getId());
    }
    public static void test2(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.taskAssignee("包华杰").list();
        for (Task task : taskList){
            System.out.println(task.getId());
            System.out.println(task.getAssignee());
            System.out.println(task.getName());
        }


    }
}
