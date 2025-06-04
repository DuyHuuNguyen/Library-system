package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.Status;
import com.g15.library_system.view.Style;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class StatusCellRenderer extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
    Component c =
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    JLabel label = (JLabel) c;

    Color fgColor = label.getForeground();
    Color bgColor = label.getBackground();

    if(value != null) {
      BookStatus status = BookStatus.get(value.toString());
      label.setHorizontalAlignment(SwingConstants.CENTER);
      switch (status) {
        case BookStatus.AVAILABLE , BookStatus.RETURNED, BookStatus.ON_TIME:
          fgColor = Style.GREEN_STATUS_FOREGROUND_COLOR;
          bgColor = Style.GREEN_STATUS_BACKGROUND_COLOR;
          break;
        case BookStatus.DAMAGED , BookStatus.LOST:
          fgColor = Style.RED_STATUS_FOREGROUND_COLOR;
          bgColor = Style.RED_STATUS_BACKGROUND_COLOR;
          break;
        case BookStatus.OVERDUE:
          fgColor = Style.YELLOW_STATUS_FOREGROUND_COLOR;
          bgColor = Style.YELLOW_STATUS_BACKGROUND_COLOR;
          break;
        case BookStatus.NULL:
          fgColor = Style.GREEN_STATUS_FOREGROUND_COLOR;
          bgColor = Style.GREEN_STATUS_BACKGROUND_COLOR;
//        fgColor = Style.PURPLE_STATUS_FOREGROUND_COLOR;
//        bgColor = Style.PURPLE_STATUS_BACKGROUND_COLOR;
          break;
      }

    }

    if (isSelected) {
      label.setForeground(table.getSelectionForeground());
      label.setBackground(table.getSelectionBackground());
    } else {
      label.setForeground(fgColor);
      label.setBackground(bgColor);
    }

    return label;
  }
}
