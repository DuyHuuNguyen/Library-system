package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.overrideComponent.labels.RoundedImageLabel;
import net.miginfocom.swing.MigLayout;
import java.awt.*;
import javax.swing.*;

public class BookCoverPanel extends JPanel {


    public BookCoverPanel(String imagePath, String title, String author, int imgWidth, int imgHeight) {
      this.setOpaque(false);
      this.setLayout(new MigLayout("wrap 1, align center",
              "[center]",
              "[]10[]5[]"));
      this.setPreferredSize(new Dimension(200, 300));

      RoundedImageLabel imageLabel = new RoundedImageLabel(imagePath, imgWidth, imgHeight);
      this.add(imageLabel, "center");

      JLabel titleLabel = new JLabel("<html><div style='text-align: center; width: " + imgWidth + "px;'>" + title + "</div></html>");
      titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
      this.add(titleLabel, "center");

      JLabel authorLabel = new JLabel(author);
      authorLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
      authorLabel.setForeground(Color.GRAY);
      this.add(authorLabel, "center");
    }
}
