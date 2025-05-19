package com.g15.library_system.view.managementView.dashboard.chartObserver;

public interface ChartFilterSubject {
  void addObserver(FilterObserver observer);

  void removeObserver(FilterObserver observer);

  void notifyObservers();
}
