package com.g15.library_system.entity.strategies;

import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.entity.strategies.tiers.YearFineTier;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class YearBasedFineStrategy implements OverdueFineStrategy {
  private List<YearFineTier> tiers;
  private final int currentYear = LocalDate.now().getYear();

  public YearBasedFineStrategy(List<YearFineTier> tiers) {
    this.tiers = tiers;
  }

  @Override
  public double calculateFine(Transaction transaction, LocalDate returnDate) {
    Long expected = transaction.getExpectedReturnAt();
    Long actual = DateUtil.convertToEpochMilli(returnDate);

    int daysLate = (int) ((actual - expected) / (1000 * 60 * 60 * 24));
    double totalFine = 0.0;

    for (Map.Entry<Book, Integer> entry : transaction.getBooks().entrySet()) {
      Book book = entry.getKey(); // book borrow
      int quantity = entry.getValue();

      int publicationYear = book.getPublishYear();
      int bookAge = currentYear - publicationYear;

      double finePerDay =
          tiers.stream()
              .filter(tier -> tier.isWithinMaxAge(bookAge))
              .map(tier -> tier.getFinePerDay())
              .findFirst()
              .orElse(0.0);

      totalFine += finePerDay * quantity * daysLate;
    }

    return totalFine;
  }
}
