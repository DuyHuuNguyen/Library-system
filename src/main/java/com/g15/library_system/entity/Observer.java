package com.g15.library_system.entity;

public interface Observer {
  void updateNewBook(Book newBook);

  void updateNewEvent(String newEvent);

  boolean isSameId(long id);

  boolean isSameEmail(String email);
}
