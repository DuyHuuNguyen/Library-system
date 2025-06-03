package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import com.g15.library_system.view.managementView.returnBooks.strategies.MaxFineStrategy;
import com.g15.library_system.view.managementView.returnBooks.strategies.OverdueFineStrategy;

public class MaxFineLimitFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.MAX_FINE;
  }

  @Override
  public OverdueFineStrategy createStrategy() {
    return new MaxFineStrategy(2000, 100000);
  }
}
