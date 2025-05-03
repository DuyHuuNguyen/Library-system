package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.Set;
import javax.swing.*;

public class ContentPanel extends JPanel implements ContentAction {
  private CheckboxTablePanel tablePanel;

  private String[] columnNames = {
    "",
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
  private Object[][] tableData;

  public ContentPanel() {
    setLayout(new BorderLayout());
    // data demo
    Object[][] tableData = {
      {
        false,
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
        false,
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
        false,
        "R002",
        "Bob",
        "0123JQK",
        "2024-10-02",
        "Crime and Punishment, The Lord of the Rings",
        "Overdue",
        "5000",
        "Staff",
        ""
      },
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Overdue", "5000", "Staff", ""},
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Overdue", "5000", "Staff", ""},
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
      {false, "R002", "Bob", "0123JQK", "2024-10-02", " ", "Damaged", "5000", "Staff", ""},
      {false, "R003", "Carol", "0123JQK", "2024-10-03", " ", "Returned", "0", "Admin", ""}
    };
    tablePanel = new CheckboxTablePanel(columnNames, tableData);
    tablePanel.setEditableColumns(Set.of(4, 6, 7, 8, 9));
    this.add(tablePanel, BorderLayout.CENTER);
  }

  @Override
  public Runnable editTable(CustomButton editButton) {
    return tablePanel.getActionForEditingTable(editButton);
  }

  @Override
  public void exportExcel() {}

  @Override
  public void refreshTable() {}
}
