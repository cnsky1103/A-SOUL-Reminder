package com.cnsky1103.storage;

import com.cnsky1103.model.SettingState;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface StorageService extends PersistentStateComponent<SettingState> {
    static StorageService getInstance() {
        return ApplicationManager.getApplication().getService(StorageService.class);
    }

    @Override
    SettingState getState();

    @Override
    void loadState(@NotNull SettingState settingState);

    void setState(@NotNull SettingState state);
}
