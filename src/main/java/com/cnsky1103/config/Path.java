package com.cnsky1103.config;

import com.intellij.openapi.application.PluginPathManager;

import java.io.File;

public class Path {
    /**
     * 获取随机图片
     */
    public static final String randomPictureUrl = "https://api.asoul.cloud:8000/getRandomPic";

    /**
     * 插件配置文件的根目录，目前只支持Windows和mac OS
     */
    public static final String pluginHomePath =
            System.getProperties().getProperty("os").startsWith("Windows") ?
                    "C:\\Users\\" + System.getProperties().getProperty("user.name") + "\\" + ".a-soul-reminder"
                    : "/Users/" + System.getProperties().getProperty("user.name") + "/.a-soul-reminder";

    /**
     * 保存图片文件的目录
     */
    public static final String pluginImgPath = pluginHomePath + File.separator + "img";

}
