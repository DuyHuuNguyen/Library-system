package com.g15.library_system.mapper;

import com.g15.library_system.dto.ReturnBookDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import java.util.List;

public class ReturnBookMapperImpl implements ReturnBookMapper {

  public ReturnBookMapperImpl() {}

  @Override
  public Object[][] toReturnBookTableData(List<ReturnBookDTO> returnBookDTOs) {
    Object[][] data = new Object[returnBookDTOs.size()][];
    for (int i = 0; i < data.length; i++) {
      var returnBook = returnBookDTOs.get(i);
      data[i] =
          new Object[] {
            returnBook.getTransactionId(),
            returnBook.getReaderId(),
            returnBook.getReaderFullName(),
            returnBook.getReaderPhoneNumber(),
            returnBook.getReaderEmail(),
            returnBook.getReturnDate(),
            returnBook.getBooks().stream().map(book -> book.getTitle()).toList(),
            returnBook.getStatus(),
            returnBook.getTotalFine(),
            returnBook.getStaffProcessed(),
            returnBook.getNotes()
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
        //        .books(transaction.getBooks().entrySet())
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
