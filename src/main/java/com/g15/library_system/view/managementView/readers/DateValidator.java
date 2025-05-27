package com.g15.library_system.view.managementView.readers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator {
  public static boolean isValidDate(String dateStr, String pattern) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

    try {
      LocalDate date = LocalDate.parse(dateStr, formatter);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }

  //    public static void main(String[] args) {
  //        System.out.println(isValidDate("2025-05-20", "yyyy-MM-dd")); // true
  //        System.out.println(isValidDate("31/02/2024", "dd/MM/yyyy")); // false
  //        System.out.println(isValidDate("20-05-2025", "yyyy-MM-dd")); // false
  //    }
}
