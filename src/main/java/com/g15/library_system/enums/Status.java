package com.g15.library_system.enums;

public enum Status {
  RETURNED,
  LOST,
  DAMAGED,
  OVERDUE,
  AVAILABLE,
  NULL;

  public static Status get(String status) {
    for (Status s : Status.values()) {
      if (s.name().equalsIgnoreCase(status)) {
        return s;
      }
    }
    return NULL;
  }
}
