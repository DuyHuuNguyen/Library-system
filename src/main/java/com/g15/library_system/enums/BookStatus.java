package com.g15.library_system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookStatus {
  AVAILABLE("available"),
  BORROWED("borrowed"),
  RETURNED("returned"),
  LOST("lost"),
  DAMAGED("damaged"),
  OVERDUE("overdue"),
  NULL("null");
  private final String status;

  public static BookStatus get(String value) {
    for (BookStatus status : BookStatus.values()) {
      if (status.name().equalsIgnoreCase(value)) {
        return status;
      }
    }
    return BookStatus.NULL;
  }
}
