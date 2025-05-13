package com.g15.library_system.observers;

public interface TransactionSubject {
  void registerObserver(TransactionObserver o);

  void removeObserver(TransactionObserver o);

  void notifyObservers();
}
