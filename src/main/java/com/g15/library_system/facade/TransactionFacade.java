package com.g15.library_system.facade;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import java.util.List;

public interface TransactionFacade {
  Transaction createTransaction(Transaction transaction);

  void notifyBorrowTransaction(TransactionContentDTO transaction);

  List<Transaction> getByType(TransactionType transactionType);
}
