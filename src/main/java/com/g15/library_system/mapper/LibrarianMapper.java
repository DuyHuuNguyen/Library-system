package com.g15.library_system.mapper;

import com.g15.library_system.dto.LibrarianExcelDTO;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.entity.User;

import java.util.List;

public interface LibrarianMapper {
  Librarian toLibrarian(Librarian librarian);
  Object[][] toLibrarianData(List<Librarian> librarian);

  LibrarianExcelDTO toLibrarianExcelDTO(Librarian librarian, long andIncrement);
}
