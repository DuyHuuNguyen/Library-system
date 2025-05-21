package com.g15.library_system.mapper;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Transaction;

public interface TransactionMapper {
  TransactionContentDTO convertToContentDTO(Transaction transaction);
}
