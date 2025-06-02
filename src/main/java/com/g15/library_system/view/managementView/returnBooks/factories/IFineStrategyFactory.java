package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import com.g15.library_system.view.managementView.returnBooks.strategies.OverdueFineStrategy;

public interface IFineStrategyFactory {
  FineStrategyType getStrategyType();

  OverdueFineStrategy createStrategy(FineStrategyType type);
}
