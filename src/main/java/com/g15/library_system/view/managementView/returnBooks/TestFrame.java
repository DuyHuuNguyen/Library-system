package com.g15.library_system.view.managementView.returnBooks;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.g15.library_system.LibrarySystemApplication;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.returnBooks.controllers.ReturnManagementController;
import java.awt.*;
import javax.swing.*;
import lombok.Getter;
import org.springframework.boot.SpringApplication;

public class TestFrame extends JFrame {
  @Getter public ReturnBookPanel returnBookPanel = new ReturnBookPanel();

  public TestFrame() {
    this.setTitle("Library Management");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(800, 750));
    this.setLocationRelativeTo(null);
    this.setLocationRelativeTo(null);
    this.setIconImage(new ImageIcon("src/main/resources/icons/libraryIconLogo.png").getImage());

    this.add(returnBookPanel);
    this.setVisible(true);
  }

  public static void main(String[] args) {
    FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("raven.themes");
    UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
    FlatMacLightLaf.setup();
    UIManager.put("Separator.foreground", Color.WHITE);
    UIManager.put(
        "TableHeader.cellBorder",
        BorderFactory.createLineBorder(Style.BLUE_HEADER_TABLE_AND_BUTTON));

    SpringApplication app = new SpringApplication(LibrarySystemApplication.class);
    app.setHeadless(false);
    app.run(args);
    //    new ReturnBookController();

    TestFrame testFrame = new TestFrame();

    ReturnManagementController returnManagementController =
        new ReturnManagementController(testFrame.getReturnBookPanel());
  }
}
