package com.g15.library_system.service;

import com.g15.library_system.entity.Reader;

import java.util.List;
import java.util.Optional;

public interface ReaderService {
  List<String> searchNameContains(String name);

    Optional<Reader> findByName(String name);
}
