package com.g15.library_system.view.managementView.dashboard.statistics;

import com.g15.library_system.data.ReaderData;
import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ReaderStatistics {

  public Map<String, Long> aggregateReaderSignUpTrendData(int year) {
    Map<String, Long> result =
        ReaderData.getInstance().getReaders().stream()
            .filter(
                t ->
                    Instant.ofEpochMilli(t.getCreatedAt()).atZone(ZoneId.systemDefault()).getYear()
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

    return result;
  }

  public Map<String, Long> aggregateReaderSignUpTrendData(String selectedMonth, int year) {
    Month monthConverted = Month.valueOf(selectedMonth.toUpperCase());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");

    Map<String, Long> result =
        ReaderData.getInstance().getReaders().stream()
            .filter(
                t ->
                    Instant.ofEpochMilli(t.getCreatedAt()).atZone(ZoneId.systemDefault()).getYear()
                            == year
                        && Instant.ofEpochMilli(t.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getMonth()
                            == monthConverted)
            .map(
                t ->
                    Instant.ofEpochMilli(t.getCreatedAt())
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate())
            .collect(
                Collectors.groupingBy(
                    date -> date.format(formatter), TreeMap::new, Collectors.counting()));

    return result;
  }

  public Map<String, Long> aggregateReaderTypeData(int year) {
    Map<String, Long> result =
        ReaderData.getInstance().getReaders().stream()
            .filter(
                reader ->
                    Instant.ofEpochMilli(reader.getCreatedAt())
                            .atZone(ZoneId.systemDefault())
                            .getYear()
                        == year)
            .collect(
                Collectors.groupingBy(
                    reader -> reader.getReaderType().getTypeName(), Collectors.counting()));

    return result;
  }

  public Map<String, Long> aggregateReaderTypeData(String month, int year) {
    Map<String, Long> result =
        ReaderData.getInstance().getReaders().stream()
            .filter(
                reader ->
                    Instant.ofEpochMilli(reader.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getYear()
                            == year
                        && Instant.ofEpochMilli(reader.getCreatedAt())
                                .atZone(ZoneId.systemDefault())
                                .getMonth()
                            == Month.valueOf(month.toUpperCase()))
            .collect(
                Collectors.groupingBy(
                    reader -> reader.getReaderType().getTypeName(), Collectors.counting()));

    return result;
  }
}
