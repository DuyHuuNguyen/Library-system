package com.g15.library_system.observers;

public interface LibrarianSubject {
  void registerObserver(LibrarianObserver o);

  void removeObserver(LibrarianObserver o);

  void notifyObservers();
}
