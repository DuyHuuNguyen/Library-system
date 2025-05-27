package com.g15.library_system.entity.strategies;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;

public class MaxFineStrategy implements OverdueFineStrategy {

  private final double dailyRate;
  private final double maxFine;

  public MaxFineStrategy(double dailyRate, double maxFine) {
    this.dailyRate = dailyRate;
    this.maxFine = maxFine;
  }
  /**
   * Calculates the overdue fine based on the number of days late and a fixed daily rate, capped at
   * a maximum fine.
   *
   * @param transaction The transaction for which to calculate the fine.
   * @return The calculated fine, capped at maxFine.
   */
  @Override
  public double calculateFine(Transaction transaction, LocalDate returnDate) {
    Long expected = transaction.getExpectedReturnAt();
    Long actual = DateUtil.convertToEpochMilli(returnDate);

    if (expected == null || actual == null || actual <= expected) return 0;

    long daysLate = (actual - expected) / (1000 * 60 * 60 * 24);

    return Math.min(daysLate * dailyRate, maxFine);
  }
}
