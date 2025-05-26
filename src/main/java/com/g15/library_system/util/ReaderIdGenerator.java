package com.g15.library_system.util;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

public class ReaderIdGenerator {
  private static final AtomicLong counter = new AtomicLong(1);
  private static int year = LocalDate.now().getYear();

  public static Long generateId() {
    String yearStr = String.valueOf(year).substring(2);
    String countStr = String.format("%04d", counter.getAndIncrement());
    String result = yearStr + countStr;
    return Long.parseLong(result);
  }

  public static void main(String[] args) {
    System.out.println(ReaderIdGenerator.generateId());
  }
}
