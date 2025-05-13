package com.g15.library_system.repository;

import com.g15.library_system.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository {
  List<Book> findAll();

  Optional<Book> findById(long id);

  void deleteById(long id);

  Optional<Book> findByTitle(String title);

  void save(Book newBook);
}
