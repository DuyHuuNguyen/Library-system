package com.g15.library_system.repository.impl;

import com.g15.library_system.data.TransactionData;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
  private TransactionData transactionData = TransactionData.getInstance();

  @Override
  public Optional<Transaction> findById(Long id) {
    return transactionData.getTransactions().stream()
        .filter(transaction -> transaction.findById(id))
        .findFirst();
  }

  @Override
  public List<Transaction> findAll() {
    return transactionData.getTransactions();
  }

  @Override
  public Transaction save(Transaction transaction) {
    transaction.setId(transactionData.getTransactions().getLast().getId() + 1);
    transactionData.add(transaction);
    return transaction;
  }
}
