package com.g15.library_system.facade.impl;

import com.g15.library_system.facade.BookFacade;
import com.g15.library_system.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {
  private final BookService bookService;
}
