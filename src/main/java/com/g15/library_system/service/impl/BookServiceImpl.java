package com.g15.library_system.service.impl;

import com.g15.library_system.entity.Book;
import com.g15.library_system.repository.BookRepository;
import com.g15.library_system.service.BookService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;

  @Override
  public Optional<Book> findById(long id) {
    return bookRepository.findById(id);
  }

  @Override
  public List<Book> finAll() {
    return bookRepository.findAll();
  }

  @Override
  public void deleteById(long id) {
    this.bookRepository.deleteById(id);
  }
}
