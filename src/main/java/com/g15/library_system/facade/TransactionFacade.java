package com.g15.library_system.facade;

import com.g15.library_system.entity.Transaction;

public interface TransactionFacade {
  Boolean createTransaction(Transaction transaction);
}
