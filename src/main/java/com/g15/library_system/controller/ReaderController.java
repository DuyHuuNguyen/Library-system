package com.g15.library_system.controller;

import com.g15.library_system.facade.ReaderFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class ReaderController {
  private final ReaderFacade readerFacade;
}
