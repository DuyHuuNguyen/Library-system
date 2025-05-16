package com.g15.library_system.view.managementView.dashboard.chartObserver;

import java.util.ArrayList;
import java.util.List;

public class ChartFilterSubject implements Subject {
    private final List<ChartObserver> observers = new ArrayList<>();

    @Override
    public void register(ChartObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void remove(ChartObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(String month, int year) {
        for (ChartObserver o : observers) {
            o.onFilterChanged(month, year);
        }
    }
}
