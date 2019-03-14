package com.example.demoServer;

import com.controller.LineAndConditionController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoServerApplicationTests {

	Logger logger = LoggerFactory.getLogger(LineAndConditionController.class);
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;

	@Test
	public void deploy() {
		Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test1.bpmn").deploy();
		logger.info("deploymentID:"+deployment.getId());
	}

	@Test
	public void start(){
		ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1");
		logger.info("ProcessID:"+myProcess_1.getId());
		logger.info("ActivityID:"+myProcess_1.getActivityId());
	}

	@Test
	public void completeTask(){
		Map<String,Object> var=new HashMap<String,Object>();
		var.put("message",2);
		taskService.complete("40001",var);
	}
	@Test
	public void findTask(){
		String userId="zhuguan";
		TaskQuery taskQuery = taskService.createTaskQuery();
		List<Task> list = taskQuery.taskAssignee(userId).list();
		for(Task l:list){
			logger.info(l.getId());
			logger.info(l.getName());
			logger.info(l.getAssignee());
		}


	}

}
