package com.g15.library_system.data;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.observers.TransactionObserver;
import com.g15.library_system.observers.TransactionSubject;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class TransactionData implements Data<Transaction>, TransactionSubject {
  private static final TransactionData INSTANCE = new TransactionData();
  private final List<Transaction> transactions = new ArrayList<>();
  private List<TransactionObserver> observers = new ArrayList<>();

  private TransactionData() {
    this.initializeData();
  }

  public static TransactionData getInstance() {
    return INSTANCE;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  @Override
  public void add(Transaction transaction) {
    this.transactions.add(transaction);
  }

  @Override
  public void add(List<Transaction> t) {
    this.transactions.addAll(t);
  }

  @Override
  public void remove(Transaction transaction) {}

  @Override
  public void remove(int index) {}

  @Override
  public void registerObserver(TransactionObserver o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(TransactionObserver o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (TransactionObserver o : observers) {
      o.update();
    }
  }

  private void initializeData() {
    List<Transaction> transactions =
        List.of(
            Transaction.builder()
                .id(1L)
                .books(
                    List.of(
                        BookData.getInstance().getBooks().get(2),
                        BookData.getInstance().getBooks().get(3)))
                .librarian(LibrarianData.getInstance().getLibrarians().get(0))
                .expectedReturnAt(1726214400000L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 9, 1)))
                .actualReturnAt(1726822800000L)
                .transactionType(TransactionType.BORROW)
                .build(),
            Transaction.builder()
                .id(2L)
                .books(
                    List.of(
                        BookData.getInstance().getBooks().get(0),
                        BookData.getInstance().getBooks().get(1)))
                .librarian(LibrarianData.getInstance().getLibrarians().get(1))
                .expectedReturnAt(1726300800000L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 8, 1)))
                .actualReturnAt(1726909200000L)
                .transactionType(TransactionType.BORROW)
                .build(),
            Transaction.builder()
                .id(3L)
                .books(List.of(BookData.getInstance().getBooks().get(4)))
                .librarian(LibrarianData.getInstance().getLibrarians().get(0))
                .expectedReturnAt(1726387200000L)
                .actualReturnAt(0L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 9, 1)))
                .transactionType(TransactionType.BORROW)
                .build());
    this.transactions.addAll(transactions);
  }
}
