package com.g15.library_system.entity;

import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@SuperBuilder
public class Librarian extends User {
  public boolean isSamePassword(String password) {
    return this.password.equals(password);
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }
}
