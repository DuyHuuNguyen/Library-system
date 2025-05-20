package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.ReturnBookDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.mapper.TransactionMapper;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionMapperImpl implements TransactionMapper {

  public TransactionMapperImpl() {}

  @Override
  public Object[][] toReturnBookTableData(List<ReturnBookDTO> returnBookDTOs) {
    Object[][] data = new Object[returnBookDTOs.size()][];
    for (int i = 0; i < data.length; i++) {
      var returnBookDTO = returnBookDTOs.get(i);
      data[i] =
          new Object[] {
            returnBookDTO.getTransactionId(),
            returnBookDTO.getReaderId(),
            returnBookDTO.getReaderFullName(),
            returnBookDTO.getReaderPhoneNumber(),
            returnBookDTO.getReaderEmail(),
            returnBookDTO.getReturnDate(),
            returnBookDTO.getBooks().entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ")),
            returnBookDTO.getStatus(),
            returnBookDTO.getTotalFine(),
            returnBookDTO.getStaffProcessed(),
            returnBookDTO.getNotes()
          };
    }
    return data;
  }

  @Override
  public ReturnBookDTO toReturnBookDTO(Reader reader, Transaction transaction) {
    return ReturnBookDTO.builder()
        .transactionId(transaction.getId())
        .readerId(reader.getId())
        .readerFullName(reader.getFirstName() + " " + reader.getLastName())
        .readerPhoneNumber(reader.getPhoneNumber())
        .readerEmail(reader.getEmail())
        .returnDate(transaction.getCreatedAt().toString())
        .books(transaction.getBooks())
        .status(transaction.getTransactionType().toString())
        .totalFine(
            transaction.getOverdueFee() != null
                ? transaction.getOverdueFee().getPrice().toString()
                : "0")
        .staffProcessed(
            transaction.getLibrarian() != null ? transaction.getLibrarian().getFullName() : "")
        .notes(transaction.getDescription())
        .build();
  }
}
