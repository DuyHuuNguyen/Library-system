package com.g15.library_system.mapper;

import com.g15.library_system.dto.returnBookDTOs.BorrowBookDTO;
import com.g15.library_system.dto.returnBookDTOs.ReturnBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.BookStatus;
import java.util.List;

public interface IReturnTransactionMapper {

  /**
   * Converts a list of ReturnBookDTO objects to a 2D Object array for table representation.
   *
   * @param returnBookDTOs the list of ReturnBookDTO objects to convert
   * @return a 2D Object array representing the data in the ReturnBookDTO objects
   */
  Object[][] toReturnBookTableData(List<ReturnBookDTO> returnBookDTOs);

  Object[][] toBorrowBookTableData(List<BorrowBookDTO> borrowBookDTOs);

  ReturnBookDTO toReturnBookDTO(Reader reader, Transaction transaction);

  BorrowBookDTO toBorrowBookDTO(Transaction transaction, Book book, int amount, BookStatus status);
}
