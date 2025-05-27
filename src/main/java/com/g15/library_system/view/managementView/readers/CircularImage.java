package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CircularImage extends JComponent {
  private static final Logger log = LoggerFactory.getLogger(CircularImage.class);
  private BufferedImage image;

  public CircularImage(String imagePath) {
    try {
      image = ImageIO.read(new File(imagePath));
      setPreferredSize(new Dimension(110, 110)); // Kích thước ảnh tròn
    } catch (Exception e) {
      //      e.printStackTrace();
      log.error("bug url ");
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (image == null) return;
    Graphics2D g2 = (Graphics2D) g.create();
    int diameter = Math.min(getWidth(), getHeight());
    BufferedImage mask = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
    Graphics2D gMask = mask.createGraphics();

    // Vẽ mặt nạ hình tròn
    gMask.fill(new Ellipse2D.Double(0, 0, diameter, diameter));
    gMask.setComposite(AlphaComposite.SrcIn);
    gMask.drawImage(image, 0, 0, diameter, diameter, null);
    gMask.dispose();

    g2.drawImage(mask, 0, 0, null);
    g2.dispose();
  }
}
