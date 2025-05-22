package com.g15.library_system.facade;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;

public interface TransactionFacade {
  Transaction createTransaction(Transaction transaction);

  void notifyBorrowTransaction(TransactionContentDTO transaction);
}
