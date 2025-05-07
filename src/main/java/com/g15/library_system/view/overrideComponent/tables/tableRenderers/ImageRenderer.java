package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageRenderer extends DefaultTableCellRenderer {
  private int imgWidth;
  private int imgHeight;

  public ImageRenderer(int imgWidth, int imgHeight) {
    this.imgWidth = imgWidth;
    this.imgHeight = imgHeight;
  }

  @Override
  public Component getTableCellRendererComponent(
      JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

    JLabel label = new JLabel();
    label.setHorizontalAlignment(SwingConstants.CENTER);
    label.setOpaque(true);

    if (value instanceof ImageIcon) {
      ImageIcon icon = (ImageIcon) value;
      Image scaledImage =
          icon.getImage().getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
      label.setIcon(new ImageIcon(scaledImage));
    }

    if (isSelected) {
      label.setBackground(table.getSelectionBackground());
      label.setForeground(table.getSelectionForeground());
    } else {
      label.setBackground(row % 2 == 0 ? Color.WHITE : Style.LIGHT_BLUE_TABLE_ROW_COLOR);
      label.setForeground(table.getForeground());
    }

    return label;
  }
}
