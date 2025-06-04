package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.returnBookDTOs.BorrowBookDTO;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.mapper.IReturnTransactionMapper;
import com.g15.library_system.util.DateUtil;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;

public class ReturnTransactionMapper implements IReturnTransactionMapper {

  public ReturnTransactionMapper() {}

  @Override
  public Object[][] toBorrowBookTableData(List<BorrowBookDTO> borrowBookDTOs) {
    Object[][] tableData = new Object[borrowBookDTOs.size()][];
    for (BorrowBookDTO borrowBookDTO : borrowBookDTOs) {
      tableData[borrowBookDTOs.indexOf(borrowBookDTO)] =
          new Object[] {
            borrowBookDTO.getTransId(),
            borrowBookDTO.getBookId(),
            borrowBookDTO.getFirstCoverImage(),
            borrowBookDTO.getBookTitle(),
            borrowBookDTO.getBookQuantity(),
            borrowBookDTO.getBorrowDate(),
            borrowBookDTO.getDueDate(),
            borrowBookDTO.getReturnQuantity(),
            borrowBookDTO.getStatus().getStatus()
          };
    }

    return tableData;
  }

  @Override
  public BorrowBookDTO toBorrowBookDTO(
      Transaction transaction, Book book, int amount, BookStatus status) {
    return BorrowBookDTO.builder()
        .transId(transaction.getId())
        .bookId(book.getId())
        .firstCoverImage(new ImageIcon(getClass().getResource(book.getFirstImage())))
        .bookTitle(book.getTitle())
        .bookQuantity(amount)
        .borrowDate(DateUtil.convertToLocalDate(transaction.getCreatedAt()))
        .dueDate(DateUtil.convertToLocalDate(transaction.getExpectedReturnAt()))
        .returnQuantity(amount)
        .status(status)
        .build();
  }

  @Override
  public Object[][] toReturnBookTableData(List<ReturnBookDTO> returnBookDTOs) {
    Object[][] data = new Object[returnBookDTOs.size()][];
    for (int i = 0; i < data.length; i++) {
      var returnBookDTO = returnBookDTOs.get(i);
      data[i] =
          new Object[] {
            returnBookDTO.getTransactionId(),
            returnBookDTO.getReaderFullName(),
            returnBookDTO.getReaderPhoneNumber(),
            returnBookDTO.getReaderEmail(),
            returnBookDTO.getReturnDate(),
            returnBookDTO.getBooks().entrySet().stream()
                .map(entry -> entry.getKey().getTitle() + " x" + entry.getValue())
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
        .returnDate(DateUtil.convertToLocalDate(transaction.getActualReturnAt()))
        .books(transaction.getBooks())
        .status(
            transaction.getOverdueFine() != null
                ? BookStatus.OVERDUE.getStatus()
                : BookStatus.RETURNED.getStatus())
        .totalFine(
            transaction.getOverdueFine() != null
                ? String.valueOf(transaction.getOverdueFine().getPrice())
                : "0")
        .staffProcessed(
            transaction.getLibrarian() != null ? transaction.getLibrarian().getFullName() : "")
        .notes(transaction.getDescription())
        .build();
  }
}
