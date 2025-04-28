package com.g15.library_system.facade.impl;

import com.g15.library_system.facade.LibrarianFacade;
import com.g15.library_system.service.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianFacadeImpl implements LibrarianFacade {
  private final LibrarianService librarianService;
}
