package com.g15.library_system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
  AVAILABLE("ccc"),
  BORROWED("cc"),
  DEMO("cc"),
  RETURNED("returned"),
  LOST("lost"),
  DAMAGED("damaged"),
  OVERDUE("overdue");
  private final String status;

  public boolean isDemo() {
    return this == DEMO;
  }
}
