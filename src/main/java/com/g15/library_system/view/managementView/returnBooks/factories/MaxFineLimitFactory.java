package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.MaxFineStrategy;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;

public class MaxFineLimitFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.MAX_FINE;
  }

  @Override
  public OverdueFineStrategy createStrategy(FineStrategyType type) {
    return new MaxFineStrategy(2000, 100000);
  }
}
