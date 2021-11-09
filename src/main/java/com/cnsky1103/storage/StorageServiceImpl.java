package com.cnsky1103.storage;

import com.cnsky1103.model.SettingState;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;

@State(name = "A-SOUL-Reminder", storages = @Storage("A-SOUL-Reminder.xml"))
public class StorageServiceImpl implements StorageService{
    private SettingState settingState;

    public StorageServiceImpl() {
        settingState = new SettingState();
    }

    @Override
    public SettingState getState() {
        return settingState;
    }

    @Override
    public void loadState(@NotNull SettingState settingState) {
        XmlSerializerUtil.copyBean(settingState, this.settingState);
    }

    /**
     * 会被保存到build/idea-sandbox/config/options/A-SOUL-Reminder.xml
     * @param state
     */
    @Override
    public void setState(@NotNull SettingState state) {
        this.settingState = state;
    }
}
