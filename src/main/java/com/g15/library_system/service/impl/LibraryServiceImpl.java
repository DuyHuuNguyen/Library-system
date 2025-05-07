package com.g15.library_system.service.impl;

import com.g15.library_system.repository.LibraryRepository;
import com.g15.library_system.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {
  private final LibraryRepository libraryRepository;
}
