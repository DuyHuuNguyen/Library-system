package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.ITransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapper;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

public class ReturnBookPanel extends JPanel implements ContentAction {
  public static final String TABLE_PANEL = "tablePanel";
  public static final String ADD_RETURN_BOOK_PANEL = "addReturnBookPanel";

  @Getter private AddReturnBookPanel addReturnBookPanel;
  private ContentPanel mainContentPn;
  private ToolPanel toolPn;
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

    toolPn = new ToolPanel(this);
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

  public void initData() {
    List<Transaction> transactions = new ArrayList<>();

    for (int i = 0; i < readersData.size(); i++) {
      try {
        Reader reader = readersData.get(i);
        readersData
            .get(i)
            .getLibraryCard()
            .getBorrowTransactions()
            .forEach(
                transaction -> {
                  if (transaction.getTransactionType() == TransactionType.RETURNED) {
                    returnBookDTOs.add(transactionMapper.toReturnBookDTO(reader, transaction));
                  }
                  if (transaction.getCreatedAt() == null) {
                    transactions.add(transaction);
                  }
                });
      } catch (NullPointerException e) {
        System.out.println("Reader " + i + " don't have data");
      }
    }

    tableData = transactionMapper.toReturnBookTableData(returnBookDTOs);
    tablePn.setNewDataForTable(tableData);
  }

  @Override
  public Runnable editTable(CustomButton editButton, CustomButton cancelButton) {
    return tablePn.getActionForEditingTable(editButton, cancelButton);
  }

  @Override
  public void exportExcel() {}

  @Override
  public void refreshTable() {
    returnBookDTOs.clear();
    //    initData();
    tablePn.setNewDataForTable(tableData);
  }

  public void setTableData(Object[][] tableData) {
    this.tableData = tableData;
    tablePn.setNewDataForTable(tableData);
  }

  public void setConfirmBtListener(ActionListener actionListener) {
    addReturnBookPanel.setConfirmBtListener(actionListener);
  }

  public void setCancelBtListener() {
    addReturnBookPanel.setCancelBtListener(
        e -> {
          showPanel(ReturnBookPanel.TABLE_PANEL);
        });
  }
}
