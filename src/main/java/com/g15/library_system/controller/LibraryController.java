package com.g15.library_system.controller;

import com.g15.library_system.facade.LibraryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LibraryController {
  private final LibraryFacade libraryFacade;
}
