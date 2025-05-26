package com.g15.library_system.entity.strategies;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;

public class FixedDailyFineStrategy implements OverdueFineStrategy {

  private double dailyRate;

  public FixedDailyFineStrategy(double dailyRate) {
    this.dailyRate = dailyRate;
  }
  /**
   * Calculates the overdue fine based on the number of days late and a fixed daily rate.
   *
   * @param transaction The transaction for which to calculate the fine.
   * @return The calculated fine.
   */
  @Override
  public double calculateFine(Transaction transaction, LocalDate returnDate) {
    Long expected = transaction.getExpectedReturnAt();
    Long actual = DateUtil.convertToEpochMilli(returnDate);

    long daysLate = (actual - expected) / (1000 * 60 * 60 * 24);

    return daysLate * dailyRate;
  }
}
