package com.cnsky1103.action;

import com.cnsky1103.config.Text;
import com.cnsky1103.model.picture.Picture;
import com.cnsky1103.service.Pictures;
import com.cnsky1103.view.SettingComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * 打开设置页面
 * @author sky
 */
public class OpenSettingAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        new SettingComponent().setVisible(true);
    }
}
