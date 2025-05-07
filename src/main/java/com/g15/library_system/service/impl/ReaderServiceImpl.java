package com.g15.library_system.service.impl;

import com.g15.library_system.entity.Reader;
import com.g15.library_system.repository.ReaderRepository;
import com.g15.library_system.service.ReaderService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
  private final ReaderRepository readerRepository;

  @Override
  public Optional<Reader> findByName(String name) {
    return readerRepository.findByName(name);
  }

  @Override
  public List<Reader> findAll() {
    return readerRepository.findAll();
  }
}
