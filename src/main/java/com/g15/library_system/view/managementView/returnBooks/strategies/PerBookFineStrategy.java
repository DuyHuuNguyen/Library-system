package com.g15.library_system.view.managementView.returnBooks.strategies;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;

public class PerBookFineStrategy implements OverdueFineStrategy {

  private final double perBookPerDayRate;

  public PerBookFineStrategy(double perBookPerDayRate) {
    this.perBookPerDayRate = perBookPerDayRate;
  }

  /**
   * Calculates the overdue fine based on the number of days late and the number of books.
   *
   * @param transaction The transaction for which to calculate the fine.
   * @return The calculated fine.
   */
  @Override
  public double calculateFine(Transaction transaction, LocalDate returnDate) {
    Long expected = transaction.getExpectedReturnAt();
    Long actual = DateUtil.convertToEpochMilli(returnDate);

    long daysLate = (actual - expected) / (1000 * 60 * 60 * 24);
    int totalBooks = transaction.getBooks().values().stream().mapToInt(Integer::intValue).sum();

    return daysLate * totalBooks * perBookPerDayRate;
  }
}
