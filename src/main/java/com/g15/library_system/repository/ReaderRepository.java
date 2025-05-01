package com.g15.library_system.repository;

import com.g15.library_system.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderRepository {
  List<String> searchNameContains(String id);

    Optional<Reader> findByName(String name);
}
