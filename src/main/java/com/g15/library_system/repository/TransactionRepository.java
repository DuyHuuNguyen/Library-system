package com.g15.library_system.repository;

import com.g15.library_system.entity.Transaction;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {
  Optional<Transaction> findById(Long id);

  List<Transaction> findAll();

  Boolean save(Transaction transaction);
}
