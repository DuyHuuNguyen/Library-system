package com.g15.library_system.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
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
    return this.firstName.toLowerCase().contains(text.toLowerCase())
        || this.lastName.toLowerCase().contains(text.toLowerCase())
        || this.phoneNumber.contains(text);
  }

  public String getDateOfBirthAsString() {
    if (dateOfBirth == null) return null;
    Date date = new Date(dateOfBirth); // dateOfBirth được tính bằng milliseconds từ epoch
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // định dạng ngày tháng
    return sdf.format(date);
  }
}
