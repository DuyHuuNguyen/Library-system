package com.g15.library_system.controller;

import com.g15.library_system.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {
  private final BookFacade bookFacade;
}
