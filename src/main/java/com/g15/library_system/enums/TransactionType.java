package com.g15.library_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
  BORROW("Borrow"),
  RETURNED("Returned");
  private final String value;

  public boolean isBorrow() {
    return this == BORROW;
  }
}
