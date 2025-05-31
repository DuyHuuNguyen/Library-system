package com.g15.library_system.service;

import com.g15.library_system.entity.Librarian;
import java.util.List;
import java.util.Optional;

public interface LibrarianService {
  Optional<Librarian> findByEmail(String email);

  List<Librarian> findAll();

  Optional<Librarian> findByName(String name);

  void save(Librarian newLibrarian);
}
