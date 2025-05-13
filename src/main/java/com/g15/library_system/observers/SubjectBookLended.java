package com.g15.library_system.observers;

public interface SubjectBookLended {
  void registerObserver(BookLendedObserver o);

  void removeObserver(BookLendedObserver o);

  void notifyObservers();
}
