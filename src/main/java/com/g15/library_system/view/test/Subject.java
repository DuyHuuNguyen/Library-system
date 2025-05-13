package com.g15.library_system.view.test;

public interface Subject {
  void registerObserver(Observer o);

  void removeObserver(Observer o);

  void notifyObservers();
}
