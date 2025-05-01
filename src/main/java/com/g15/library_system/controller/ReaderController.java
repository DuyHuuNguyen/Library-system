package com.g15.library_system.controller;

import com.g15.library_system.entity.Reader;
import com.g15.library_system.facade.ReaderFacade;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReaderController {
  private final ReaderFacade readerFacade;

  public List<String> searchNameContains(String name) {
    return readerFacade.searchNameContains(name);
  }

  public Optional<Reader> findByName(String name) {
    return readerFacade.findByName(name);
  }
}
