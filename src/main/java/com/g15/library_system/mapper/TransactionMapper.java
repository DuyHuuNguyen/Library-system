package com.g15.library_system.mapper;

import com.g15.library_system.dto.ReturnBookDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import java.util.List;

public interface TransactionMapper {

  /**
   * Converts a list of ReturnBookDTO objects to a 2D Object array for table representation.
   *
   * @param returnBookDTOs the list of ReturnBookDTO objects to convert
   * @return a 2D Object array representing the data in the ReturnBookDTO objects
   */
  Object[][] toReturnBookTableData(List<ReturnBookDTO> returnBookDTOs);

  ReturnBookDTO toReturnBookDTO(Reader reader, Transaction transaction);
}
