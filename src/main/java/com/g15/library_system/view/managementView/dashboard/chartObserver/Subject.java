package com.g15.library_system.view.managementView.dashboard.chartObserver;

import com.g15.library_system.observers.BookObserver;

public interface Subject {
    void register(ChartObserver observer);
    void remove(ChartObserver observer);
    void notifyObservers(String month, int year);
}
