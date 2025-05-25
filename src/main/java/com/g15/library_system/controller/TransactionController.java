package com.g15.library_system.controller;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.facade.TransactionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionFacade transactionFacade;

  public Transaction createTransaction(Transaction transaction) {
    return this.transactionFacade.createTransaction(transaction);
  }

  public void notifyBorrowTransaction(TransactionContentDTO transaction) {
    this.transactionFacade.notifyBorrowTransaction(transaction);
  }

  public List<Transaction> getByType(TransactionType transactionType) {
    return this.transactionFacade.getByType(transactionType);
  }
}
