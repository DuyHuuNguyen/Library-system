package com.g15.library_system.view.managementView.dashboard.statistics;

import com.g15.library_system.data.BookData;
import java.util.Map;
import java.util.stream.Collectors;

public class BookStatistics {

  public static Map<String, Long> aggregateBookAvailabilityData() {
    return BookData.getInstance().getBooks().stream()
        .collect(
            Collectors.groupingBy(book -> book.getBookStatus().getStatus(), Collectors.counting()));
  }
}
