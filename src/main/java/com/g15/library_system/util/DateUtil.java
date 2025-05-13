package com.g15.library_system.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class DateUtil {
  public static boolean isSameDay(long timestamp1, long timestamp2) {
    LocalDate date1 = Instant.ofEpochMilli(timestamp1).atZone(ZoneId.systemDefault()).toLocalDate();
    LocalDate date2 = Instant.ofEpochMilli(timestamp2).atZone(ZoneId.systemDefault()).toLocalDate();
    return date1.isEqual(date2);
  }

  public static LocalDate convertToLocalDate(long timestamp) {
    try {
      return Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
    } catch (Exception e) {
      return null;
    }
  }

  public static long convertToEpochMilli(LocalDate date) {
    return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
  }
}
