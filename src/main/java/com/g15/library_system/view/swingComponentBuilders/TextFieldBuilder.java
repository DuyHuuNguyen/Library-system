package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class TextFieldBuilder extends JTextField {

  public static TextFieldBuilder builder() {
    return new TextFieldBuilder();
  }

  public TextFieldBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public TextFieldBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public TextFieldBuilder textColor(Color color) {
    this.setForeground(color);
    return this;
  }

  public TextFieldBuilder preferredSize(Dimension dimension) {
    this.setPreferredSize(dimension);
    return this;
  }

  public TextFieldBuilder editable(boolean editable) {
    this.setEditable(editable);
    return this;
  }
}
