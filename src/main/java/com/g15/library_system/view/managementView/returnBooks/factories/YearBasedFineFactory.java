package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.entity.strategies.FineStrategyType;
import com.g15.library_system.entity.strategies.OverdueFineStrategy;
import com.g15.library_system.entity.strategies.YearBasedFineStrategy;
import com.g15.library_system.entity.strategies.tiers.YearFineTier;
import java.util.List;

public class YearBasedFineFactory implements IFineStrategyFactory {

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.YEAR_BASED;
  }

  @Override
  public OverdueFineStrategy createStrategy(FineStrategyType type) {
    List<YearFineTier> tiers =
        List.of(
            new YearFineTier(1, 10000),
            new YearFineTier(5, 5000),
            new YearFineTier(10, 1000),
            new YearFineTier(Integer.MAX_VALUE, 800));
    return new YearBasedFineStrategy(tiers);
  }
}
