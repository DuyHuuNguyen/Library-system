package com.g15.library_system.view.managementView.returnBooks.controllers;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.IReturnTransactionMapper;
import com.g15.library_system.mapper.impl.ReturnTransactionMapper;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.ToolPanel;
import com.g15.library_system.view.managementView.returnBooks.commands.ExportExcelCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.RefreshCommand;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import lombok.Getter;

public class ReturnManagementController implements IReturnController{
  // view
  @Getter private ReturnBookPanel returnBookPanel;
  private ToolPanel toolPn;

  // controller
  private AddReturnBookController returnBookController;

  // temp var
  private List<Transaction> borrowTransactions = new ArrayList<>();
  private Map<Book, Integer> borrowingBooks = new HashMap<>();

  private IReturnTransactionMapper transactionMapper = new ReturnTransactionMapper();
  private List<Reader> readersData = ReaderData.getInstance().getReaders();
  @Getter private List<ReturnBookDTO> returnBookDTOs = new ArrayList<>();

  // command
  private ExportExcelCommand exportExcelCommand;
  private RefreshCommand refreshCommand;

  public ReturnManagementController(ReturnBookPanel returnBookPanel) {
    this.returnBookPanel = returnBookPanel;
    this.toolPn = returnBookPanel.getToolPn();
    this.returnBookController =
        new AddReturnBookController(this,returnBookPanel, returnBookPanel.getAddReturnBookPanel());
    initTableData();
    this.refreshCommand = new RefreshCommand(this, returnBookPanel);
    this.exportExcelCommand = new ExportExcelCommand(returnBookPanel);

    setupFreshBtAction();
    setupExportBtAction();
    setupSearchAction();
  }

  @Override
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

  @Override
  public void refreshTable() {
    returnBookDTOs.clear();
    initTableData();
    returnBookPanel.setTableData(transactionMapper.toReturnBookTableData(returnBookDTOs));
    returnBookPanel.showPanel(ReturnBookPanel.TABLE_PANEL);
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

                      LocalDate returnDate = dto.getReturnDate();
                      String day = String.valueOf(returnDate.getDayOfMonth());
                      String month = String.valueOf(returnDate.getMonthValue());
                      String year = String.valueOf(returnDate.getYear());

                      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                      String formattedDate = returnDate.format(formatter).toLowerCase();

                      return formattedDate.contains(searchText)
                          || day.contains(searchText)
                          || month.contains(searchText)
                          || year.contains(searchText);

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

  public void setupFreshBtAction() {
    returnBookPanel.getToolPn().setRefreshBtListener(e -> this.refreshCommand.execute());
  }

  public void setupExportBtAction() {
    returnBookPanel.setExportBtListener(e -> exportExcelCommand.execute());
  }
}
