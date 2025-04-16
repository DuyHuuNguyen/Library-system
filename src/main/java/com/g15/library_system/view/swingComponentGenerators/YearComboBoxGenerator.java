package com.g15.library_system.view.swingComponentGenerators;


import com.g15.library_system.view.Style;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Year;
import java.awt.*;
public class YearComboBoxGenerator{

    public static JComboBox<Integer> createYearComboBox(int startYear) {
        JComboBox<Integer> yearComboBox = new JComboBox<>();
        int currentYear = LocalDate.now().getYear();

        DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<>();
        for (int year = currentYear; year >= startYear; year--) {
            yearModel.addElement(year);
        }

        yearComboBox = new JComboBox<>(yearModel);
        yearComboBox.setSelectedItem(currentYear);
        yearComboBox.setFont(Style.FONT_PLAIN_13);
        yearComboBox.setBorder(BorderFactory.createLineBorder(Style.MENU_BUTTON_COLOR, 2));
        yearComboBox.setFocusable(false);
        yearComboBox.setPreferredSize(new Dimension(80, 25));
        return yearComboBox;
    }
}