package com.g15.library_system.controller;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.facade.TransactionFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TransactionController {
  private final TransactionFacade transactionFacade;

  public Boolean createTransaction(Transaction transaction) {
    return this.transactionFacade.createTransaction(transaction);
  }
}
