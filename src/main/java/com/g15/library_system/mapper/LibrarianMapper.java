package com.g15.library_system.mapper;

import com.g15.library_system.dto.LibrarianExcelDTO;
import com.g15.library_system.entity.Librarian;

public interface LibrarianMapper {

  LibrarianExcelDTO toLibrarianExcelDTO(Librarian librarian, long andIncrement);
}
