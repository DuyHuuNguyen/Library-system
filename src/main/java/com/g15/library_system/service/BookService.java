package com.g15.library_system.service;

import com.g15.library_system.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService {
  Optional<Book> findById(long id);

  List<Book> findAll();

  void deleteById(long id);

  Optional<Book> findByTitle(String selectedTitle);

  void save(Book newBook);

  void save(List<Book> books);
}
