package com.g15.library_system.exception;

import com.g15.library_system.enums.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EntityNotFoundException extends RuntimeException {
  private final String errorCode;
  private final String message;

  public EntityNotFoundException(ErrorCode errorCode) {
    super(errorCode.name());
    this.errorCode = errorCode.getCode();
    this.message = errorCode.getMessage();
  }
}
