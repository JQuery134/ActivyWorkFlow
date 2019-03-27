package com.test;

import org.activiti.engine.*;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.repository.Deployment;
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
//        test2();
//        test3();
        test4();
    }


    public static void test1(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String,Object> varMap = new HashMap<String,Object>();
        varMap.put("userIds","张三,李四,王五");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process4", varMap);
        System.out.println(processInstance.getId());
    }
    public static void test2(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.taskAssignee("张无忌").list();
        for (Task task : taskList){
            System.out.println(task.getId());
            System.out.println(task.getAssignee());
            System.out.println(task.getName());
        }


    }
    public static void test3(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        TaskQuery taskQuery = taskService.createTaskQuery();
        List<Task> taskList = taskQuery.taskCandidateUser("张三").list();
        for (Task task : taskList){
            System.out.println(task.getId());
            System.out.println(task.getAssignee());
            System.out.println(task.getName());
        }


    }

    public static void test4(){
        ProcessEngine processEngine = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml").buildProcessEngine();
        Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("processes/process4.bpmn").name("任务分配").deploy();
        System.out.println("流程部署ID:"+deployment.getId());
        System.out.println("流程部署名称："+deployment.getName());

        IdentityService   identityService =  processEngine.getIdentityService();
        //创建角色
        identityService.saveGroup(new GroupEntity("部门主管"));
        identityService.saveGroup(new GroupEntity("部门经理"));
        identityService.saveGroup(new GroupEntity("CTO"));
        //创建用户
        identityService.saveUser(new UserEntity("张三"));
        identityService.saveUser(new UserEntity("李四"));
        identityService.saveUser(new UserEntity("王五"));
        identityService.saveUser(new UserEntity("赵六"));
        identityService.saveUser(new UserEntity("田七"));
        identityService.saveUser(new UserEntity("胡八"));

        //创建角色和用户的对应关系
        identityService.createMembership("张三", "部门主管");
        identityService.createMembership("李四", "部门主管");
        identityService.createMembership("王五", "部门经理");
        identityService.createMembership("赵六", "部门经理");
        identityService.createMembership("田七", "CTO");
        identityService.createMembership("胡八", "CTO");

    }
}
