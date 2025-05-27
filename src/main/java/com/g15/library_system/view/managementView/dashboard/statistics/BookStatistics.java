package com.g15.library_system.view.managementView.dashboard.statistics;

import com.g15.library_system.data.BookData;
import com.g15.library_system.data.ReaderData;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.TransactionType;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BookStatistics {

  public Map<Book, Long> getMostBorrowedBooks(int limit) {
    return ReaderData.getInstance().getBorrowTransactions().stream()
        .filter(transaction -> transaction.getTransactionType() == TransactionType.BORROW)
        .flatMap(transaction -> transaction.getBooks().entrySet().stream())
        .collect(
            Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Book, Long>comparingByValue(Comparator.reverseOrder()))
        .limit(limit)
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public Map<Book, Long> getMostBorrowedBooks(int limit, int year) {
    return ReaderData.getInstance().getBorrowTransactions().stream()
        .filter(transaction -> transaction.getTransactionType() == TransactionType.BORROW)
        .filter(
            transaction ->
                Instant.ofEpochMilli(transaction.getCreatedAt())
                        .atZone(ZoneId.systemDefault())
                        .getYear()
                    == year)
        .flatMap(transaction -> transaction.getBooks().entrySet().stream())
        .collect(
            Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Book, Long>comparingByValue(Comparator.reverseOrder()))
        .limit(limit)
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public Map<Book, Long> getMostBorrowedBooks(int limit, String month, int year) {

    return ReaderData.getInstance().getBorrowTransactions().stream()
        .filter(transaction -> transaction.getTransactionType() == TransactionType.BORROW)
        .filter(
            transaction ->
                Instant.ofEpochMilli(transaction.getCreatedAt())
                            .atZone(ZoneId.systemDefault())
                            .getYear()
                        == year
                    && Instant.ofEpochMilli(transaction.getCreatedAt())
                            .atZone(ZoneId.systemDefault())
                            .getMonth()
                        == Month.valueOf(month.toUpperCase()))
        .flatMap(transaction -> transaction.getBooks().entrySet().stream())
        .collect(
            Collectors.groupingBy(Map.Entry::getKey, Collectors.summingLong(Map.Entry::getValue)))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Book, Long>comparingByValue(Comparator.reverseOrder()))
        .limit(limit)
        .collect(
            Collectors.toMap(
                Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
  }

  public Map<String, Long> aggregateBookAvailabilityData() {
    return BookData.getInstance().getBooks().stream()
        .collect(
            Collectors.groupingBy(book -> book.getBookStatus().getStatus(), Collectors.counting()));
  }
  // by status
  public Map<String, Long> aggregateBookAvailabilityData(int year) {
    return BookData.getInstance().getBooks().stream()
        .filter(
            book ->
                Instant.ofEpochMilli(book.getCreatedAt()).atZone(ZoneId.systemDefault()).getYear()
                    == year)
        .collect(
            Collectors.groupingBy(book -> book.getBookStatus().getStatus(), Collectors.counting()));
  }

  public static Map<String, Long> aggregateBookAvailabilityData(String month, int year) {
    return BookData.getInstance().getBooks().stream()
        .filter(
            book -> {
              Instant instant = Instant.ofEpochMilli(book.getCreatedAt());
              ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
              return zonedDateTime.getYear() == year
                  && zonedDateTime.getMonth() == Month.valueOf(month.toUpperCase());
            })
        .collect(
            Collectors.groupingBy(book -> book.getBookStatus().getStatus(), Collectors.counting()));
  }

  // by genre
  public Map<String, Long> countBookGenreData(int year) {
    Map<String, Long> result =
        BookData.getInstance().getBooks().stream()
            .filter(
                book ->
                    Instant.ofEpochMilli(book.getCreatedAt())
                            .atZone(ZoneId.systemDefault())
                            .getYear()
                        == year)
            .collect(
                Collectors.groupingBy(book -> book.getGenreType().name(), Collectors.counting()));

    return result;
  }

  public Map<String, Long> countBookGenreData(String month, int year) {
    Map<String, Long> result =
        BookData.getInstance().getBooks().stream()
            .filter(
                book ->
                    Instant.ofEpochMilli(book.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getYear()
                            == year
                        && Instant.ofEpochMilli(book.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getMonth()
                            == Month.valueOf(month.toUpperCase()))
            .collect(
                Collectors.groupingBy(book -> book.getGenreType().name(), Collectors.counting()));

    return result;
  }
}
