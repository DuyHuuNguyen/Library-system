package com.g15.library_system.view.swingComponentGenerators;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class LabelGenerator {

  /**
   * create a simple JLabel
   *
   * @param text
   * @param font
   * @param textColor
   * @param textPosition
   * @return
   */
  public static JLabel createLabel(String text, Font font, Color textColor, int textPosition) {
    JLabel label = new JLabel(text);
    label.setForeground(textColor);
    label.setFont(font);
    label.setHorizontalAlignment(textPosition);
    return label;
  }

  public static JLabel createLabel(
      String text, Font font, Color textColor, int horizontal, int vertical) {
    JLabel label = new JLabel(text);
    label.setForeground(textColor);
    label.setFont(font);
    label.setHorizontalAlignment(horizontal); // the horizontal text position
    label.setVerticalAlignment(vertical); // the vertical text position
    return label;
  }

  /**
   * attach, insert icon or image to label
   *
   * @param label
   * @param iconPath
   * @param iconWidth
   * @param iconHeight
   */
  public static void setIcon(JLabel label, String iconPath, int iconWidth, int iconHeight) {
    try {
      ImageIcon icon = new ImageIcon(ImageIO.read(new File(iconPath)));
      Image image = icon.getImage();
      Image scaledImage = image.getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);

      label.setIcon(new ImageIcon(scaledImage));

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * create a new label and insert image to that label ( This label can only display image.)
   *
   * @param imagePath
   * @param width
   * @param height
   * @return
   */
  public static JLabel createImageLabel(String imagePath, int width, int height) {
    ImageIcon icon = new ImageIcon(imagePath);

    Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(image);

    JLabel label = new JLabel(resizedIcon);
    label.setPreferredSize(new Dimension(width, height));
    return label;
  }
}
