package com.g15.library_system.view.swingComponentGenerators;

import javax.swing.*;
import java.awt.*;

public class RadioButtonGenerator {

    public static JRadioButton createRadioButton(
            String title, Font font, Color textColor, Color backgroudColor, Dimension size) {
        JRadioButton rdoBt = new JRadioButton(title);
        rdoBt.setBorderPainted(false);
        rdoBt.setFocusable(false);
        rdoBt.setFont(font);
        rdoBt.setBackground(backgroudColor);
        rdoBt.setForeground(textColor);
        rdoBt.setPreferredSize(size);
        return rdoBt;
    }
}
