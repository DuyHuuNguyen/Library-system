package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class CheckBoxRenderer extends JCheckBox implements TableCellRenderer {

  public CheckBoxRenderer() {
    super();
    setOpaque(true);
    setBackground(new Color(255, 255, 255, 255));
    setFont(new Font("Segoe UI", Font.BOLD, 5));
    setHorizontalAlignment(JLabel.CENTER);
    setBorderPainted(true);
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    if (value instanceof Boolean) {
      setSelected((Boolean) value);
    }
    this.setSize(new Dimension(1, 1));
    this.setSelected(isSelected);
    if (isSelected) {

      setBackground(new Color(76, 110, 236));
    } else {
      setBackground(new Color(194, 224, 255));
    }

    return this;
  }
}
