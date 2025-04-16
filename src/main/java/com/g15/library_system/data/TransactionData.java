package com.g15.library_system.data;

import com.g15.library_system.entity.Transaction;
import java.util.ArrayList;
import java.util.List;

public class TransactionData implements Data<Transaction> {
  private static final TransactionData INSTANCE = new TransactionData();
  private final List<Transaction> transactions = new ArrayList<>();

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

  private void initializeData() {}
}
