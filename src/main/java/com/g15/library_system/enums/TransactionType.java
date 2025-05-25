package com.g15.library_system.enums;

public enum TransactionType {
  BORROW,
  RETURN,
  ;

  public boolean isBorrow() {
    return this == BORROW;
  }
}
