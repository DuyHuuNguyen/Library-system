package com.g15.library_system.view.test;

import java.util.ArrayList;
import java.util.List;

public class ReaderData implements Subject {
  private static ReaderData instance;
  private final List<Reader> readers = new ArrayList<>();
  private final List<Observer> observers = new ArrayList<>();

  private ReaderData() {}

  public static ReaderData getInstance() {
    if (instance == null) {
      instance = new ReaderData();
    }
    return instance;
  }

  public void addData(Reader data) {
    readers.add(data);
    notifyObservers();
  }

  public List<Reader> getDataList() {
    return readers;
  }

  @Override
  public void registerObserver(Observer o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(Observer o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (Observer o : observers) {
      o.update();
    }
  }
}
