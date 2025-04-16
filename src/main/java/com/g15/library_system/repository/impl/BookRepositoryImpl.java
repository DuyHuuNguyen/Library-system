package com.g15.library_system.repository.impl;

import com.g15.library_system.data.BookData;
import com.g15.library_system.entity.Book;
import com.g15.library_system.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
  private BookData bookData = BookData.getInstance();

  @Override
  public List<Book> findAll() {
    return bookData.getBooks();
  }

  @Override
  public Optional<Book> findById(long id) {
    return bookData.getBooks().stream().filter(book -> book.hasSameId(id)).findFirst();
  }
}
