package com.g15.library_system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReturnStatus {
  ON_TIME("On time"),
  OVERDUE("Overdue"),
  UNRETURNED("Unreturned");
  private final String value;
}
