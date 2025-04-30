package com.g15.library_system.controller;

import com.g15.library_system.facade.LibrarianFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LibrarianController {
  private final LibrarianFacade librarianFacade;
}
