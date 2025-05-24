package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedShadowPanel extends JPanel {

  private int radius;
  private Color backgroundColor;
  private Color shadowColor;
  private int shadowSize;
  private int shadowOffset;

  public RoundedShadowPanel() {
    this(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setLayout(new BorderLayout());
  }

  public RoundedShadowPanel(
      int radius, Color backgroundColor, Color shadowColor, int shadowSize, int shadowOffset) {
    this.radius = radius;
    this.backgroundColor = backgroundColor;
    this.shadowColor = shadowColor;
    this.shadowSize = shadowSize;
    this.shadowOffset = shadowOffset;
    this.setOpaque(false);
    this.setBorder(new EmptyBorder(15, 15, 15, 15));
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int width = getWidth();
    int height = getHeight();

    g2.setColor(shadowColor);
    g2.fillRoundRect(
        shadowOffset, shadowOffset, width - shadowSize, height - shadowSize, radius, radius);

    g2.setColor(backgroundColor);
    g2.fillRoundRect(0, 0, width - shadowSize, height - shadowSize, radius, radius);

    g2.dispose();
    super.paintComponent(g);
  }
}
