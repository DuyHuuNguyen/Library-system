package com.g15.library_system.entity;

public interface Observer {
  void updateNewBook(Book newBook);

  void updateNewEvent(String newEvent);
}
