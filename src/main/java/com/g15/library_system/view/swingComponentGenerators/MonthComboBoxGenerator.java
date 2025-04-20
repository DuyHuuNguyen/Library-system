package com.g15.library_system.view.swingComponentGenerators;

import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;

public class MonthComboBoxGenerator {
  private static String[] months = {
    "All",
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  };

  public static JComboBox<String> createMonthComboBox() {
    JComboBox<String> monthComboBox = new JComboBox<>(months);
    monthComboBox.setFont(Style.FONT_PLAIN_13);
    monthComboBox.setBorder(BorderFactory.createLineBorder(Style.MENU_BUTTON_COLOR, 2));
    monthComboBox.setFocusable(false);
    monthComboBox.setPreferredSize(new Dimension(80, 25));
    return monthComboBox;
  }
}
