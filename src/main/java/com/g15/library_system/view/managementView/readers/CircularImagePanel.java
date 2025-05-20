package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class CircularImagePanel extends JPanel {
  private BufferedImage image;

  public CircularImagePanel(String imagePath) {
    try {
      image = ImageIO.read(new File(imagePath));
      setPreferredSize(new Dimension(100, 100)); // kích thước khung
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      int diameter = Math.min(getWidth(), getHeight());
      BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
      Graphics2D g2 = mask.createGraphics();
      g2.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
      g2.setComposite(AlphaComposite.SrcIn);
      g2.drawImage(image, 0, 0, diameter, diameter, null);
      g2.dispose();

      g.drawImage(mask, 0, 0, null);
    }
  }
}
