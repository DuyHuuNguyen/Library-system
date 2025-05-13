package com.g15.library_system.observers;

public interface BookSubject {
  void registerObserver(BookObserver o);

  void removeObserver(BookObserver o);

  void notifyObservers();
}
