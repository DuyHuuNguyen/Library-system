package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import javax.swing.*;

public class ShowEntriesPanel extends JPanel {
  public ShowEntriesPanel() {
    setLayout(new FlowLayout(FlowLayout.RIGHT));

    JLabel showLabel = new JLabel("Show");
    showLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));

    String[] options = {"10"};
    JComboBox<String> comboBox = new JComboBox<>(options);
    comboBox.setPreferredSize(new Dimension(70, 25));

    add(showLabel);
    add(comboBox);
  }
}
