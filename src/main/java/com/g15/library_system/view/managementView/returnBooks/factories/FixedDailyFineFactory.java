package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.*;

public class FixedDailyFineFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.DAILY_FINE;
  }

  @Override
  public OverdueFineStrategy createStrategy(FineStrategyType type) {
    return new FixedDailyFineStrategy(2000);
  }
}
