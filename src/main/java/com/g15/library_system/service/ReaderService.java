package com.g15.library_system.service;

import com.g15.library_system.entity.Reader;
import java.util.List;
import java.util.Optional;

public interface ReaderService {

  Optional<Reader> findByName(String name);

  Optional<Reader> findById(String id);

  List<Reader> findAll();
}
