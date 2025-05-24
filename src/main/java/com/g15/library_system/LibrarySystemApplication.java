package com.g15.library_system;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.g15.library_system.view.loginView.LoginFrame;
import java.awt.*;
import javax.swing.*;

import com.g15.library_system.view.managementView.returnBooks.controllers.ReturnBookController;
import com.g15.library_system.view.managementView.returnBooks.controllers.ReturnManagementController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class LibrarySystemApplication {
  public static void main(String[] args) {
    // improve Swing UI
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("raven.themes");
    UIManager.put(
        "defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13)); // set default font
    FlatMacLightLaf.setup(); // light mode

    SpringApplication app = new SpringApplication(LibrarySystemApplication.class);
    app.setHeadless(false);
    app.run(args);
    var login = new LoginFrame();


    login.setVisible(true);
  }
}
