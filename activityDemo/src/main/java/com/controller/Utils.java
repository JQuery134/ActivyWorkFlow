package com.controller;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class Utils {

    private static Logger logger = LoggerFactory.getLogger(Utils.class);

//    @Autowired
//    private static TaskService taskService;
    /**
     * 遍历并完成任务
     * @param list
     */
    public static void  traverseAndComplete(List<Task> list, Map<String,Object> var){
        //遍历流程
        for(Task t:list){
            logger.info("taskName:"+t.getName());
            logger.info("taskId:"+t.getId());
            logger.info("Assignee:"+t.getAssignee());
            logger.info("executionId:"+t.getExecutionId());
            if (var==null){
//                taskService.complete(t.getId());
            }else{
//                taskService.complete(t.getId(),var);
            }
            logger.info("任务"+t.getName()+"执行完毕");
        }
    }

    /**
     * 遍历任务
     * @param list
     */
    public static void  traverse(List<Task> list){
        //遍历流程
        for(Task t:list){
            logger.info("taskName:"+t.getName());
            logger.info("taskId:"+t.getId());
            logger.info("Assignee:"+t.getAssignee());
            logger.info("executionId:"+t.getExecutionId());
        }
    }

    /**
     *
     * @param list
     */
    public static void getUsersList(List<Task> list,TaskService taskService){
        for(Task t:list){
            List<IdentityLink> identityLinksForTask = taskService.getIdentityLinksForTask(t.getId());
            for(IdentityLink i:identityLinksForTask){
                logger.info("userId:"+i.getUserId());
                logger.info("taskId:"+i.getTaskId());
                logger.info("piId:"+i.getProcessInstanceId());
                logger.info("DefinitionId:"+i.getProcessDefinitionId());
                logger.info("GroupId:"+i.getGroupId());
            }

        }
    }
}
