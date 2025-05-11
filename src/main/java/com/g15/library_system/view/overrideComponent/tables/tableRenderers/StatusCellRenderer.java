package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.g15.library_system.enums.Status;
import com.g15.library_system.view.Style;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    Status status = Status.get(value.toString());

    Color fgColor = Color.BLACK;
    Color bgColor = Color.WHITE;
    label.setHorizontalAlignment(SwingConstants.CENTER);
    switch (status) {
      case Status.AVAILABLE , Status.RETURNED:
        fgColor = Style.GREEN_STATUS_FOREGROUND_COLOR;
        bgColor = Style.GREEN_STATUS_BACKGROUND_COLOR;
        break;
      case Status.DAMAGED , Status.LOST:
        fgColor = Style.RED_STATUS_FOREGROUND_COLOR;
        bgColor = Style.RED_STATUS_BACKGROUND_COLOR;
        break;
      case Status.OVERDUE:
        fgColor = Style.YELLOW_STATUS_FOREGROUND_COLOR;
        bgColor = Style.YELLOW_STATUS_BACKGROUND_COLOR;
        break;
//      case Status.:
//        fgColor = Style.PURPLE_STATUS_FOREGROUND_COLOR; //waiting new status
//        bgColor = Style.PURPLE_STATUS_BACKGROUND_COLOR;
//        break;
      case Status.NULL:
//        fgColor = Style.PURPLE_STATUS_FOREGROUND_COLOR;
//        bgColor = Style.PURPLE_STATUS_BACKGROUND_COLOR;
        break;
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
