package com.g15.library_system.view.managementView.returnBooks.strategies;

import com.g15.library_system.entity.Transaction;
import java.time.LocalDate;

public class NoFineStrategy implements OverdueFineStrategy {
  /**
   * Calculates the overdue fine based on the number of days late and a fixed daily rate.
   *
   * @param transaction The transaction for which to calculate the fine.
   * @return The calculated fine.
   */
  @Override
  public double calculateFine(Transaction transaction, LocalDate returnDate) {
    return 0;
  }
}
