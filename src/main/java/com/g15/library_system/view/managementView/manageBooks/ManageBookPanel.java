package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.overrideComponent.TablePanel;
import com.g15.library_system.view.overrideComponent.ToolbarTablePanel;
import java.awt.*;
import javax.swing.*;

public class ManageBookPanel extends JPanel {
  private ToolbarTablePanel toolbar;
  private TablePanel tablePanel;
  private String[] columns;

  public ManageBookPanel() {
    setLayout(new BorderLayout());

    this.columns =
        new String[] {
          "",
          "Book ID",
          "Book Title",
          "Author(s)",
          "Genre/Category",
          "Language",
          "Total Copies",
          "Status"
        };

    Object[][] data = {
      {
        !true,
        "ASP-BO-06",
        "The Catcher in the Rye",
        "John Peter",
        "Fiction",
        "English",
        5,
        "Damaged"
      },
      {!true, "ASP-BO-07", "The Alchemist", "Sara Jones", "Non-Fiction", "English", 10, "Lended"},
      {
        !true,
        "ASP-BO-08",
        "A Brief History of Time",
        "Will Turner",
        "Science",
        "English",
        20,
        "Lended"
      },
      {
        !true,
        "ASP-BO-09",
        "The Diary of a Young",
        "Dwayne Smith",
        "Memoir",
        "English",
        30,
        "Lended"
      },
      {
        true,
        "ASP-BO-10",
        "Ux-UI Design Book",
        "Anne Frank",
        "Visual Design",
        "English",
        50,
        "Lended"
      },
    };
    this.tablePanel = new TablePanel(columns, data, 1000, 1000);

    toolbar = new ToolbarTablePanel();
    //    toolbar.setBackground(new Color(52, 142, 154, 200));

    add(toolbar, BorderLayout.NORTH);
    add(new JScrollPane(tablePanel), BorderLayout.CENTER);
  }
}
