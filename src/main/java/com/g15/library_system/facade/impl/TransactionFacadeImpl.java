package com.g15.library_system.facade.impl;

import com.g15.library_system.facade.TransactionFacade;
import com.g15.library_system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionFacadeImpl implements TransactionFacade {
  private final TransactionService transactionService;
}
