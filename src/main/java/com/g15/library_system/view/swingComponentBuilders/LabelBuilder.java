package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class LabelBuilder extends JLabel {

  public static LabelBuilder builder() {
    return new LabelBuilder();
  }

  public LabelBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public LabelBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public LabelBuilder textColor(Color color) {
    this.setForeground(color);
    return this;
  }

  public LabelBuilder horizontal(int align) {
    this.setHorizontalAlignment(align);
    return this;
  }

  public LabelBuilder vertical(int align) {
    this.setVerticalAlignment(align);
    return this;
  }

  public LabelBuilder preferredSize(Dimension size) {
    this.setPreferredSize(size);
    return this;
  }

  public LabelBuilder icon(String path) {
    this.setIcon(new ImageIcon(path));
    return this;
  }

  public LabelBuilder iconTextGap(int gap) {
    this.setIconTextGap(gap);
    return this;
  }

  public LabelBuilder iconAlignment(int alignment) {
    this.setHorizontalTextPosition(alignment);
    return this;
  }
}
