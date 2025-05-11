package com.g15.library_system.view.managementView.returnBooks;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;

import java.awt.*;
import javax.swing.*;

public class TestFrame extends JFrame {
  public TestFrame() {
    this.setTitle("Library Management");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(new Dimension(800, 750));
    this.setLocationRelativeTo(null);
    this.setLocationRelativeTo(null);
    this.setIconImage(new ImageIcon("src/main/resources/icons/libraryIconLogo.png").getImage());

    String[] columnNames = {
      "Return ID",
      "Reader Name",
      "Reader Number",
      "Return Date",
      "Returned Books",
      "Status",
      "Fine (VND)",
      "Processed By",
      "Notes"
    };

    Object[][] tableData = {
      {
        "R001",
        "Alice",
        "0123JQK",
        "2024-10-01",
        "To Kill a Mockingbird, War and Peace, Crime and Punishment, The Lord of the Rings",
        "Returned",
        "0",
        "Admin",
        ""
      },
      {
        "R002",
        "Bob",
        "0123JQK",
        "2024-10-02",
        "To Kill a Mockingbird",
        "Overdue",
        "5000",
        "Staff",
        ""
      },
      {
        "R002",
        "Bob",
        "0123JQK",
        "2024-10-02",
        "Crime and Punishment, The Lord of the Rings",
        "Overdue",
        "5000",
        "Staff",
        ""
      }
    };

//        this.add(new CheckboxTablePanel(columnNames, tableData), BorderLayout.CENTER);
    this.add(new ReturnBookPanel());
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

    new TestFrame();
  }
}
