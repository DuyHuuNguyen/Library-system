package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class ButtonBuilder extends JButton {

  public ButtonBuilder(String text) {
    super(text);
    this.setFocusPainted(false);
    this.setOpaque(true);
  }

  public static ButtonBuilder builder(String text) {
    return new ButtonBuilder(text);
  }

  public ButtonBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public ButtonBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public ButtonBuilder textColor(Color textColor) {
    this.setForeground(textColor);
    return this;
  }

  public ButtonBuilder backgroundColor(Color backgroundColor) {
    this.setBackground(backgroundColor);
    return this;
  }

  public ButtonBuilder hoverColor(Color hoverColor) {
    this.addMouseListener(
        new java.awt.event.MouseAdapter() {
          public void mouseEntered(java.awt.event.MouseEvent evt) {
            setBackground(hoverColor);
          }

          public void mouseExited(java.awt.event.MouseEvent evt) {
            setBackground(getBackground());
          }
        });
    return this;
  }

  public ButtonBuilder borderColor(Color borderColor) {
    this.setBorder(BorderFactory.createLineBorder(borderColor));
    return this;
  }

  public ButtonBuilder preferredSize(Dimension size) {
    this.setPreferredSize(size);
    return this;
  }

  public ButtonBuilder opaque(boolean b) {
    this.setOpaque(b);
    return this;
  }

  public ButtonBuilder contentAreaFilled(boolean isFilled) {
    this.setContentAreaFilled(isFilled);
    return this;
  }

  public ButtonBuilder icon(Icon icon) {
    this.setIcon(icon);
    return this;
  }
}
