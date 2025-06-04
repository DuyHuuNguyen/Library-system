package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import lombok.Getter;

public class ReturnBookPanel extends JPanel {
  public static final String TABLE_PANEL = "tablePanel";
  public static final String ADD_RETURN_BOOK_PANEL = "addReturnBookPanel";

  @Getter private AddReturnBookPanel addReturnBookPanel;
  private ContentPanel mainContentPn;
  @Getter private ToolPanel toolPn;
  private CheckboxTablePanel tablePn;
  private CardLayout cardLayout;
  private String[] columnNames = {
    "",
    "Return ID",
    "Reader Name",
    "Reader Number",
    "Reader Email",
    "Return Date",
    "Returned Books",
    "Status",
    "Overdue Fine (VND)",
    "Processed By",
    "Notes"
  };
  private String[] statuses = {"return", "overdue"};

  // data
  private Object[][] tableData;

  public ReturnBookPanel() {
    cardLayout = new CardLayout(10, 10);
    this.setLayout(cardLayout);

    mainContentPn = new ContentPanel();
    this.add(mainContentPn, ReturnBookPanel.TABLE_PANEL);

    addReturnBookPanel = new AddReturnBookPanel();
    //    backToTableAction();
    this.add(addReturnBookPanel, ReturnBookPanel.ADD_RETURN_BOOK_PANEL);

    toolPn = new ToolPanel();
    mainContentPn.add(toolPn, BorderLayout.NORTH);
    mainContentPn.setAddBtListener();

    cardLayout.show(this, ReturnBookPanel.TABLE_PANEL);
    setCancelBtListener();
  }

  public void showPanel(String panelTitle) {
    this.cardLayout.show(this, panelTitle);
  }

  private class ContentPanel extends JPanel {
    public ContentPanel() {
      setLayout(new BorderLayout());

      tablePn = new CheckboxTablePanel(columnNames, tableData);
      tablePn.setStatuses(statuses);
      this.add(tablePn, BorderLayout.CENTER);
    }

    private void setAddBtListener() {
      toolPn.setAddReturnBookBtListener(e -> showPanel("addReturnBookPanel"));
    }
  }

  public String[] getColumnNames() {
    String[] excelColNames = new String[columnNames.length - 1];
    System.arraycopy(columnNames, 1, excelColNames, 0, columnNames.length - 1);
    return excelColNames;
  }

  public Object[][] getTableDataForExport() {
    if (tableData == null || tableData.length == 0 || tableData[0].length <= 1)
      return new Object[0][0];

    List<Integer> checkedRow = tablePn.getCheckedRows();

    Object[][] result = new Object[checkedRow.size()][tableData[0].length - 1];
    int index = 0;
    for (int i = 0; i < tableData.length; i++) {
      if (checkedRow.contains(i)) {
        System.arraycopy(tableData[i], 1, result[index], 0, tableData[i].length - 1);
        index++;
      }
    }

    return result;
  }

  public void setTableData(Object[][] tableData) {
    this.tableData = tableData;
    tablePn.setNewDataForTable(tableData);
  }

  public void setCancelBtListener() {
    addReturnBookPanel.setCancelBtListener(
        e -> {
          showPanel(ReturnBookPanel.TABLE_PANEL);
        });
  }

  public void setExportBtListener(ActionListener exportAction) {
    toolPn.setExportBtListener(exportAction);
  }
}
