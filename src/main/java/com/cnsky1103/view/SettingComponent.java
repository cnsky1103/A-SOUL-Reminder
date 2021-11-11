/*
 * Created by JFormDesigner on Tue Nov 09 23:48:54 CST 2021
 */

package com.cnsky1103.view;

import com.cnsky1103.config.Data;
import com.cnsky1103.config.Text;
import com.cnsky1103.model.ReminderModeEnum;
import com.cnsky1103.model.SettingState;
import com.cnsky1103.service.ReminderTask;
import com.cnsky1103.storage.StorageService;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * 设置界面
 * 由JFormDesigner插件生成
 * @author sky
 */
public class SettingComponent extends JFrame {
    public SettingComponent() {
        initComponents();
    }

    private void okButtonActionPerformed(ActionEvent e) {
        System.out.println("okButtonActionPerformed");

        SettingState settingState = StorageService.getInstance().getState();
        settingState.setReminderBody(this.reminderBodyText.getText());
        try {
            int inputInterval = Integer.parseInt(this.intervalText.getText());
            if (inputInterval > 0) {
                settingState.setInterval(inputInterval);
            } else {
                settingState.setInterval(Data.defaultInterval);
            }
        } catch (NumberFormatException ex) {
            settingState.setInterval(Data.defaultInterval);
        }
        settingState.setReminderMode(ReminderModeEnum.values()[this.reminderModeComboBox.getSelectedIndex()]);
        StorageService.getInstance().setState(settingState);

        this.setVisible(false);

        ReminderTask.restart();
    }

    private void cancelButtonActionPerformed(ActionEvent e) {
        System.out.println("cancelButtonActionPerformed");
        this.setVisible(false);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        reminderBodyLabel = new JLabel();
        reminderBodyText = new JTextField();
        intervalLabel = new JLabel();
        intervalText = new JTextField();
        reminderModeLabel = new JLabel();
        reminderModeComboBox = new JComboBox<>();
        buttonBar = new JPanel();
        okButton = new JButton();
        cancelButton = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setLayout(new GridLayout(3, 0));

                //---- reminderBodyLabel ----
                reminderBodyLabel.setText(Text.reminderBodyLabel);
                contentPanel.add(reminderBodyLabel);

                //---- reminderBodyText ----
                reminderBodyText.setText(StorageService.getInstance().getState().getReminderBody());
                contentPanel.add(reminderBodyText);

                //---- intervalLabel ----
                intervalLabel.setText(Text.intervalLabel);
                contentPanel.add(intervalLabel);

                //---- intervalText ----
                intervalText.setText(String.valueOf(StorageService.getInstance().getState().getInterval()));
                contentPanel.add(intervalText);

                //---- reminderModeLabel ----
                reminderModeLabel.setText(Text.reminderModeLabel);
                contentPanel.add(reminderModeLabel);

                //---- reminderModeComboBox ----
                reminderModeComboBox.setModel(new DefaultComboBoxModel<>(new String[] {
                    ReminderModeEnum.INDIRECT.getDescription(),
                    ReminderModeEnum.DIRECT.getDescription()
                }));
                reminderModeComboBox.setSelectedIndex(StorageService.getInstance().getState().getReminderMode().ordinal());
                contentPanel.add(reminderModeComboBox);
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0};

                //---- okButton ----
                okButton.setText("OK");
                okButton.addActionListener(e -> okButtonActionPerformed(e));
                buttonBar.add(okButton, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- cancelButton ----
                cancelButton.setText("Cancel");
                cancelButton.addActionListener(e -> cancelButtonActionPerformed(e));
                buttonBar.add(cancelButton, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel reminderBodyLabel;
    private JTextField reminderBodyText;
    private JLabel intervalLabel;
    private JTextField intervalText;
    private JLabel reminderModeLabel;
    private JComboBox<String> reminderModeComboBox;
    private JPanel buttonBar;
    private JButton okButton;
    private JButton cancelButton;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
