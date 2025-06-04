package com.g15.library_system.entity;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@SuperBuilder
@Getter
public class Librarian extends User {

  public boolean isSamePassword(String password) {
    return this.password.equals(password);
  }

  public void changePassword(String newPassword) {
    this.password = newPassword;
  }

    public boolean hasSameName(String lastName) {
        return this.lastName.equalsIgnoreCase(lastName);
    }

    public boolean isSameInfo(String text) {
        return this.firstName.toLowerCase().contains(text.toLowerCase()) ||
                this.lastName.toLowerCase().contains(text.toLowerCase()) ||
                this.phoneNumber.contains(text);
    }
  public boolean isSameLibrarian(Librarian other) {
    return this.getId() == other.getId();
  }

}
