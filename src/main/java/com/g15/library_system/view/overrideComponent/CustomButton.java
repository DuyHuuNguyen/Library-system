package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
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
  private RoundedSide roundedSide = RoundedSide.BOTH;

  public enum RoundedSide {
    NONE,
    LEFT,
    RIGHT,
    BOTH
  }

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

    Shape shape = createRoundedShape();

    if (getModel().isPressed() && isDarkerWhenPress) {
      g2d.setColor(backgroundColor.darker());
    } else if (getModel().isRollover() && hoverColor != null) {
      g2d.setColor(hoverColor);
    } else {
      g2d.setColor(backgroundColor);
    }

    g2d.fill(shape);
    g2d.dispose();
    super.paintComponent(g);
  }

  @Override
  protected void paintBorder(Graphics g) {
//    if (drawBorder) {
//      Graphics2D g2d = (Graphics2D) g.create();
//      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//      g2d.setStroke(new BasicStroke(thickness));
//      GradientPaint gradient =
//          new GradientPaint(0, 0, startGradientColor, getWidth(), getHeight(), endGradientColor);
//      g2d.setPaint(gradient);
//      g2d.draw(createRoundedShape());
//      g2d.dispose();
//    }
    if (drawBorder) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.setStroke(new BasicStroke(thickness));

      GradientPaint gradient =
              new GradientPaint(
                      0, 0, startGradientColor, getWidth(), getHeight(), endGradientColor, true);
      g2d.setPaint(gradient);
      g2d.drawRoundRect(1, 1, getWidth() - 2, getHeight() - 2, borderRadius, borderRadius);
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

  public void setRoundedSide(RoundedSide side) {
    this.roundedSide = side;
    repaint();
  }

  private Shape createRoundedShape() {
    int w = getWidth();
    int h = getHeight();
    int r = borderRadius;

    Path2D path = new Path2D.Float();

    switch (roundedSide) {
      case LEFT:
        path.moveTo(r, 0);
        path.quadTo(0, 0, 0, r);
        path.lineTo(0, h - r);
        path.quadTo(0, h, r, h);
        path.lineTo(w, h);
        path.lineTo(w, 0);
        path.closePath();
        break;
      case RIGHT:
        path.moveTo(0, 0);
        path.lineTo(w - r, 0);
        path.quadTo(w, 0, w, r);
        path.lineTo(w, h - r);
        path.quadTo(w, h, w - r, h);
        path.lineTo(0, h);
        path.closePath();
        break;
      case BOTH:
        return new RoundRectangle2D.Float(0, 0, w, h, r, r);
      default:
        return new Rectangle(0, 0, w, h);
    }

    return path;
  }
}
