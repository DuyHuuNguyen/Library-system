package com.g15.library_system.view.overrideComponent.checkBoxs;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

public class ModernCheckBoxExample {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame("Modern JCheckBox");
        JCheckBox checkBox = new JCheckBox("Đồng ý điều khoản");

        frame.add(checkBox);
        frame.setSize(300, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
