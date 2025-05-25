package com.g15.library_system.repository;

import com.g15.library_system.entity.Librarian;
import java.util.Optional;

public interface LibrarianRepository {
  Optional<Librarian> findByEmail(String email);
}
