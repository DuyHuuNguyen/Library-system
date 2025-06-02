package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.TransactionData;
import com.g15.library_system.observers.TransactionObserver;
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
    implements FilterObserver, TransactionObserver {
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
    initTitlePanel();
    registerObservers();
    initChart();
  }

  private void initTitlePanel() {
    TitlePanel titlePanel = new TitlePanel("Book Borrows By Genre");
    this.selectedYear = titlePanel.getSelectedYear();
    this.selectedMonth = titlePanel.getSelectedMonth();
    titlePanel.addObserver(this);
    this.add(titlePanel, BorderLayout.NORTH);
  }

  private void registerObservers() {
    TransactionData.getInstance().registerObserver(this);
  }

  private void initChart() {
    chartDataset = new DefaultCategoryDataset();
    updateChartDataByMonth(selectedYear);

    stackedBarChart =
        JFreeChartGenerator.createStackedBarChart("", "Months", "Books borrowed", chartDataset);

    chartPanel = new ChartPanel(stackedBarChart);
    this.add(chartPanel, BorderLayout.CENTER);
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  private void updateChart() {
    clearChartData();
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      updateChartDataByMonth(selectedYear);
    } else {
      updateChartDataByDay(selectedMonth, selectedYear);
    }
  }

  private void updateChartDataByMonth(int year) {
    updateChartData(transactionStatistics.aggregateGenreBorrowData(year), "Months");
  }

  private void updateChartDataByDay(String month, int year) {
    updateChartData(transactionStatistics.aggregateGenreBorrowData(month, year), "Days");
  }

  private void updateChartData(Map<String, Map<String, Long>> data, String categoryAxisLabel) {
    if (data != null && !data.isEmpty()) {
      for (Map.Entry<String, Map<String, Long>> entry : data.entrySet()) {
        String timeLabel = entry.getKey();
        Map<String, Long> genreCounts = entry.getValue();

        for (Map.Entry<String, Long> genreEntry : genreCounts.entrySet()) {
          chartDataset.setValue(genreEntry.getValue(), genreEntry.getKey(), timeLabel);
        }
      }
      renderChart(categoryAxisLabel);
    }
  }

  private void renderChart(String categoryAxisLabel) {
    if (chartPanel != null) {
      this.remove(chartPanel);
    }

    stackedBarChart =
        JFreeChartGenerator.createStackedBarChart(
            "", categoryAxisLabel, "Number of Books borrowed", chartDataset);

    chartPanel = new ChartPanel(stackedBarChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    this.selectedMonth = month;
    this.selectedYear = year;
    updateChart();
  }

  @Override
  public void updateTransactionData() {
    System.out.println("updated lending.==================================================");
    SwingUtilities.invokeLater(
        () -> {
          transactionStatistics = new TransactionStatistics();

          clearChartData();
          updateChart();

          revalidate();
          repaint();
        });
  }
}
