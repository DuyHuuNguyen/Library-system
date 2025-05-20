package com.g15.library_system.observers;

public interface ReaderSubject {
  void registerObserver(ReaderObserver o);

  void removeObserver(ReaderObserver o);

  void notifyObservers();
}
