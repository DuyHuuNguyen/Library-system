package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import java.util.Set;
import javax.swing.*;

public class ReturnBookPanel extends JPanel implements ContentAction {
  private AddReturnBookPanel addReturnBookPanel;
  private ContentPanel mainContentPn;

  private ToolPanel toolPn;
  private CheckboxTablePanel tablePn;
  private CardLayout cardLayout;

  private String[] columnNames = {
    "",
    "Return ID",
    "Reader Name",
    "Reader Number",
    "Return Date",
    "Returned Books",
    "Status",
    "Overdue Fee (VND)",
    "Processed By",
    "Notes"
  };
  private Object[][] tableData;

  public ReturnBookPanel() {
    cardLayout = new CardLayout(10, 10);
    this.setLayout(cardLayout);

    initData();
    mainContentPn = new ContentPanel();
    this.add(mainContentPn, "tablePanel");

    addReturnBookPanel = new AddReturnBookPanel();
    backToTableAction();
    this.add(addReturnBookPanel, "addReturnBookPanel");

    toolPn = new ToolPanel(this);
    mainContentPn.add(toolPn, BorderLayout.NORTH);
    mainContentPn.setAddBtListener();

    cardLayout.show(this, "tablePanel");
  }

  public void showPanel(String panelTitle) {
    cardLayout.show(this, panelTitle);
  }

  private class ContentPanel extends JPanel {
    public ContentPanel() {
      setLayout(new BorderLayout());

      tablePn = new CheckboxTablePanel(columnNames, tableData);
      tablePn.setEditableColumns(Set.of(4, 6, 7, 8, 9));
      this.add(tablePn, BorderLayout.CENTER);
    }

    private void setAddBtListener() {
      toolPn.setAddButtonListener(e -> showPanel("addReturnBookPanel"));
    }
  }

  public void initData() {
    tableData =
        new Object[][] {
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
          },
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Overdue", "5000", "Staff", ""},
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Overdue", "5000", "Staff", ""},
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Returned", "5000", "Staff", ""},
          {"R002", "Bob", "0123JQK", "2024-10-02", " ", "Damaged", "5000", "Staff", ""},
          {"R003", "Carol", "0123JQK", "2024-10-03", " ", "Returned", "0", "Admin", ""}
        };
  }

  @Override
  public Runnable editTable(CustomButton editButton) {
    return tablePn.getActionForEditingTable(editButton);
  }

  @Override
  public void exportExcel() {}

  @Override
  public void refreshTable() {}

  private void backToTableAction() {
    addReturnBookPanel.setListenerConfirmBt(
        e -> {
          int result =
              JOptionPane.showConfirmDialog(
                  JOptionPane.getFrameForComponent(this),
                  "Are you sure you want to add this book return for the reader?",
                  "Confirm Return",
                  JOptionPane.YES_NO_OPTION);

          if (result == JOptionPane.YES_OPTION) {
            showPanel("tablePanel");
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.SUCCESS,
                    ToastNotification.Location.TOP_CENTER,
                    "New book returned successfully!")
                .showNotification();
          }
        });
    addReturnBookPanel.setListenerCancelBt(e -> showPanel("tablePanel"));
  }
}
