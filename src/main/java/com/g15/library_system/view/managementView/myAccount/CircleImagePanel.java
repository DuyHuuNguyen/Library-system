package com.g15.library_system.view.managementView.myAccount;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class CircleImagePanel extends JPanel {
  private BufferedImage image;

  public CircleImagePanel(BufferedImage img) {
    this.image = img;
    setPreferredSize(new Dimension(200, 200));
  }

  public void setImage(BufferedImage img) {
    this.image = img;
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (image != null) {
      int diameter = Math.min(getWidth(), getHeight());
      BufferedImage clipped = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);

      Graphics2D g2 = clipped.createGraphics();
      g2.setClip(new Ellipse2D.Float(0, 0, diameter, diameter));
      g2.drawImage(image, 0, 0, diameter, diameter, null);
      g2.dispose();

      g.drawImage(clipped, (getWidth() - diameter) / 2, (getHeight() - diameter) / 2, null);
    }
  }
}
