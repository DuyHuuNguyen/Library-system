package com.g15.library_system.view.managementView.returnBooks.factories;

import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import com.g15.library_system.view.managementView.returnBooks.strategies.OverdueFineStrategy;
import com.g15.library_system.view.managementView.returnBooks.strategies.YearBasedFineStrategy;
import com.g15.library_system.view.managementView.returnBooks.strategies.tiers.YearFineTier;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class YearBasedFineFactory implements IFineStrategyFactory {
  private static final List<YearFineTier> tiers =
      new ArrayList<>(
          List.of(
              new YearFineTier(1, 10000),
              new YearFineTier(5, 5000),
              new YearFineTier(10, 1000),
              new YearFineTier(Integer.MAX_VALUE, 800)));

  @Override
  public FineStrategyType getStrategyType() {
    return FineStrategyType.BOOK_AGE;
  }

  @Override
  public OverdueFineStrategy createStrategy() {
    return new YearBasedFineStrategy(tiers);
  }
}
