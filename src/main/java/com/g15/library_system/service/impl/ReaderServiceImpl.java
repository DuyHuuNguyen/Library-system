package com.g15.library_system.service.impl;

import com.g15.library_system.repository.ReaderRepository;
import com.g15.library_system.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
  private final ReaderRepository readerRepository;
}
