package com.cnsky1103.action;

import com.intellij.ide.DataManager;
import com.intellij.notification.Notification;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.jetbrains.annotations.NotNull;

/**
 * 打开一张图片
 * @author sky
 */
public class OpenPictureAction extends AnAction {
    private Notification notification;

    public OpenPictureAction() {
    }

    public OpenPictureAction(String text, Notification notification) {
        super(text);
        this.notification = notification;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        System.out.println("actionPerformed");

        DataManager.getInstance().getDataContextFromFocusAsync()
                .onSuccess(dataContext -> {new OpenPictureConsumer().accept(dataContext);})
                .onError(System.err::println);

        notification.expire();
    }
}
