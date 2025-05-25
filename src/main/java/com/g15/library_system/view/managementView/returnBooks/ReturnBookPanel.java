package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.mapper.ITransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapper;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

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
  private String[] statuses = {"Returned", "Overdue"};

  // data
  @Setter private Object[][] tableData;
  //  private ReaderController readerController =
  //      ApplicationContextProvider.getBean(ReaderController.class);
  private ITransactionMapper transactionMapper = new TransactionMapper();
  private List<Reader> readersData = ReaderData.getInstance().getReaders();
  private List<ReturnBookDTO> returnBookDTOs = new ArrayList<>();

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
      tablePn.setEditableColumns(Set.of(5, 7, 8, 9, 10));
      tablePn.setStatuses(statuses);
      this.add(tablePn, BorderLayout.CENTER);
    }

    private void setAddBtListener() {
      toolPn.setAddButtonListener(e -> showPanel("addReturnBookPanel"));
    }
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
}
