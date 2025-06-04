package com.g15.library_system.view.managementView.returnBooks.controllers;

import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.data.BookData;
import com.g15.library_system.data.CacheData;
import com.g15.library_system.data.ReaderData;
import com.g15.library_system.dto.returnBookDTOs.BorrowBookDTO;
import com.g15.library_system.entity.*;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.mapper.IReturnTransactionMapper;
import com.g15.library_system.mapper.impl.ReturnTransactionMapper;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.util.DateUtil;
import com.g15.library_system.util.TransactionIdGenerator;
import com.g15.library_system.view.managementView.returnBooks.AddReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.commands.CancelCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.ConfirmReturnCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.ReturnBookInvoker;
import com.g15.library_system.view.managementView.returnBooks.factories.*;
import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import com.g15.library_system.view.managementView.returnBooks.strategies.OverdueFineStrategy;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.time.LocalDate;
import java.util.*;
import javax.swing.*;

public class AddReturnBookController implements IAddReturnController {
  private AddReturnBookPanel addReturnBookPanel;
  private ReturnBookPanel returnBookPanel;
  private IReturnController returnManagementController;

  private IReturnTransactionMapper transactionMapper = new ReturnTransactionMapper();
  private Librarian currentLibrarian = CacheData.getCURRENT_LIBRARIAN();
  private Set<Book> books = BookData.getInstance().getBooks();
  private LocalDate today = LocalDate.now();
  // controller
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  // temp data
  private Reader readerFound;
  private List<Transaction> borrowTransactions = new ArrayList<>();

  // strategy factory
  private Map<FineStrategyType, IFineStrategyFactory> factories =
      Map.of(
          FineStrategyType.BOOK_AGE, new YearBasedFineFactory(),
          FineStrategyType.DAILY_FINE, new FixedDailyFineFactory(),
          FineStrategyType.PER_BOOK, new PerBookFineFactory(),
          FineStrategyType.MAX_FINE, new MaxFineLimitFactory(),
          FineStrategyType.NO_FINE, new NoFineFactory());

  // command
  private ReturnBookInvoker returnBookInvoker;

  public AddReturnBookController(
      IReturnController returnManagementController,
      ReturnBookPanel returnBookPanel,
      AddReturnBookPanel addReturnBookPanel) {
    this.returnManagementController = returnManagementController;
    this.addReturnBookPanel = addReturnBookPanel;
    this.returnBookPanel = returnBookPanel;

    this.returnBookInvoker = new ReturnBookInvoker();

    setupCancelBtListener();
    setupConfirmBtListener();
    setupStrategyComboBoxListener();
    setupSearchFieldListener();
    setupSearchFieldPopup();
    setupStaffLabel();
  }

  // process return book
  @Override
  public void processReturnTransaction() {
    Map<Book, Integer> returningBooks = new HashMap<>();
    Map<Book, Integer> lostBooks = new HashMap<>();
    List<Object[]> selectedRowsData = addReturnBookPanel.getSelectedRowsData();

    for (var rowData : selectedRowsData) {
      Long bookId = (Long) rowData[1];
      int quantity = Integer.parseInt(rowData[7].toString());
      BookStatus status = BookStatus.get((String) rowData[8]);

      Book book = findBookInTransaction(bookId).orElseThrow();

      if (status == BookStatus.LOST) {
        lostBooks.put(book, quantity);
        book.setCurrentQuantity(book.getCurrentQuantity() - quantity);
      } else {
        returningBooks.merge(book, quantity, Integer::sum);
      }
    }

    FineStrategyType selectedStrategy = addReturnBookPanel.getSelectedStrategy();

    Transaction returnTransaction =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .books(returningBooks)
            .libraryCard(readerFound.getLibraryCard())
            .transactionType(TransactionType.RETURNED)
            .createdAt(DateUtil.convertToEpochMilli(today))
            .actualReturnAt(DateUtil.convertToEpochMilli(today))
            .overdueFine(
                addReturnBookPanel.getStatusFieldText().equals("Overdue")
                    ? new OverdueFine(
                        Double.parseDouble(addReturnBookPanel.getLateFeeText()),
                        factories.get(selectedStrategy).createStrategy())
                    : null)
            .librarian(currentLibrarian)
            .description(addReturnBookPanel.getNotesText())
            .build();

    readerFound.getLibraryCard().addReturnTransaction(returnTransaction);

    updateBookCurrentQuantity(returningBooks);

    updateBorrowTransaction(selectedRowsData);

    //    System.out.println(returnTransaction);
    ReaderData.getInstance().notifyObservers();
  }

  @Override
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

  private void updateBookCurrentQuantity(Map<Book, Integer> returningBooks) {
    for (Map.Entry<Book, Integer> entry : returningBooks.entrySet()) {
      Book returnBook = entry.getKey();
      int quantity = entry.getValue();
      for (Book book : books) {
        if (book.getId().equals(returnBook.getId())) {
          book.setCurrentQuantity(book.getCurrentQuantity() + quantity);
          break;
        }
      }
    }
  }

  private void updateBorrowTransaction(List<Object[]> returningBooksData) {
    Map<Long, List<Object[]>> groupedByTransactionId = new HashMap<>();

    for (Object[] rowData : returningBooksData) {
      Long transactionId = Long.parseLong(rowData[0].toString());
      groupedByTransactionId.computeIfAbsent(transactionId, k -> new ArrayList<>()).add(rowData);
    }

    for (Map.Entry<Long, List<Object[]>> transactionGroup : groupedByTransactionId.entrySet()) {
      Long transactionId = transactionGroup.getKey();
      List<Object[]> booksForThisTransaction = transactionGroup.getValue();

      Transaction matchingBorrowTrans =
          borrowTransactions.stream()
              .filter(
                  trans -> trans.getId().equals(transactionId) && trans.getActualReturnAt() == null)
              .findFirst()
              .orElse(null);

      if (matchingBorrowTrans != null) {
        boolean allBooksReturned = true;

        for (Map.Entry<Book, Integer> borrowedEntry : matchingBorrowTrans.getBooks().entrySet()) {
          Book book = borrowedEntry.getKey();
          int borrowedQty = borrowedEntry.getValue();

          int totalReturnQty =
              booksForThisTransaction.stream()
                  .filter(rowData -> book.getId().equals(Long.parseLong(rowData[1].toString())))
                  .mapToInt(rowData -> Integer.parseInt(rowData[7].toString()))
                  .sum();

          if (totalReturnQty < borrowedQty) {
            allBooksReturned = false;
            break;
          }
        }

        if (allBooksReturned) {

          matchingBorrowTrans.setActualReturnAt(DateUtil.convertToEpochMilli(today));
        }
      }
    }
  }

  private Optional<Book> findBookInTransaction(Long bookId) {
    return borrowTransactions.stream()
        .flatMap(tx -> tx.getBooks().keySet().stream())
        .filter(book -> book.getId().equals(bookId))
        .findFirst();
  }

  private void mappingTableData() {
    List<BorrowBookDTO> borrowBookDTOs = new ArrayList<>();
    Map<Book, Integer> unreturnedBooks = readerFound.getLibraryCard().getUnreturnedBooks();

    for (Transaction transaction : borrowTransactions) {
      for (Map.Entry<Book, Integer> entry : transaction.getBooks().entrySet()) {
        Book book = entry.getKey();

        if (unreturnedBooks.containsKey(book)) {
          Integer remainingQuantity = unreturnedBooks.get(book);

          BookStatus status =
              DateUtil.convertToLocalDate(transaction.getExpectedReturnAt()).isAfter(today)
                  ? BookStatus.RETURNED
                  : BookStatus.OVERDUE;

          borrowBookDTOs.add(
              transactionMapper.toBorrowBookDTO(transaction, book, remainingQuantity, status));
        }
      }
    }

    Object[][] bookBorrowData = transactionMapper.toBorrowBookTableData(borrowBookDTOs);
    addReturnBookPanel.setNewBorrowDataForTable(bookBorrowData);
  }

  // setup all components action listeners in view
  private void setComboBoxAction() {
    double totalFine = 0;
    FineStrategyType selectedStrategy = addReturnBookPanel.getSelectedStrategy();

    if (selectedStrategy != null) {
      OverdueFineStrategy overdueFineStrategy = factories.get(selectedStrategy).createStrategy();

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
      List<Transaction> allTrans = reader.getLibraryCard().getBorrowTransactions();
      borrowTransactions.addAll(
          allTrans.stream()
              .filter(
                  tran ->
                      tran.getTransactionType() == TransactionType.BORROW
                          && tran.getActualReturnAt() == null)
              .toList());

      this.mappingTableData();

      addReturnBookPanel.setReaderFields(
          String.valueOf(reader.getId()),
          reader.getFullName(),
          reader.getEmail(),
          reader.getPhoneNumber());

      String status =
          borrowTransactions.stream()
                  .filter(tran -> tran.getActualReturnAt() == null)
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

  public void setupCancelBtListener() {
    addReturnBookPanel.setCancelBtListener(
        e -> {
          this.returnBookInvoker.setCommand(new CancelCommand(returnBookPanel, addReturnBookPanel));
          this.returnBookInvoker.executeCommand();
        });
  }

  public void setupConfirmBtListener() {
    addReturnBookPanel.setConfirmBtListener(
        e -> {
          this.returnBookInvoker.setCommand(
              new ConfirmReturnCommand(this, returnManagementController, addReturnBookPanel));
          this.returnBookInvoker.executeCommand();
        });
  }
}
