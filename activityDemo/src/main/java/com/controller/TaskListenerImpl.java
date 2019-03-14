package com.controller;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

public class TaskListenerImpl implements TaskListener {
    /**
     * 指定个人任务和组任务的办理人
     */
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.addCandidateUser("zhangsan");
        delegateTask.addCandidateUser("lisi");
    }
}
