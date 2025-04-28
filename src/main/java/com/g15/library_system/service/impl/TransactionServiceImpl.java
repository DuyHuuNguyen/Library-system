package com.g15.library_system.service.impl;

import com.g15.library_system.repository.TransactionRepository;
import com.g15.library_system.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
  private final TransactionRepository transactionRepository;
}
