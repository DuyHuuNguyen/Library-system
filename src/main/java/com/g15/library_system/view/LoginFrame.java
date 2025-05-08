package com.g15.library_system.view;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.g15.library_system.view.loginView.LoginCardPanel;
import java.awt.*;
import javax.swing.*;

public class LoginFrame extends JFrame {
  private String backgroundImagePath = "/images/hogwarts.png";

  public LoginFrame() {
    this.setTitle("Library Management");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(1000, 700));
    this.setLocationRelativeTo(null);
    this.setLocationRelativeTo(null);
    this.setIconImage(new ImageIcon("src/main/resources/icons/libraryIconLogo.png").getImage());

    this.add(new LoginCardPanel(backgroundImagePath), BorderLayout.CENTER);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("raven.themes");
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    FlatMacLightLaf.setup();
    //    FlatMacDarkLaf.setup();
    UIManager.put("Separator.foreground", Color.WHITE);

    EventQueue.invokeLater(() -> new LoginFrame());
  }
}
