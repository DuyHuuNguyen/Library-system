package com.g15.library_system.view.managementView.returnBooks.strategies;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FineStrategyType {
  DAILY_FINE("Daily fine"),
  PER_BOOK("Per book"),
  YEAR_BASED("Year based"),
  MAX_FINE("Max fine"),
  NO_FINE("No fine");
  private final String values;
}
