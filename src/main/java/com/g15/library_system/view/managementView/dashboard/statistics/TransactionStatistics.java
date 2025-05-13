package com.g15.library_system.view.managementView.dashboard.statistics;

import com.g15.library_system.data.TransactionData;
import com.g15.library_system.enums.TransactionType;
import java.time.*;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;

public class TransactionStatistics {

  public static Map<String, Long> aggregateLendingTrendData(int year) {
    Map<String, Long> lendingTrend =
        TransactionData.getInstance().getTransactions().stream()
            .filter(
                t ->
                    t.getTransactionType() == TransactionType.BORROW
                        && Instant.ofEpochMilli(t.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getYear()
                            == year)
            .map(
                t ->
                    Instant.ofEpochMilli(t.getCreatedAt())
                        .atZone(ZoneId.systemDefault())
                        .getMonth())
            .collect(
                Collectors.groupingBy(
                    month -> month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
                    () ->
                        new TreeMap<>(
                            Comparator.comparingInt(
                                m -> Month.valueOf(m.toUpperCase()).getValue())),
                    Collectors.counting()));

    return lendingTrend;
  }

//  public static Map<String, Long> aggregateLendingTrendData(int month, int year) {
//    Map<String, Long> lendingTrend =
//            TransactionData.getInstance().getTransactions().stream()
//                    .filter(
//                            t ->
//                                    t.getTransactionType() == TransactionType.BORROW
//                                            && Instant.ofEpochMilli(t.getCreatedAt())
//                                            .atZone(ZoneId.systemDefault())
//                                            .getYear()
//                                            == year)
//                    .map(
//                            t ->
//                                    Instant.ofEpochMilli(t.getCreatedAt())
//                                            .atZone(ZoneId.systemDefault())
//                                            .getMonth())
//                    .collect(
//                            Collectors.groupingBy(
//                                    month -> month.getDisplayName(TextStyle.FULL, Locale.ENGLISH),
//                                    () ->
//                                            new TreeMap<>(
//                                                    Comparator.comparingInt(
//                                                            m -> Month.valueOf(m.toUpperCase()).getValue())),
//                                    Collectors.counting()));
//
//    return lendingTrend;
//  }
}
