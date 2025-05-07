package com.g15.library_system.controller;

import com.g15.library_system.entity.Book;
import com.g15.library_system.facade.BookFacade;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {
  private final BookFacade bookFacade;

  public List<String> searchTitleContains(String title) {
    return bookFacade.searchTitleContains(title);
  }

  public Optional<Book> findByTitle(String title) {
    return bookFacade.findByTitle(title);
  }
}
