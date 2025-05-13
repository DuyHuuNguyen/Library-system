package com.g15.library_system.view.test;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class Main {
  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    FlatLaf.registerCustomDefaultsSource("raven.themes");
    javax.swing.SwingUtilities.invokeLater(() -> new MainView().setVisible(true));
  }
}
