package com.g15.library_system.service;

import com.g15.library_system.entity.Librarian;
import java.util.Optional;

public interface LibrarianService {
  Optional<Librarian> findByEmail(String email);
}
