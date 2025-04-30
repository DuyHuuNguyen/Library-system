package com.g15.library_system.view.swingComponentBuilders;

import com.g15.library_system.view.overrideComponent.CustomButton;
import java.awt.*;
import javax.swing.*;

public class CustomButtonBuilder extends CustomButton {

  public CustomButtonBuilder(String text) {
    super(text);
  }

  public static CustomButtonBuilder builder() {
    return new CustomButtonBuilder("");
  }

  public CustomButtonBuilder text(String text) {
    this.setText(text);
    return this;
  }

  public CustomButtonBuilder font(Font font) {
    this.setFont(font);
    return this;
  }

  public CustomButtonBuilder textColor(Color textColor) {
    this.setTextColor(textColor);
    return this;
  }

  public CustomButtonBuilder backgroundColor(Color backgroundColor) {
    this.setBackgroundColor(backgroundColor);
    return this;
  }

  public CustomButtonBuilder hoverColor(Color hoverColor) {
    this.setHoverColor(hoverColor);
    return this;
  }

  public CustomButtonBuilder borderColor(Color borderColor) {
    this.setBorderColor(borderColor);
    return this;
  }

  public CustomButtonBuilder gradientColors(Color start, Color end) {
    this.setGradientColors(start, end);
    return this;
  }

  public CustomButtonBuilder thickness(int thickness) {
    this.setBorderThickness(thickness);
    return this;
  }

  public CustomButtonBuilder radius(int radius) {
    this.setBorderRadius(radius);
    return this;
  }

  public CustomButtonBuilder alignment(int horizontalAlignment) {
    this.setHorizontalAlignment(horizontalAlignment);
    return this;
  }

  public CustomButtonBuilder preferredSize(Dimension size) {
    this.setPreferredSize(size);
    return this;
  }

  public CustomButtonBuilder drawBorder(boolean drawBorder) {
    this.setDrawBorder(drawBorder);
    return this;
  }

  public CustomButtonBuilder darkerWhenPress(boolean isDarker) {
    this.setIsDarkerWhenPress(isDarker);
    return this;
  }

  public CustomButtonBuilder opaque(boolean b) {
    this.setOpaque(b);
    return this;
  }

  public CustomButtonBuilder contentAreaFilled(boolean isFilled) {
    this.setContentAreaFilled(isFilled);
    return this;
  }

  public CustomButtonBuilder icon(String path, int gap) {
    ImageIcon iconButton = new ImageIcon(getClass().getResource(path));
    Image image = iconButton.getImage();
    Image resized =
        image.getScaledInstance(
            this.getPreferredSize().height - gap,
            this.getPreferredSize().height - gap,
            Image.SCALE_SMOOTH);
    this.setIcon(new ImageIcon(resized));
    return this;
  }
}
