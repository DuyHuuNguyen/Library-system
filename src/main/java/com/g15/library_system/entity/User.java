package com.g15.library_system.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity implements Observer {

  protected String firstName;

  protected String lastName;

  protected String password;

  protected String email;

  protected String phoneNumber;

  protected String avatarKey;

  protected Long dateOfBirth;

  protected String address;

  public String getFullName() {
    return this.lastName.concat(" " + firstName);
  }

  @Override
  public void updateNewBook(Book newBook) {}

  @Override
  public void updateNewEvent(String newEvent) {}

  @Override
  public boolean isSameId(long id) {
    return id == super.getId();
  }

  @Override
  public boolean isSameEmail(String email) {
    return this.email.equals(email);
  }
}
