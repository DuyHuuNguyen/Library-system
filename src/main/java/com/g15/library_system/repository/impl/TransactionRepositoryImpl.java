package com.g15.library_system.repository.impl;

import com.g15.library_system.data.TransactionData;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.repository.TransactionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
  private List<Transaction> transactions = TransactionData.getInstance().getTransactions();

  @Override
  public Optional<Transaction> findById(Long id) {
    return transactions.stream().filter(transaction -> transaction.findById(id)).findFirst();
  }

  @Override
  public List<Transaction> findAll() {
    return transactions;
  }

  @Override
  public Boolean save(Transaction transaction) {
    return transactions.add(transaction);
  }
}
