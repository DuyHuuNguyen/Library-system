package com.g15.library_system.repository.impl;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.repository.ReaderRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class ReaderRepositoryImpl implements ReaderRepository {
  private ReaderData readerData = ReaderData.getInstance();

  @Override
  public Optional<Reader> findByName(String name) {
    return readerData.getReaders().stream().filter(reader -> reader.findByName(name)).findFirst();
  }

  @Override
  public Optional<Reader> findById(String id) {
    return readerData.getReaders().stream().filter(reader -> reader.findById(id)).findFirst();
  }

  @Override
  public List<Reader> findAll() {
    return readerData.getReaders();
  }
}
