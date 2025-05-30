package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.BookData;
import com.g15.library_system.observers.BookObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.BookStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class BookAvailabilityChart extends RoundedShadowPanel
    implements BookObserver, FilterObserver {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;
  private String selectedMonth;
  private Integer selectedYear;

  private BookStatistics bookStatistics = new BookStatistics();
  private final List<Integer> years = new ArrayList<>();
  private Map<String, Long> bookAvailabilityData = new HashMap<>();
  private DefaultPieDataset chartDataset;

  public BookAvailabilityChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(500, 450));
    initTitlePanel();
    registerObservers();
    initChart();
  }

  private void initTitlePanel() {
    TitlePanel titlePanel = new TitlePanel("Book Availability");
    this.selectedMonth = titlePanel.getSelectedMonth();
    this.selectedYear = titlePanel.getSelectedYear();
    titlePanel.addObserver(this);
    this.add(titlePanel, BorderLayout.NORTH);
  }

  private void registerObservers() {
    BookData.getInstance().registerObserver(this);
  }

  private void initChart() {
    chartDataset = new DefaultPieDataset();
    bookAvailabilityData = bookStatistics.aggregateBookAvailabilityData(selectedYear);
    for (Map.Entry<String, Long> entry : bookAvailabilityData.entrySet()) {
      chartDataset.setValue(entry.getKey(), entry.getValue());
    }

    pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    chartPanel = new ChartPanel(pieChart);
    this.add(chartPanel, BorderLayout.CENTER);
  }

  private boolean hasData() {
    return bookAvailabilityData != null && !bookAvailabilityData.isEmpty();
  }

  public void clearChartData() {
    this.chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    this.bookAvailabilityData = bookStatistics.aggregateBookAvailabilityData(year);
    clearChartData();
    if (hasData()) {
      for (Map.Entry<String, Long> entry : bookAvailabilityData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
      renderChart();
    }
  }

  private void showDailyStatistics(String month, int year) {
    this.bookAvailabilityData = bookStatistics.aggregateBookAvailabilityData(month, year);
    clearChartData();

    if (hasData()) {
      for (Map.Entry<String, Long> entry : bookAvailabilityData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
    }
    renderChart();
  }

  private void renderChart() {
    this.pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    this.remove(chartPanel);
    this.chartPanel = new ChartPanel(pieChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    this.selectedMonth = month;
    this.selectedYear = year;
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      showMonthlyStatistics(selectedYear);
    } else {
      showDailyStatistics(selectedMonth, selectedYear);
    }
  }

  @Override
  public void updateBookData() {
    updateBasedOnComboBox(selectedMonth, selectedYear);
  }
}
