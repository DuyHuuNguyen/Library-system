package com.g15.library_system.controller;

import com.g15.library_system.facade.OverdueFeeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class OverdueFeeController {
  private final OverdueFeeFacade overdueFeeFacade;
}
