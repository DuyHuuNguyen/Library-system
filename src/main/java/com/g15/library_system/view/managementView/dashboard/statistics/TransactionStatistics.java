package com.g15.library_system.view.managementView.dashboard.statistics;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.data.TransactionData;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.util.DateUtil;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TransactionStatistics {

  // borrow Overview Chart-------------------------------------------
  public Map<String, Long> countBorrowStatusDistribution(int year) {
    return ReaderData.getInstance().getBorrowTransactions().stream()
        .filter(tran -> tran.getTransactionType() == TransactionType.BORROW)
        .filter(tran -> tran.getCreatedYear().orElse(-1) == year)
        .collect(
            Collectors.groupingBy(
                trans -> trans.getReturnStatus().getValue(), Collectors.counting()));
  }

  public Map<String, Long> countBorrowStatusDistribution(String selectedMonth, int year) {
    Month targetMonth = Month.valueOf(selectedMonth.toUpperCase());

    return ReaderData.getInstance().getBorrowTransactions().stream()
        .filter(tran -> tran.getTransactionType() == TransactionType.BORROW)
        .filter(tran -> tran.getCreatedYear().orElse(-1) == year)
        .filter(tran -> tran.getCreatedMonth().map(month -> month == targetMonth).orElse(false))
        .collect(
            Collectors.groupingBy(
                tran -> tran.getReturnStatus().getValue(), Collectors.counting()));
  }

  // Book borrow by genre chart---------------------------------------------
  public Map<String, Map<String, Long>> aggregateGenreBorrowData(int year) {
    return TransactionData.getInstance().getTransactions().stream()
        .filter(
            trans ->
                trans.getTransactionType() == TransactionType.BORROW
                    && trans.getCreatedYear().orElse(-1) == year)
        .flatMap(
            trans ->
                trans
                    .getGenreWithQuantities()
                    .flatMap(
                        entry ->
                            trans.getCreatedMonth().stream()
                                .map(
                                    month ->
                                        new AbstractMap.SimpleEntry<>(
                                            month.getDisplayName(
                                                TextStyle.FULL, Locale.ENGLISH), // Key: month
                                            new AbstractMap.SimpleEntry<>(
                                                entry.getKey(),
                                                entry.getValue()) // Value: (genre, quantity)
                                            ))))
        .collect(
            Collectors.groupingBy(
                Map.Entry::getKey,
                () ->
                    new TreeMap<>(
                        Comparator.comparingInt(m -> Month.valueOf(m.toUpperCase()).getValue())),
                Collectors.groupingBy(
                    e -> e.getValue().getKey(), // genre
                    Collectors.summingLong(e -> e.getValue().getValue()))));
  }

  public Map<String, Map<String, Long>> aggregateGenreBorrowData(String selectedMonth, int year) {
    Month targetMonth = Month.valueOf(selectedMonth.toUpperCase());

    return TransactionData.getInstance().getTransactions().stream()
        .filter(
            trans ->
                trans.getTransactionType() == TransactionType.BORROW
                    && trans.getCreatedYear().orElse(-1) == year
                    && trans.getCreatedMonth().map(m -> m == targetMonth).orElse(false))
        .flatMap(
            trans ->
                trans
                    .getGenreWithQuantities()
                    .flatMap(
                        entry ->
                            trans.getCreatedDayString().stream()
                                .map(
                                    dayStr ->
                                        new AbstractMap.SimpleEntry<>(
                                            dayStr,
                                            new AbstractMap.SimpleEntry<>(
                                                entry.getKey(),
                                                entry.getValue()) // value: (genre, quantity)
                                            ))))
        .collect(
            Collectors.groupingBy(
                Map.Entry::getKey,
                TreeMap::new,
                Collectors.groupingBy(
                    e -> e.getValue().getKey(),
                    Collectors.summingLong(e -> e.getValue().getValue()))));
  }

  // Late Book return chart
  public Map<String, Long> aggregateLateReturnTrend(int year) {
    return ReaderData.getInstance().getReturnTransactions().stream()
        .filter(
            trans ->
                trans.getActualReturnAt() != null
                    && trans.getOverdueFine() != null
                    && DateUtil.convertToLocalDate(trans.getActualReturnAt()).getYear() == year)
        .map(trans -> DateUtil.convertToLocalDate(trans.getActualReturnAt()).getMonth())
        .collect(
            Collectors.groupingBy(
                month -> month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                () ->
                    new TreeMap<>(
                        Comparator.comparingInt(m -> Month.valueOf(m.toUpperCase()).getValue())),
                Collectors.counting()));
  }

  public Map<String, Long> aggregateLateReturnTrend(String selectedMonth, int year) {
    Month monthConverted = Month.valueOf(selectedMonth.toUpperCase());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

    return ReaderData.getInstance().getReturnTransactions().stream()
        .filter(
            trans ->
                trans.getActualReturnAt() != null
                    && trans.getOverdueFine() != null
                    && DateUtil.convertToLocalDate(trans.getActualReturnAt()).getYear() == year
                    && DateUtil.convertToLocalDate(trans.getActualReturnAt()).getMonth()
                        == monthConverted)
        .map(trans -> DateUtil.convertToLocalDate(trans.getActualReturnAt()))
        .collect(
            Collectors.groupingBy(
                date -> date.format(formatter), TreeMap::new, Collectors.counting()));
  }
}
