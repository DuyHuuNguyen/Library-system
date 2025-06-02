package com.g15.library_system.view.managementView.returnBooks.factories.simpleFactory;

import com.g15.library_system.view.managementView.returnBooks.strategies.*;
import com.g15.library_system.view.managementView.returnBooks.strategies.tiers.YearFineTier;

import java.util.List;

public class FineStrategyFactory {
    public static OverdueFineStrategy createStrategy(FineStrategyType type) {
        return switch (type) {
            case DAILY_FINE -> new FixedDailyFineStrategy(2000);
            case PER_BOOK -> new PerBookFineStrategy(1000);
            case YEAR_BASED -> new YearBasedFineStrategy(List.of(
                new YearFineTier(1, 10000),
                new YearFineTier(3, 7000),
                new YearFineTier(10, 5000),
                new YearFineTier(Integer.MAX_VALUE, 2000)
            ));
            case MAX_FINE -> new MaxFineStrategy(2000,80000);
            case NO_FINE -> new NoFineStrategy();
        };
    }

}
