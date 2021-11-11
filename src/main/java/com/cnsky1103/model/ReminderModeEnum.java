package com.cnsky1103.model;

import com.cnsky1103.action.OpenPictureAction;
import com.cnsky1103.action.OpenPictureConsumer;
import com.cnsky1103.action.WorkAction;
import com.cnsky1103.config.Text;
import com.cnsky1103.storage.StorageService;
import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationGroupManager;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;

/**
 * 提醒模式
 * @author sky
 */
public enum ReminderModeEnum {
    INDIRECT {
        public String getDescription() {
            // 弹窗提醒
            return "\u5f39\u7a97\u63d0\u9192";
        }

        @Override
        public void remind() {
            System.out.println("indirect remind");
            Notification notification = NotificationGroupManager.getInstance().getNotificationGroup("A-SOUL-Reminder")
                    .createNotification(Text.pluginName, StorageService.getInstance().getState().getReminderBody(),
                            NotificationType.INFORMATION, null);
            notification.addAction(new OpenPictureAction(Text.rest, notification));
            notification.addAction(new WorkAction(Text.work, notification));
            Notifications.Bus.notify(notification);
        }
    },
    DIRECT {
        public String getDescription() {
            // 直接提醒
            return "\u76f4\u63a5\u63d0\u9192";
        }

        @Override
        public void remind() {
            System.out.println("direct remind");
            openPicture();
        }
    };

    public void remind() {
    }

    public String getDescription() {
        return "";
    }

    protected void openPicture() {
        DataManager.getInstance().getDataContextFromFocusAsync()
                .onSuccess(dataContext -> new OpenPictureConsumer().accept(dataContext))
                .onError(System.err::println);
    }
}
