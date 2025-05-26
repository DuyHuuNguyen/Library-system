package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;

public interface IFineStrategyFactory {
  FineStrategyType getStrategyType();

  OverdueFineStrategy createStrategy(FineStrategyType type);
}
