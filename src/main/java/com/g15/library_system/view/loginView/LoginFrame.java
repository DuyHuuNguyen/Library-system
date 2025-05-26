package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
  private String backgroundImagePath = "/images/NLULibrary6.jpg";

  public LoginFrame() {
    this.setTitle("Library Management");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(800, 750));
    this.setLocationRelativeTo(null);
    this.setIconImage(new ImageIcon("src/main/resources/icons/libraryIconLogo.png").getImage());

    this.add(new LoginCardPanel(backgroundImagePath), BorderLayout.CENTER);
    this.setVisible(true);
  }
}
