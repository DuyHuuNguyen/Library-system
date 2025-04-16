package com.g15.library_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
  ADMIN("ADMIN"),
  USER("USER"),
  EMPLOYEE("EMPLOYEE");

  private final String name;

  public boolean isAdmin() {
    return this == ADMIN;
  }

  public boolean isUser() {
    return this == USER;
  }
}
