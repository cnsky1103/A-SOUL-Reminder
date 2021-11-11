package com.cnsky1103.activity;

import com.cnsky1103.config.Path;
import com.cnsky1103.service.ReminderTask;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

/**
 * 随IDEA启动且打开project而执行
 * @see <a href="https://blog.csdn.net/u013541707/article/details/113758797"/>
 */
public class ReminderPostStartupActivity implements StartupActivity {
    @Override
    public void runActivity(@NotNull Project project) {
        System.out.println("run reminder startup activity");
        ReminderTask.init();
        createDirectory();
    }

    /**
     * 创建保存图片的目录
     */
    private void createDirectory() {
        File pluginHomeDir = new File(Path.pluginHomePath);
        if (!pluginHomeDir.exists() || !pluginHomeDir.isDirectory()) {
            pluginHomeDir.mkdir();
        }
        File pluginImgDir = new File(Path.pluginImgPath);
        if (!pluginImgDir.exists() || !pluginImgDir.isDirectory()) {
            pluginImgDir.mkdir();
        }
    }
}
