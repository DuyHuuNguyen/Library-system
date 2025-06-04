package com.g15.library_system.view.managementView.returnBooks.strategies.tiers;

import lombok.Getter;

public class YearFineTier {
  private int maxAge;
  @Getter private double finePerDay;

  public YearFineTier(int maxAge, double finePerDay) {
    this.maxAge = maxAge;
    this.finePerDay = finePerDay;
  }

  public boolean isWithinMaxAge(int bookAge) {
    return bookAge <= maxAge;
  }
}
