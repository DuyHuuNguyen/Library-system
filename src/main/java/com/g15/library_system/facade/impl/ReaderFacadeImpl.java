package com.g15.library_system.facade.impl;

import com.g15.library_system.entity.Reader;
import com.g15.library_system.facade.ReaderFacade;
import com.g15.library_system.service.ReaderService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderFacadeImpl implements ReaderFacade {
  private final ReaderService readerService;

  @Override
  public List<String> searchNameContains(String name) {
    return readerService.findAll().stream()
        .filter(reader -> reader.nameContains(name))
        .map(reader -> reader.getFirstName() + " " + reader.getLastName())
        .toList();
  }

  @Override
  public Optional<Reader> findByName(String name) {
    return readerService.findByName(name);
  }
}
