package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;

public class RainbowTextLabel extends JLabel {
  private Color color1;
  private Color color2;
  private Color color3;

  public RainbowTextLabel(String text) {
    super(text);
    setFont(new Font("Arial", Font.BOLD, 40));
    setPreferredSize(new Dimension(500, 100));
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    int width = getWidth();
    int height = getHeight();

    // Mảng màu rainbow
    Color[] rainbowColors = {
      Color.RED,
      Color.ORANGE,
      Color.YELLOW,
      Color.GREEN,
      Color.BLUE,
      new Color(75, 0, 130),
      new Color(143, 0, 255)
    };

    float[] fractions = {0.0f, 0.17f, 0.33f, 0.5f, 0.67f, 0.83f, 1.0f};

    LinearGradientPaint gradient =
        new LinearGradientPaint(0, 0, width, 0, fractions, rainbowColors);

    g2d.setPaint(gradient);
    g2d.setFont(getFont());

    FontMetrics fm = g2d.getFontMetrics();
    int x = (width - fm.stringWidth(getText())) / 2;
    int y = (height - fm.getHeight()) / 2 + fm.getAscent();

    g2d.drawString(getText(), x, y);
    g2d.dispose();
  }
}
