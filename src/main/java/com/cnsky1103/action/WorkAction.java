package com.cnsky1103.action;

import com.cnsky1103.service.ReminderTask;
import com.intellij.notification.Notification;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * 继续工作
 * @author sky
 */
public class WorkAction extends AnAction {
    private Notification notification;

    public WorkAction() {
    }

    public WorkAction(String text, Notification notification) {
        super(text);
        this.notification = notification;
    }

    @Override
    public void actionPerformed(AnActionEvent e) {
        notification.expire();
        ReminderTask.restart();
    }
}
