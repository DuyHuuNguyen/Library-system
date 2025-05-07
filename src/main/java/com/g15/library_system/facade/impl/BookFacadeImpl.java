package com.g15.library_system.facade.impl;

import com.g15.library_system.entity.Book;
import com.g15.library_system.facade.BookFacade;
import com.g15.library_system.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {
  private final BookService bookService;

  @Override
  public List<String> searchTitleContains(String title) {
    return bookService.findAll().stream()
        .filter(book -> book.titleContains(title))
        .map(Book::getTitle)
        .toList();
  }

  @Override
  public Optional<Book> findByTitle(String title) {
    return bookService.findByTitle(title);
  }
}
