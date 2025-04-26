package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class CheckBoxBuilder extends JCheckBox {

  public static CheckBoxBuilder builder() {
    return new CheckBoxBuilder();
  }

  public CheckBoxBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public CheckBoxBuilder selected(boolean selected) {
    this.setSelected(selected);
    return this;
  }

  public CheckBoxBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public CheckBoxBuilder textColor(Color textColor) {
    this.setForeground(textColor);
    return this;
  }

  public CheckBoxBuilder backgroundColor(Color backgroundColor) {
    this.setBackground(backgroundColor);
    return this;
  }
}
