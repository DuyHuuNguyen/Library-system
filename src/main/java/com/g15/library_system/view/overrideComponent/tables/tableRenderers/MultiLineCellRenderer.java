package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

  public MultiLineCellRenderer() {
    setLineWrap(false);
    setWrapStyleWord(false);
    setOpaque(true);
    setBorder(null);
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    int defaultRowHeight = table.getRowHeight();

    if (value != null) {
      String text = value.toString().replaceAll(",\\s*", ",\n");
      setText(text);
    } else {
      setText("");
    }

    if (isSelected) {
      setBackground(table.getSelectionBackground());
      setForeground(table.getSelectionForeground());
    } else {
      setBackground(row % 2 == 0 ? Color.WHITE : Style.LIGHT_BLUE_TABLE_ROW_COLOR);
      setForeground(table.getForeground());
    }

    setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
    int preferredHeight = getPreferredSize().height;

    if (preferredHeight > defaultRowHeight) {
      table.setRowHeight(row, preferredHeight + 10);
    } else {
      table.setRowHeight(row, defaultRowHeight);
    }

    return this;
  }
}
