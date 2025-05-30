package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.NoFineStrategy;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;

public class NoFineFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.NO_FINE;
  }

  @Override
  public OverdueFineStrategy createStrategy(FineStrategyType type) {
    return new NoFineStrategy();
  }
}
