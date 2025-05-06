package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class CustomCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
  public CustomCheckBoxRenderer() {
    setHorizontalAlignment(CENTER);
    setOpaque(true);
    setBackground(Color.WHITE);
  }

  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    setSelected(value != null && (Boolean) value);
    return this;
  }

}
