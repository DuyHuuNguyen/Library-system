package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.ReaderStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ReaderRegisteredTrendsChart extends RoundedShadowPanel
    implements FilterObserver, ReaderObserver {
  private ChartPanel chartPanel;
  private JFreeChart lineChart;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;
  private String selectedMonth;
  private Integer selectedYear;
  // statistics data
  private ReaderStatistics readerStatistics = new ReaderStatistics();

  // chart data
  private Map<String, Long> readerSignUpTrendsData;
  private DefaultCategoryDataset chartDataset;

  public ReaderRegisteredTrendsChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    setPreferredSize(new Dimension(700, 450));
    this.setLayout(new BorderLayout());
    this.setBackground(Color.WHITE);
    setLayout(new BorderLayout());

    TitlePanel titlePn = new TitlePanel("Reader Registered Trends");
    this.yearComboBox = titlePn.getYearComboBox();
    this.monthComboBox = titlePn.getMonthComboBox();
    this.yearComboBox.setSelectedItem(2024);

    // chart panel
    chartDataset = new DefaultCategoryDataset();
    readerSignUpTrendsData =
        readerStatistics.aggregateReaderSignUpTrendData((int) yearComboBox.getSelectedItem());
    if (readerSignUpTrendsData != null && !readerSignUpTrendsData.isEmpty()) {
      for (Map.Entry<String, Long> entry : readerSignUpTrendsData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }

    lineChart =
        JFreeChartGenerator.createLineChart(
            "", "Months", "Number of registered readers", chartDataset);

    chartPanel = new ChartPanel(lineChart);
    this.add(chartPanel, BorderLayout.CENTER);
    titlePn.addObserver(this);
    this.add(titlePn, BorderLayout.NORTH);
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    readerSignUpTrendsData = readerStatistics.aggregateReaderSignUpTrendData(year);
    clearChartData();

    if (readerSignUpTrendsData != null && !readerSignUpTrendsData.isEmpty()) {
      for (Map.Entry<String, Long> entry : readerSignUpTrendsData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
      renderChart("Months");
    }
  }

  private void showDailyStatistics(String month, int year) {
    readerSignUpTrendsData = readerStatistics.aggregateReaderSignUpTrendData(month, year);
    clearChartData();

    if (readerSignUpTrendsData != null && !readerSignUpTrendsData.isEmpty()) {
      for (Map.Entry<String, Long> entry : readerSignUpTrendsData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }
    renderChart("Days");
  }

  private void renderChart(String categoryAxisLabel) {
    lineChart =
        JFreeChartGenerator.createLineChart(
            "", categoryAxisLabel, "Number of registered readers", chartDataset);
    this.remove(chartPanel);
    chartPanel = new ChartPanel(lineChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    clearChartData();
    selectedMonth = month;
    selectedYear = year;
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      showMonthlyStatistics(selectedYear);
    } else {
      showDailyStatistics(selectedMonth, selectedYear);
    }
  }

  @Override
  public void updateReaderData() {
    updateBasedOnComboBox(selectedMonth, selectedYear);
  }
}
