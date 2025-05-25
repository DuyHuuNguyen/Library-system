package com.g15.library_system.view.managementView.returnBooks.controllers;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.ITransactionMapper;
import com.g15.library_system.mapper.impl.TransactionMapper;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.TestFrame;
import com.g15.library_system.view.managementView.returnBooks.ToolPanel;
import com.g15.library_system.view.managementView.returnBooks.commands.RefreshCommand;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;

import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import lombok.Getter;

public class ReturnManagementController {
  // view
  private ReturnBookPanel returnBookPanel;
  private ToolPanel toolPn;
  private TestFrame testFrame;

  // controller
  private ReturnBookController returnBookController;

  // temp var
  private Reader readerFound;
  private List<Transaction> borrowTransactions = new ArrayList<>();
  private Map<Book, Integer> borrowingBooks = new HashMap<>();

  private ITransactionMapper transactionMapper = new TransactionMapper();
  private List<Reader> readersData = ReaderData.getInstance().getReaders();
  @Getter private List<ReturnBookDTO> returnBookDTOs = new ArrayList<>();

  // command
  private final RefreshCommand refreshCommand;

  public ReturnManagementController(ReturnBookPanel returnBookPanel) {
    this.returnBookPanel = returnBookPanel;
    this.toolPn = returnBookPanel.getToolPn();
    this.returnBookController =
        new ReturnBookController(this, returnBookPanel.getAddReturnBookPanel());
    initTableData();
    this.refreshCommand = new RefreshCommand(this, returnBookPanel, transactionMapper);
    setupFreshBtAction();
    setupSearchAction();
  }

  public void initTableData() {
    for (Reader reader : readersData) {
      try {
        if (reader.getLibraryCard() == null) {
          continue;
        }

        reader.getLibraryCard().getReturnTransactions().stream()
            .filter(transaction -> transaction.getTransactionType() == TransactionType.RETURNED)
            .forEach(
                transaction ->
                    returnBookDTOs.add(transactionMapper.toReturnBookDTO(reader, transaction)));

      } catch (NullPointerException e) {
        System.out.println("Reader " + reader.getId() + " doesn't have library card data");
      }
    }
    returnBookPanel.setTableData(transactionMapper.toReturnBookTableData(returnBookDTOs));
  }

  public void refreshTable() {
    refreshCommand.execute();
  }

  public void setupFreshBtAction() {
    returnBookPanel
        .getToolPn()
        .setFreshButtonListener(
            e -> {
              refreshTable();
              new ToastNotification(
                      JOptionPane.getFrameForComponent(returnBookPanel),
                      ToastNotification.Type.INFO,
                      ToastNotification.Location.TOP_CENTER,
                      "Refreshing....")
                  .showNotification();
            });
  }

  public Object[][] searchReturnBooks(String searchText, String searchOption) {
    List<ReturnBookDTO> filteredList =
        returnBookDTOs.stream()
            .filter(
                dto -> {
                  switch (searchOption) {
                    case "Name":
                      return dto.getReaderFullName().toLowerCase().contains(searchText);
                    case "Tel":
                      return dto.getReaderPhoneNumber().toLowerCase().contains(searchText);
                    case "Email":
                      return dto.getReaderEmail().toLowerCase().contains(searchText);
                    case "Return Date":
                      if (dto.getReturnDate() == null) return false;

                      String day = String.valueOf(dto.getReturnDate().getDayOfMonth());  // "25"
                      String month = String.valueOf(dto.getReturnDate().getMonthValue()); // "5"
                      String year = String.valueOf(dto.getReturnDate().getYear()); // "2025"

                      return day.contains(searchText) || month.contains(searchText) || year.contains(searchText);

                    case "Staff":
                      return dto.getStaffProcessed().toLowerCase().contains(searchText);
                    default:
                      return false;
                  }
                })
            .toList();

    return transactionMapper.toReturnBookTableData(filteredList);
  }

  public void setupSearchAction() {
    returnBookPanel
        .getToolPn()
        .setSearchTxtActionListener(
            e -> {
              String searchText = returnBookPanel.getToolPn().getSearchTextLowerCase();
              String selectedOption = returnBookPanel.getToolPn().getSelectedSearchOption();

              Object[][] resultData;

              if (searchText.isEmpty()) {

                resultData = transactionMapper.toReturnBookTableData(returnBookDTOs);
              } else {

                resultData = searchReturnBooks(searchText, selectedOption);
              }

              returnBookPanel.setTableData(resultData);
            });
  }
}
