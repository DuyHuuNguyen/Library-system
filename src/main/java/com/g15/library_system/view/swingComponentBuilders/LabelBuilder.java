package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

/**
 * example: JLabel label = new LabelBuilder() .text("Xin chào") .font(new Font("Arial", Font.BOLD,
 * 16)) .textColor(Color.BLUE) .horizontal(SwingConstants.LEFT) .icon("assets/user.png")
 * .iconGap(10) .iconAlignment(SwingConstants.RIGHT) .size(new Dimension(150, 40)) .build();
 */
public class LabelBuilder {
  private String text = "";
  private Font font = new Font("Arial", Font.PLAIN, 14);
  private Color textColor = Color.BLACK;
  private int horizontalAlignment = SwingConstants.CENTER;
  private int verticalAlignment = SwingConstants.CENTER;
  private Dimension size;
  private String iconPath;
  private int iconTextGap = 5;
  private int iconAlignment = JLabel.LEFT;

  public LabelBuilder text(String text) {
    this.text = text;
    return this;
  }

  public LabelBuilder font(Font font) {
    this.font = font;
    return this;
  }

  public LabelBuilder textColor(Color color) {
    this.textColor = color;
    return this;
  }

  public LabelBuilder horizontal(int align) {
    this.horizontalAlignment = align;
    return this;
  }

  public LabelBuilder vertical(int align) {
    this.verticalAlignment = align;
    return this;
  }

  public LabelBuilder size(Dimension size) {
    this.size = size;
    return this;
  }

  public LabelBuilder icon(String path) {
    this.iconPath = path;
    return this;
  }

  public LabelBuilder iconGap(int gap) {
    this.iconTextGap = gap;
    return this;
  }

  public LabelBuilder iconAlignment(int alignment) {
    this.iconAlignment = alignment;
    return this;
  }

  public JLabel build() {
    JLabel label = new JLabel(text);
    label.setFont(font);
    label.setForeground(textColor);
    label.setHorizontalAlignment(horizontalAlignment);
    label.setVerticalAlignment(verticalAlignment);
    label.setIconTextGap(iconTextGap);

    if (size != null) {
      label.setPreferredSize(size);
    }

    if (iconPath != null) {
      ImageIcon icon = new ImageIcon(iconPath);
      label.setIcon(icon);
      label.setHorizontalTextPosition(iconAlignment);
    }

    return label;
  }
}
