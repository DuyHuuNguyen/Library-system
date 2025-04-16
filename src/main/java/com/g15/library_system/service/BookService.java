package com.g15.library_system.service;

import com.g15.library_system.entity.Book;
import java.util.Optional;

public interface BookService {
  Optional<Book> findById(long id);
}
