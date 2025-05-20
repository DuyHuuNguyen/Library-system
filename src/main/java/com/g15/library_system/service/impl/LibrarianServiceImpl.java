package com.g15.library_system.service.impl;

import com.g15.library_system.entity.Librarian;
import com.g15.library_system.repository.LibrarianRepository;
import com.g15.library_system.service.LibrarianService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianServiceImpl implements LibrarianService {
  private final LibrarianRepository librarianRepository;

  @Override
  public Optional<Librarian> findByEmail(String email) {
    return librarianRepository.findByEmail(email);
  }
}
