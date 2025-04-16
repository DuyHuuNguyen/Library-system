package com.g15.library_system.entity;

public interface Subject {
  void addObserver(Observer o);

  void removeObserver(Observer o);

  void notifyNewBook(Book book);

  void notifyNewEvent(String newEvent);
}
