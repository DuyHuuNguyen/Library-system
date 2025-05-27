package com.g15.library_system.service.impl;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.repository.TransactionRepository;
import com.g15.library_system.service.TransactionService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;

  @Override
  public Transaction createTransaction(Transaction transaction) {
    return this.transactionRepository.save(transaction);
  }

  @Override
  public List<Transaction> findAll() {
    return this.transactionRepository.findAll();
  }
}
