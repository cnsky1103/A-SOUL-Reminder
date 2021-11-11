package com.cnsky1103.service;

import com.cnsky1103.model.SettingState;
import com.cnsky1103.storage.StorageService;
import com.intellij.concurrency.JobScheduler;
import com.intellij.openapi.application.ApplicationManager;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * 定时提醒任务
 * @author sky
 */
public class ReminderTask {
    private static final ThreadLocal<ScheduledFuture<?>> thread = new ThreadLocal<>();

    public static void init() {
        restart();
    }

    public static void restart() {
        destroy();

        SettingState settingState = StorageService.getInstance().getState();
        ScheduledFuture<?> scheduledFuture = JobScheduler.getScheduler()
                .scheduleWithFixedDelay(() -> {
                    ApplicationManager.getApplication()
                            .invokeLater(settingState.getReminderMode()::remind);
                }, StorageService.getInstance().getState().getInterval(), StorageService.getInstance().getState().getInterval(), TimeUnit.MINUTES);
        thread.set(scheduledFuture);
    }

    public static void destroy() {
        ScheduledFuture<?> existingFuture = thread.get();
        if (existingFuture != null) {
            existingFuture.cancel(true);
            thread.remove();
        }
    }
}
