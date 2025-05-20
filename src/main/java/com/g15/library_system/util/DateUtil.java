package com.g15.library_system.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

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

  public static boolean isNowDay(long timestamp) {
    Calendar currentDate = Calendar.getInstance();
    Calendar dateToCheck = Calendar.getInstance();
    dateToCheck.setTimeInMillis(timestamp);
    return currentDate.get(Calendar.YEAR) == dateToCheck.get(Calendar.YEAR)
        && currentDate.get(Calendar.MONTH) == dateToCheck.get(Calendar.MONTH)
        && currentDate.get(Calendar.DAY_OF_MONTH) == dateToCheck.get(Calendar.DAY_OF_MONTH);
  }
}
