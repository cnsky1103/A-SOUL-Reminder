package com.cnsky1103.action;

import com.cnsky1103.config.Path;
import com.cnsky1103.model.SettingState;
import com.cnsky1103.model.picture.Picture;
import com.cnsky1103.service.Pictures;
import com.cnsky1103.storage.StorageService;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;

import javax.swing.*;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.function.Consumer;

/**
 * 打开一张图片
 * @author sky
 */
public class OpenPictureConsumer implements Consumer<DataContext> {
    public void accept(DataContext dataContext) {
        System.out.println("accept");
        Project project = dataContext.getData(PlatformDataKeys.PROJECT);
        if (project == null) {
            return;
        }

        Picture picture = null;
        VirtualFile vfPicture = null;
        try {
            SettingState settingState = StorageService.getInstance().getState();
            picture = Pictures.getRandomPicture();
            String lastPicture = settingState.getLastPictureName();
            File lastPictureFile = new File(Path.pluginImgPath + File.separator + lastPicture);
            if (lastPictureFile.exists()) {
                lastPictureFile.delete();
            }
            String picturePath = Pictures.downloadPicture(picture.getImg());
            vfPicture = VirtualFileManager.getInstance().findFileByUrl("file://" + picturePath);

            settingState.setLastPictureName(Pictures.parsePictureNameFromUrl(picture.getImg()));
            StorageService.getInstance().setState(settingState);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (vfPicture == null) {
            return;
        }

        FileEditorManagerEx fileEditorManager = FileEditorManagerEx.getInstanceEx(project);
        EditorWindow currentWindow = fileEditorManager.getCurrentWindow();
        if (currentWindow == null || currentWindow.getTabCount() == 0) {
            fileEditorManager.openFile(vfPicture, true);
        } else {
            EditorWindow nextWindow = fileEditorManager.getNextWindow(currentWindow);
            if (nextWindow == currentWindow) {
                currentWindow.split(SwingConstants.VERTICAL, true, vfPicture, true);
            } else {
                fileEditorManager.openFileWithProviders(vfPicture, true, nextWindow);
            }
        }
    }
}
