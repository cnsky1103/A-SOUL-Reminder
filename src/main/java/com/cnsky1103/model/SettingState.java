package com.cnsky1103.model;

import com.cnsky1103.config.Data;
import com.cnsky1103.config.Text;
import com.intellij.util.xmlb.annotations.OptionTag;

/**
 * 需要被持久化的配置项
 * @author sky
 */
public class SettingState {
    @OptionTag
    private ReminderModeEnum reminderMode;

    @OptionTag
    private int interval;

    @OptionTag
    private String reminderBody;

    /**
     * 上一张图片的name，需要推迟到下一张图片下载完成后删除
     */
    @OptionTag
    private String lastPictureName;

    public SettingState() {
        reminderMode = ReminderModeEnum.INDIRECT;
        interval = Data.defaultInterval;
        reminderBody = Text.defaultReminderBody;
        lastPictureName = "";
    }

    public ReminderModeEnum getReminderMode() {
        return reminderMode;
    }

    public void setReminderMode(ReminderModeEnum reminderMode) {
        this.reminderMode = reminderMode;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getReminderBody() {
        return reminderBody;
    }

    public void setReminderBody(String reminderBody) {
        this.reminderBody = reminderBody;
    }

    public String getLastPictureName() {
        return lastPictureName;
    }

    public void setLastPictureName(String lastPictureName) {
        this.lastPictureName = lastPictureName;
    }
}
