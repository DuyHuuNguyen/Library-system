package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.TransactionContentDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.mapper.TransactionMapper;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapperImpl implements TransactionMapper {

  @Override
  public TransactionContentDTO convertToContentDTO(Transaction transaction) {
    Map<String, Integer> bookNameToQuantity = null;
    if (transaction.getBooks() != null) {
      bookNameToQuantity =
          transaction.getBooks().entrySet().stream()
              .collect(Collectors.toMap(entry -> entry.getKey().getTitle(), Map.Entry::getValue));
    }

    return TransactionContentDTO.builder()
        .id(transaction.getId())
        .books(bookNameToQuantity)
        .librarianName(
            transaction.getLibrarian() != null ? transaction.getLibrarian().getFullName() : null)
        .libraryCardId(transaction.getLibraryCardId())
        .readerName(
            transaction.getLibraryCard() != null
                ? transaction.getLibraryCard().getOwner().getFullName()
                : null)
        .readerEmail(
            transaction.getLibraryCard() != null
                ? transaction.getLibraryCard().getOwner().getEmail()
                : null)
        .expectedReturnAt(transaction.getExpectedReturnAt())
        .actualReturnAt(transaction.getActualReturnAt())
        .description(transaction.getDescription())
        .transactionType(transaction.getTransactionType())
        .returnStatus(transaction.getReturnStatus())
        .overdueFeeAmount(
            transaction.getOverdueFee() != null
                ? Double.valueOf(transaction.getOverdueFee().getPrice())
                : null)
        .build();
  }

  @Override
  public Object[][] toTransactionBorrowData(List<Transaction> transactions) {
    if (transactions == null) return null;
    Object[][] data = new Object[transactions.size()][];
    int i = 0;
    for (Transaction transaction : transactions) {
      data[i] =
              new Object[] {
                      transaction.getId(),
                      transaction.getLibraryCard() != null
                              ? transaction.getLibraryCard().getOwner().getFullName()
                              : null,
                      java.time.Instant
                              .ofEpochMilli(transaction.getCreatedAt())
                              .atZone(java.time.ZoneId.systemDefault())
                              .toLocalDate(),
                      java.time.Instant
                              .ofEpochMilli(transaction.getExpectedReturnAt())
                              .atZone(java.time.ZoneId.systemDefault())
                              .toLocalDate(),
                      transaction.getDescription()
              };
      i++;
    }
    return data;
  }
}
