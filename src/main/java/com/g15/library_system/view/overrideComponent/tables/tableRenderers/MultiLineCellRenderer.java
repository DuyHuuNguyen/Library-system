package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;

public class MultiLineCellRenderer extends JTextArea implements TableCellRenderer {

  public MultiLineCellRenderer() {
    setLineWrap(false); // Không tự wrap từ
    setWrapStyleWord(false);
    setOpaque(true);
    setBorder(null);
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    int defaultRowHeight = table.getRowHeight(); // Lưu chiều cao mặc định

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

    // Đặt kích thước để tính toán preferredHeight
    setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
    int preferredHeight = getPreferredSize().height;

    // Chỉ điều chỉnh nếu cao hơn chiều cao mặc định (tức là có nhiều dòng)
    if (preferredHeight > defaultRowHeight) {
      table.setRowHeight(row, preferredHeight);
    } else {
      table.setRowHeight(row, defaultRowHeight); // Reset lại nếu trước đó đã tăng
    }

    return this;
  }
}
