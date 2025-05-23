package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DisplayImagePanel extends JPanel {
  private BufferedImage image;
  private String url;

  public DisplayImagePanel(int w, int h) {
    this.setPreferredSize(new Dimension(w, h));
    this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    this.url = "";
    this.removeImage();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    if (image != null) {
      g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
  }

  public void addImageToPanel(String imagePath) {
    try {
      if (!imagePath.contains(":")) {
        if (!imagePath.contains("src/main/resources/"))
          imagePath = "src/main/resources/" + imagePath;
      }
      image = ImageIO.read(new File(imagePath));
      this.repaint();
      this.revalidate();
      this.url = imagePath;
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void removeImage() {
    try {
      image = ImageIO.read(new File("src/main/resources/images/John_Doe.png"));
      this.url = "";
      this.repaint();
      this.revalidate();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String getPathImage() {
    return this.url;
  }
}
