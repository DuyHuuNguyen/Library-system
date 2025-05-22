package com.g15.library_system.repository.impl;

import com.g15.library_system.data.LibrarianData;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.repository.LibrarianRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class LibrarianRepositoryImpl implements LibrarianRepository {
  private LibrarianData librarianData = LibrarianData.getInstance();

  @Override
  public Optional<Librarian> findByEmail(String email) {
    return librarianData.getLibrarians().stream()
        .filter(librarian -> librarian.isSameEmail(email))
        .findFirst();
  }
}
