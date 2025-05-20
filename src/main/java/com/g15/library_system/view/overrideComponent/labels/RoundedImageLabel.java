package com.g15.library_system.view.overrideComponent.labels;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RoundedImageLabel extends JLabel {
  private BufferedImage image;
  private int width, height;
  private int cornerRadius = 20; // corner radius

  public RoundedImageLabel(String imagePath, int width, int height) {
    this.width = width;
    this.height = height;
    try {
      image = ImageIO.read(getClass().getResource(imagePath));
    } catch (IOException | IllegalArgumentException e) {
      e.printStackTrace();
    }
    setPreferredSize(new Dimension(width, height));
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (image != null) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      Shape clip = new RoundRectangle2D.Float(0, 0, width, height, cornerRadius, cornerRadius);
      g2.setClip(clip);
      g2.drawImage(image, 0, 0, width, height, null);
      g2.dispose();
    } else {
      super.paintComponent(g);
    }
  }

  public void setCornerRadius(int radius) {
    this.cornerRadius = radius;
    repaint();
  }
}
