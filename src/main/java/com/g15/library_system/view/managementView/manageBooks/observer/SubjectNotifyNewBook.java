package com.g15.library_system.view.managementView.manageBooks.observer;

public interface SubjectNotifyNewBook {
  void notifyAllNewBook();

  void addObserverNotifyNewBook(ObserverNotifyNewBook observerNotifyNewBook);
}
