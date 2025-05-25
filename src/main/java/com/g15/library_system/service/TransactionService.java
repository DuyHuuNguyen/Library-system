package com.g15.library_system.service;

import com.g15.library_system.entity.Transaction;

import java.util.List;

public interface TransactionService {
  Transaction createTransaction(Transaction transaction);

    List<Transaction> findAll();
}
