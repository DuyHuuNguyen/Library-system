package com.g15.library_system.repository;

import com.g15.library_system.entity.Librarian;
import java.util.Optional;

import java.util.List;

public interface LibrarianRepository {
  Optional<Librarian> findByEmail(String email);

    List<Librarian> findAll();

    Optional<Librarian> findByName(String name);

    void save(Librarian newLibrarian);

    void modify(Librarian librarian);

  List<Librarian> searchLibrarians(String keyword);
}
