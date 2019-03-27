package com.test;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskListenerImpl implements TaskListener {
    public void notify(DelegateTask delegateTask) {
//        delegateTask.setAssignee("张无忌");
        delegateTask.addCandidateUser("张三");
        delegateTask.addCandidateUser("李四");
        delegateTask.addCandidateUser("王五");
    }

}
