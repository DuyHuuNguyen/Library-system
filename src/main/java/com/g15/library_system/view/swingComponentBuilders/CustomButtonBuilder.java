package com.g15.library_system.view.swingComponentBuilders;

import com.g15.library_system.view.overrideComponent.CustomButton;
import java.awt.*;
import javax.swing.*;

/**
 * build a button in easiest way, just set the attribute that you need -.- example: CustomButton
 * myButton = new CustomButtonBuilder("Login") .setFont(new Font("Arial", Font.BOLD, 18))
 * .setTextColor(Color.WHITE) .setBackgroundColor(new Color(66, 135, 245)) .setHoverColor(new
 * Color(66, 100, 200)) .setBorderColor(Color.BLUE) .setGradientColors(Color.CYAN, Color.BLUE)
 * rarely used! .setBorderThickness(4) .setBorderRadius(20) .setDrawBorder(true)
 * .setDarkerWhenPress(true) .setTextPosition(SwingConstants.CENTER) .setSize(new Dimension(150,
 * 50)) .icon("src/main/resources/icons/login.png", 10) .build();
 */
public class CustomButtonBuilder {
  private String text = "";
  private Font font = new Font("Arial", Font.PLAIN, 14);
  private Color textColor = Color.BLACK;
  private Color backgroundColor = new Color(230, 245, 255);
  private Color hoverColor;
  private Color borderColor = Color.BLUE;
  private Color startGradientColor;
  private Color endGradientColor;
  private int thickness = 3;
  private int radius;
  private int horizontalAlignment = SwingConstants.CENTER;
  private Dimension size = new Dimension(120, 40);
  private boolean drawBorder = true;
  private boolean isDarkerWhenPress = true;
  private String iconPath = null;
  private int iconGap = 10;

  public CustomButtonBuilder text(String text) {
    this.text = text;
    return this;
  }

  public CustomButtonBuilder font(Font font) {
    this.font = font;
    return this;
  }

  public CustomButtonBuilder textColor(Color textColor) {
    this.textColor = textColor;
    return this;
  }

  public CustomButtonBuilder backgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public CustomButtonBuilder hoverColor(Color hoverColor) {
    this.hoverColor = hoverColor;
    return this;
  }

  public CustomButtonBuilder borderColor(Color borderColor) {
    this.borderColor = borderColor;
    this.startGradientColor = borderColor;
    this.endGradientColor = borderColor;
    return this;
  }

  public CustomButtonBuilder gradientColors(Color start, Color end) {
    this.startGradientColor = start;
    this.endGradientColor = end;
    return this;
  }

  public CustomButtonBuilder thickness(int thickness) {
    this.thickness = thickness;
    return this;
  }

  public CustomButtonBuilder radius(int radius) {
    this.radius = radius;
    return this;
  }

  public CustomButtonBuilder alignment(int horizontalAlignment) {
    this.horizontalAlignment = horizontalAlignment;
    return this;
  }

  public CustomButtonBuilder size(Dimension size) {
    this.size = size;
    return this;
  }

  public CustomButtonBuilder drawBorder(boolean drawBorder) {
    this.drawBorder = drawBorder;
    return this;
  }

  public CustomButtonBuilder darkerWhenPress(boolean isDarker) {
    this.isDarkerWhenPress = isDarker;
    return this;
  }

  public CustomButtonBuilder icon(String path, int gap) {
    this.iconPath = path;
    this.iconGap = gap;
    return this;
  }

  public CustomButton build() {
    CustomButton button = new CustomButton(text);
    button.setFont(font);
    button.setTextColor(textColor);
    button.setBackgroundColor(backgroundColor);
    button.setHoverColor(hoverColor);
    button.setBorderColor(borderColor);
    button.setGradientColors(startGradientColor, endGradientColor);
    button.setBorderThickness(thickness);
    button.setBorderRadius(radius);
    button.setHorizontalAlignment(horizontalAlignment);
    button.setPreferredSize(size);
    button.setDrawBorder(drawBorder);
    button.setIsDarkerWhenPress(isDarkerWhenPress);

    if (iconPath != null) {
      ImageIcon iconButton = new ImageIcon(iconPath);
      Image image = iconButton.getImage();
      Image resized =
          image.getScaledInstance(size.height - iconGap, size.height - iconGap, Image.SCALE_SMOOTH);
      button.setIcon(new ImageIcon(resized));
    }

    return button;
  }

  public static void main(String[] args) {
    CustomButton myButton =
        new CustomButtonBuilder()
            .text("Đăng nhập")
            .font(new Font("Arial", Font.BOLD, 16))
            .textColor(Color.WHITE)
            .backgroundColor(Color.BLUE)
            .hoverColor(Color.CYAN)
            .radius(20)
            .icon("assets/login.png", 8)
            .build();

    System.out.println(myButton);
  }
}
