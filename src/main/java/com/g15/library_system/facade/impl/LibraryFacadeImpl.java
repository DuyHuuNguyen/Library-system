package com.g15.library_system.facade.impl;

import com.g15.library_system.facade.LibraryFacade;
import com.g15.library_system.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryFacadeImpl implements LibraryFacade {
  private final LibraryService libraryService;
}
