package com.g15.library_system.service.impl;

import com.g15.library_system.repository.OverdueFeeRepository;
import com.g15.library_system.service.FineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FineServiceImpl implements FineService {
  private final OverdueFeeRepository fineRepository;
}
