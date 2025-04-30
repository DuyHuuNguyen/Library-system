package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.*;
import javax.swing.border.Border;

public class TextFieldBuilder extends JTextField {
  private Color borderColor;

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

  public TextFieldBuilder borderColor(Color borderColor) {
    this.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    this.borderColor = borderColor;
    return this;
  }

  public TextFieldBuilder placeholder(String placeholderText) {
    text(placeholderText).textColor(Color.GRAY);
    this.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            setForeground(Color.BLACK);
            if (getText().equals(placeholderText)) {
              setText("");
            }
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (getText().isEmpty()) {
              setForeground(Color.GRAY);
              setText(placeholderText);
            }
          }
        });
    return this;
  }

  public TextFieldBuilder border(Border border) {
    this.setBorder(border);
    return this;
  }

  public TextFieldBuilder withFocusBorderEffect(Color borderColor) {
    borderColor(borderColor);
    this.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(borderColor, 3));
          }

          @Override
          public void focusLost(FocusEvent e) {
            setBorder(BorderFactory.createLineBorder(borderColor, 1));
          }
        });
    return this;
  }
}
