package com.g15.library_system.facade.impl;

import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.facade.LibrarianFacade;
import com.g15.library_system.service.LibrarianService;
import javax.swing.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianFacadeImpl implements LibrarianFacade {
  private final LibrarianService librarianService;

  @Override
  public boolean login(LoginRequest loginRequest) {
    var librarian = this.librarianService.findByEmail(loginRequest.getEmail()).orElse(null);
    var isNotFoundLibrarian = librarian == null;

    if (isNotFoundLibrarian) return false;

    return librarian.isSamePassword(loginRequest.getPassword());
  }
}
