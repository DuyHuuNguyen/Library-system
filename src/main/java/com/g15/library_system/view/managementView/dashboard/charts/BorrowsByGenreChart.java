package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.TransactionStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class BorrowsByGenreChart extends RoundedShadowPanel
    implements FilterObserver, ReaderObserver {
  private ChartPanel chartPanel;
  private JFreeChart stackedBarChart;

  private TransactionStatistics transactionStatistics = new TransactionStatistics();
  private ArrayList<Integer> years = new ArrayList<>();
  private String selectedMonth;
  private Integer selectedYear;
  private Map<String, Map<String, Long>> borrowingData =
      new HashMap<>(); // <Month/Day, <Genre, Count>>
  private DefaultCategoryDataset chartDataset;

  public BorrowsByGenreChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(730, 450));
    this.setLayout(new BorderLayout());
    ReaderData.getInstance().registerObserver(this);

    // title panel
    TitlePanel titlePn = new TitlePanel("Book Borrows By Genre");
    this.selectedYear = titlePn.getSelectedYear();
    this.selectedMonth = titlePn.getSelectedMonth();

    // chart panel
    chartDataset = new DefaultCategoryDataset();

    borrowingData = transactionStatistics.aggregateGenreBorrowData(selectedYear);

    if (borrowingData != null && !borrowingData.isEmpty()) {
      for (Map.Entry<String, Map<String, Long>> monthEntry : borrowingData.entrySet()) {
        String month = monthEntry.getKey();
        Map<String, Long> genreCounts = monthEntry.getValue();

        for (Map.Entry<String, Long> genreEntry : genreCounts.entrySet()) {
          chartDataset.setValue(genreEntry.getValue(), genreEntry.getKey(), month);
        }
      }
    }

    stackedBarChart =
        JFreeChartGenerator.createStackedBarChart("", "Months", "Books borrowed", chartDataset);

    chartPanel = new ChartPanel(stackedBarChart);
    this.add(chartPanel, BorderLayout.CENTER);

    titlePn.addObserver(this); // add observer
    this.add(titlePn, BorderLayout.NORTH);
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    borrowingData = transactionStatistics.aggregateGenreBorrowData(year);
    clearChartData();

    if (borrowingData != null && !borrowingData.isEmpty()) {
      for (Map.Entry<String, Map<String, Long>> monthEntry : borrowingData.entrySet()) {
        String month = monthEntry.getKey();
        Map<String, Long> genreCounts = monthEntry.getValue();

        for (Map.Entry<String, Long> genreEntry : genreCounts.entrySet()) {
          chartDataset.setValue(genreEntry.getValue(), genreEntry.getKey(), month);
        }
      }
      renderChart("Months");
    }
  }

  private void showDailyStatistics(String month, int year) {
    borrowingData = transactionStatistics.aggregateGenreBorrowData(month, year);
    clearChartData();

    if (borrowingData != null && !borrowingData.isEmpty()) {
      for (Map.Entry<String, Map<String, Long>> dayEntry : borrowingData.entrySet()) {
        String day = dayEntry.getKey();
        Map<String, Long> genreCounts = dayEntry.getValue();

        for (Map.Entry<String, Long> genreEntry : genreCounts.entrySet()) {
          chartDataset.setValue(genreEntry.getValue(), genreEntry.getKey(), day);
        }
      }
    }
    //    System.out.println(borrowingData);
    renderChart("Days");
  }

  private void renderChart(String categoryAxisLabel) {
    stackedBarChart =
        JFreeChartGenerator.createStackedBarChart(
            "", categoryAxisLabel, "Number of Books borrowed", chartDataset);
    this.remove(chartPanel);
    chartPanel = new ChartPanel(stackedBarChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void updateReaderData() {
    updateBasedOnComboBox(selectedMonth, selectedYear);
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    selectedMonth = month;
    selectedYear = year;
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      showMonthlyStatistics(selectedYear);
    } else {
      showDailyStatistics(selectedMonth, selectedYear);
    }
  }
}
