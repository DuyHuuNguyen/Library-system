package com.g15.library_system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BookActiveType {
  DISPLAY(true),
  HIDDEN(false);

  private final Boolean active;
}
