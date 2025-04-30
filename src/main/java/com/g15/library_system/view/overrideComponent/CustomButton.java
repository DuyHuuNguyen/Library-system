package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;

public class CustomButton extends JButton {
  private Color backgroundColor = Color.WHITE;
  private Color borderColor;
  private Color startGradientColor = backgroundColor;
  private Color endGradientColor = backgroundColor;
  private Color hoverColor;
  private Color textColor = Color.BLACK;
  private int thickness = 3;
  private int borderRadius = 0;
  private boolean drawBorder = true;
  private boolean isDarkerWhenPress = true;

  public CustomButton(String text) {
    super(text);
    setContentAreaFilled(false);
    setFocusPainted(false);
    setForeground(textColor);
    setFont(new Font("Arial", Font.BOLD, 16));
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (getModel().isPressed() && isDarkerWhenPress) {
      if (backgroundColor.getAlpha() != 0) {
        g2d.setColor(backgroundColor.darker());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
      }
    } else if (getModel().isRollover() && hoverColor != null) {
      g2d.setColor(hoverColor);
      g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
    } else {
      if (backgroundColor.getAlpha() != 0) {
        g2d.setColor(backgroundColor);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
      }
    }

    g2d.dispose();
    super.paintComponent(g);
  }

  @Override
  protected void paintBorder(Graphics g) {
    if (drawBorder) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setStroke(new BasicStroke(thickness));

      GradientPaint gradient =
          new GradientPaint(
              0, 0, startGradientColor, getWidth(), getHeight(), endGradientColor, true);
      g2d.setPaint(gradient);
      g2d.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, borderRadius, borderRadius);
    }
  }

  public void setTextColor(Color color) {
    this.textColor = color;
    setForeground(color);
    repaint();
  }

  public void setBorderColor(Color color) {
    this.borderColor = color;
    this.startGradientColor = color;
    this.endGradientColor = color;
    repaint();
  }

  public void setBorderThickness(int thickness) {
    this.thickness = thickness;
    repaint();
  }

  public void setGradientColors(Color startColor, Color endColor) {
    this.startGradientColor = startColor;
    this.endGradientColor = endColor;
    repaint();
  }

  public void setBackgroundColor(Color color) {
    this.backgroundColor = color;
    repaint();
  }

  public void setHoverColor(Color color) {
    this.hoverColor = color;
    repaint();
  }

  public void setBorderRadius(int radius) {
    this.borderRadius = radius;
    repaint();
  }

  public void setDrawBorder(boolean drawBorder) {
    this.drawBorder = drawBorder;
    repaint();
  }

  public void setIsDarkerWhenPress(boolean isDarkerWhenPress) {
    this.isDarkerWhenPress = isDarkerWhenPress;
  }
}
