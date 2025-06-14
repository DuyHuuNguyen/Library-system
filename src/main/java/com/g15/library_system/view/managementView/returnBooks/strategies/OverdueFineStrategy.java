package com.g15.library_system.view.managementView.returnBooks.strategies;

import com.g15.library_system.entity.Transaction;
import java.time.LocalDate;

public interface OverdueFineStrategy {
  double calculateFine(Transaction transaction, LocalDate returnDate);
}
