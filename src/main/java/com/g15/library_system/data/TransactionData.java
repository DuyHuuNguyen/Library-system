package com.g15.library_system.data;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.observers.TransactionObserver;
import com.g15.library_system.observers.TransactionSubject;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class TransactionData implements Data<Transaction>, TransactionSubject {
  private static final TransactionData INSTANCE = new TransactionData();
  private List<TransactionObserver> observers = new ArrayList<>();
  private final List<Transaction> transactions;

  private TransactionData() {
    transactions = ReaderData.getInstance().getBorrowTransactions();
  }

  public static TransactionData getInstance() {
    return INSTANCE;
  }

  @Override
  public void registerObserver(TransactionObserver o) {
    this.observers.add(o);
  }

  @Override
  public void removeObserver(TransactionObserver o) {
    this.observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (TransactionObserver observer : observers) {
      observer.updateTransactionData();
    }
  }

  @Override
  public void add(Transaction transaction) {
    this.transactions.add(transaction);
    notifyObservers();
  }

  @Override
  public void add(List<Transaction> t) {
    this.transactions.addAll(t);
    notifyObservers();
  }

  @Override
  public void remove(Transaction transaction) {
    this.transactions.remove(transaction);
    notifyObservers();
  }

  @Override
  public void remove(int index) {
    try {
      this.transactions.remove(index);
      notifyObservers();
    } catch (IndexOutOfBoundsException e) {
      log.error("Failed to remove transaction at index", e);
    }
  }
}
