package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;
import com.g15.library_system.entity.strategies.PerBookFineStrategy;

public class PerBookFineFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.PER_BOOK;
  }

  @Override
  public OverdueFineStrategy createStrategy(FineStrategyType type) {
    return new PerBookFineStrategy(1000);
  }
}
