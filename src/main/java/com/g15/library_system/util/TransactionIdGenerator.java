package com.g15.library_system.util;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionIdGenerator {
  private static final AtomicLong counter = new AtomicLong(1);
  private static int currentYear = LocalDate.now().getYear();

  public static synchronized Long generateId() {
    int year = LocalDate.now().getYear();
    if (year != currentYear) {
      currentYear = year;
      counter.set(1);
    }
    String yearStr = String.valueOf(year).substring(2);
    String countStr = String.format("%04d", counter.getAndIncrement());
    String result = yearStr + countStr;
    return Long.parseLong(result);
  }
}
