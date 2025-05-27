package com.g15.library_system.view.managementView.returnBooks.controllers;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.data.CacheData;
import com.g15.library_system.dto.returnBookDTOs.BorrowBookDTO;
import com.g15.library_system.entity.*;
import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.IReturnTransactionMapper;
import com.g15.library_system.mapper.impl.ReturnTransactionMapper;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.util.DateUtil;
import com.g15.library_system.util.TransactionIdGenerator;
import com.g15.library_system.view.managementView.returnBooks.AddReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.commands.CancelCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.ConfirmReturnCommand;
import com.g15.library_system.view.managementView.returnBooks.factories.*;
import com.g15.library_system.view.managementView.returnBooks.factories.simpleFactory.FineStrategyFactory;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public class AddReturnBookController {
  private AddReturnBookPanel addReturnBookPanel;
  private ReturnManagementController returnManagementController;

  private IReturnTransactionMapper transactionMapper = new ReturnTransactionMapper();
  private Librarian currentLibrarian = CacheData.getCURRENT_LIBRARIAN();

  // controller
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  // temp var
  private Reader readerFound;
  private List<Transaction> borrowTransactions = new ArrayList<>();
  private Map<Book, Integer> borrowingBooks = new HashMap<>();
  private LocalDate today = LocalDate.now();

  // strategy factory
  private IFineStrategyFactory factory;
  private List<IFineStrategyFactory> factories =
      List.of(
          new YearBasedFineFactory(),
          new FixedDailyFineFactory(),
          new PerBookFineFactory(),
          new MaxFineLimitFactory(),
          new NoFineFactory());

  // command
  private ConfirmReturnCommand confirmReturnCommand;
  private CancelCommand cancelCommand;

  public AddReturnBookController(
      ReturnManagementController returnManagementController,
      AddReturnBookPanel addReturnBookPanel) {
    this.returnManagementController = returnManagementController;
    this.addReturnBookPanel = addReturnBookPanel;

    this.confirmReturnCommand = new ConfirmReturnCommand(this, addReturnBookPanel);
    this.cancelCommand =
        new CancelCommand(returnManagementController.getReturnBookPanel(), addReturnBookPanel);

    setupCancelBtListener();
    setupConfirmBtListener();
    setupStrategyComboBoxListener();
    setupSearchFieldListener();
    setupSearchFieldPopup();
    setupStaffLabel();
  }

  // =========================== returning book
  public void processReturnTransaction() {
    Map<Book, Integer> returningBooks = new HashMap<>();
    Map<Book, Integer> lostBooks = new HashMap<>();

    // Ghi nhận sách được chọn để trả
    for (var rowData : addReturnBookPanel.getSelectedRowsData()) {
      Long bookId = (Long) rowData[0];
      int quantity = Integer.parseInt(rowData[3].toString());
      BookStatus status = BookStatus.get((String) rowData[6]);

      Book book =
          findBookInTransaction(bookId)
              .orElseThrow(
                  () ->
                      new IllegalStateException(
                          "Borrow transaction not found for book ID: " + bookId));

      if (status == BookStatus.LOST) {
        lostBooks.put(book, quantity);
        book.setCurrentQuantity(book.getCurrentQuantity() - quantity);
      } else {
        returningBooks.merge(book, quantity, Integer::sum);
      }
    }

    // Tạo transaction trả mới
    Transaction returnTransaction =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .books(returningBooks)
            .libraryCard(readerFound.getLibraryCard())
            .transactionType(TransactionType.RETURNED)
            .createdAt(DateUtil.convertToEpochMilli(today))
            .actualReturnAt(DateUtil.convertToEpochMilli(today))
            .overdueFine(
                new OverdueFine(
                    Double.parseDouble(addReturnBookPanel.getLateFeeText()),
                    FineStrategyFactory.createStrategy(addReturnBookPanel.getSelectedStrategy())))
            .librarian(currentLibrarian)
            .description(addReturnBookPanel.getNotesText())
            .build();

    readerFound.getLibraryCard().addReturnTransaction(returnTransaction);

    // check borrowTransaction -> set actualReturnAt
    for (Transaction borrowTx : readerFound.getLibraryCard().getBorrowTransactions()) {
      if (isAllBooksReturned(
          borrowTx, readerFound.getLibraryCard().getReturnTransactions(), returningBooks)) {
        borrowTx.setActualReturnAt(DateUtil.convertToEpochMilli(today));
      }
    }

    System.out.println(returnTransaction);
  }

  private boolean isAllBooksReturned(
      Transaction borrowTx,
      List<Transaction> returnTransactions,
      Map<Book, Integer> currentReturn) {
    Map<Book, Integer> totalReturned = new HashMap<>();

    for (Transaction returnTx : returnTransactions) {
      for (Map.Entry<Book, Integer> entry : returnTx.getBooks().entrySet()) {
        Book book = entry.getKey();
        int qty = entry.getValue();
        if (borrowTx.getBooks().containsKey(book)) {
          totalReturned.merge(book, qty, Integer::sum);
        }
      }
    }

    for (Map.Entry<Book, Integer> entry : currentReturn.entrySet()) {
      Book book = entry.getKey();
      if (borrowTx.getBooks().containsKey(book)) {
        totalReturned.merge(book, entry.getValue(), Integer::sum);
      }
    }

    for (Map.Entry<Book, Integer> entry : borrowTx.getBooks().entrySet()) {
      Book book = entry.getKey();
      int borrowedQty = entry.getValue();
      int returnedQty = totalReturned.getOrDefault(book, 0);

      if (returnedQty < borrowedQty) {
        return false;
      }
    }

    return true;
  }

  public boolean validateDataAndProcessReturn() {
    if (addReturnBookPanel.isSearchFieldEmpty(readerFound)) {
      return false;
    }
    if (addReturnBookPanel.hasNoBookSelected()) {
      return false;
    }

    int result =
        JOptionPane.showConfirmDialog(
            null, "Confirm returning these books?", "Confirm Return", JOptionPane.YES_NO_OPTION);

    if (result == JOptionPane.YES_OPTION) {
      try {
        this.processReturnTransaction();

        addReturnBookPanel.clearFormAndTableData();
        return true;
      } catch (Exception e) {
        System.out.println("Error processing return: " + e.getMessage());
        return false;
      }
    }

    return false;
  }

  private Optional<Book> findBookInTransaction(Long bookId) {
    return borrowTransactions.stream()
        .flatMap(tx -> tx.getBooks().keySet().stream())
        .filter(book -> book.getId().equals(bookId))
        .findFirst();
  }

  private void setComboBoxAction() {
    double totalFine = 0;
    FineStrategyType selectedStrategy = addReturnBookPanel.getSelectedStrategy();
    if (selectedStrategy != null) {
      for (IFineStrategyFactory fac : factories) {
        if (fac.getStrategyType() == selectedStrategy) {
          factory = fac;
          break;
        }
      }

      OverdueFineStrategy overdueFineStrategy = factory.createStrategy(selectedStrategy);

      for (Transaction transaction : borrowTransactions) {
        LocalDate expectedReturnDate =
            DateUtil.convertToLocalDate(transaction.getExpectedReturnAt());
        if (today.isAfter(expectedReturnDate)) {
          totalFine += overdueFineStrategy.calculateFine(transaction, today);
        }
      }

      addReturnBookPanel.setLateFeeText(String.valueOf(totalFine));
    }
  }

  // setup all components action listeners in view
  public void setupCancelBtListener() {
    addReturnBookPanel.setCancelBtListener(
        e -> {
          cancelCommand.execute();
        });
  }

  public void setupConfirmBtListener() {
    addReturnBookPanel.setConfirmBtListener(
        e -> {
          confirmReturnCommand.execute();
        });
  }

  public void refreshTable() {
    returnManagementController.refreshTable();
  }

  public void setupStrategyComboBoxListener() {
    addReturnBookPanel.setComboBoxListener(
        e -> {
          this.setComboBoxAction();
        });
  }

  public void setupSearchFieldListener() {
    addReturnBookPanel.setSearchFieldListener(
        e -> {
          setSearchFieldAction(addReturnBookPanel.getSearchFieldText());
        });
  }

  private void setupSearchFieldPopup() {
    addReturnBookPanel.setupSearchFieldPopupListener(
        name -> {
          return readerController.searchIdContains(name);
        },
        selectedName -> setSearchFieldAction(selectedName));
  }

  private void setSearchFieldAction(String searchName) {
    Reader reader = readerController.findById(searchName).orElse(null);

    if (reader != null && !searchName.isEmpty()) {
      readerFound = reader;

      borrowTransactions.clear();
      borrowingBooks.clear();
      List<Transaction> allTrans = reader.getLibraryCard().getBorrowTransactions();
      borrowTransactions.addAll(
          allTrans.stream()
              .filter(
                  tran ->
                      tran.getTransactionType() == TransactionType.BORROW
                          && tran.getActualReturnAt() == null)
              .toList());

      mappingTableData();

      addReturnBookPanel.setReaderFields(
          String.valueOf(reader.getId()),
          reader.getFullName(),
          reader.getEmail(),
          reader.getPhoneNumber());

      String status =
          borrowTransactions.stream()
                  .anyMatch(
                      tran ->
                          DateUtil.convertToLocalDate(tran.getExpectedReturnAt()).isBefore(today))
              ? "Overdue"
              : "On due date";
      addReturnBookPanel.setStatusFieldText(status);

      setComboBoxAction();
    } else {
      new ToastNotification(
              JOptionPane.getFrameForComponent(addReturnBookPanel),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.TOP_CENTER,
              "Reader not found!")
          .showNotification();
    }
  }

  private void setupStaffLabel() {
    addReturnBookPanel.setCurrentStaff(currentLibrarian.getFullName());
  }

  private void mappingTableData() {
    List<BorrowBookDTO> borrowBookDTOs = new ArrayList<>();
    Map<Book, Integer> unreturnedBooks = readerFound.getLibraryCard().getUnreturnedBooks();

    for (Transaction transaction : borrowTransactions) {
      for (Map.Entry<Book, Integer> entry : transaction.getBooks().entrySet()) {
        Book book = entry.getKey();

        if (unreturnedBooks.containsKey(book)) {
          Integer quantity = entry.getValue();
          BookStatus status =
              DateUtil.convertToLocalDate(transaction.getExpectedReturnAt()).isAfter(today)
                  ? BookStatus.RETURNED
                  : BookStatus.OVERDUE;

          borrowBookDTOs.add(
              transactionMapper.toBorrowBookDTO(transaction, book, quantity, status));
          borrowingBooks.put(book, borrowingBooks.getOrDefault(book, 0) + quantity);
        }
      }
    }

    Object[][] bookBorrowData = transactionMapper.toBorrowBookTableData(borrowBookDTOs);
    addReturnBookPanel.setNewBorrowDataForTable(bookBorrowData);
  }
}
