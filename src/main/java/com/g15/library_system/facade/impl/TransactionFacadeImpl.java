package com.g15.library_system.facade.impl;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.facade.TransactionFacade;
import com.g15.library_system.service.EmailProducerService;
import com.g15.library_system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {
  private final TransactionService transactionService;
  private final EmailProducerService emailProducerService;

  @Override
  public Transaction createTransaction(Transaction transaction) {
    return this.transactionService.createTransaction(transaction);
  }

  @Override
  public void notifyBorrowTransaction(TransactionContentDTO transaction) {
    this.emailProducerService.send(transaction);
  }
}
