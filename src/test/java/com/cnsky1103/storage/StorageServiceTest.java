package com.cnsky1103.storage;

import com.cnsky1103.model.ReminderModeEnum;
import com.cnsky1103.model.SettingState;
import org.junit.jupiter.api.Test;

public class StorageServiceTest {
    @Test
    public void testStore() {
        SettingState settingState = StorageService.getInstance().getState();
        settingState.setReminderTitle("title");
        settingState.setInterval(80);
        settingState.setReminderMode(ReminderModeEnum.DIRECT);
        StorageService.getInstance().setState(settingState);
        // 比对build/idea-sandbox/config/options/A-SOUL-Reminder.xml
    }
}
