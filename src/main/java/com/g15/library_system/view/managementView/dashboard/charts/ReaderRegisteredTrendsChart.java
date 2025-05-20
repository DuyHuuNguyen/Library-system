package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.ReaderStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.Map;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class ReaderRegisteredTrendsChart extends RoundedShadowPanel
        implements FilterObserver, ReaderObserver {

  private ChartPanel chartPanel;
  private JFreeChart lineChart;
  private DefaultCategoryDataset chartDataset;

  private String selectedMonth;
  private Integer selectedYear;

  private final ReaderStatistics readerStatistics = new ReaderStatistics();
  private Map<String, Long> readerSignUpTrendsData;

  public ReaderRegisteredTrendsChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(700, 450));
    this.setLayout(new BorderLayout());
    initTitlePanel();
    registerObservers();
    initChart();
  }

  private void initTitlePanel() {
    TitlePanel titlePanel = new TitlePanel("Reader Registered Trends");
    this.selectedYear = titlePanel.getSelectedYear();
    this.selectedMonth = titlePanel.getSelectedMonth();
    titlePanel.addObserver(this);

    this.add(titlePanel, BorderLayout.NORTH);
  }

  private void registerObservers() {
    ReaderData.getInstance().registerObserver(this);
  }

  private void initChart() {
    chartDataset = new DefaultCategoryDataset();
    updateChartDataByMonth(selectedYear);

    lineChart = JFreeChartGenerator.createLineChart(
            "", "Months", "Number of registered readers", chartDataset);

    chartPanel = new ChartPanel(lineChart);
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
    updateChartData(readerStatistics.aggregateReaderSignUpTrendData(year), "Months");
  }

  private void updateChartDataByDay(String month, int year) {
    updateChartData(readerStatistics.aggregateReaderSignUpTrendData(month, year), "Days");
  }

  private void updateChartData(Map<String, Long> data, String categoryAxisLabel) {
    if (data != null && !data.isEmpty()) {
      data.forEach((label, value) -> chartDataset.setValue(value, "Books", label));
      renderChart(categoryAxisLabel);
    }
  }

  private void renderChart(String categoryAxisLabel) {
    if (chartPanel != null) {
      this.remove(chartPanel);
    }

    lineChart = JFreeChartGenerator.createLineChart(
            "", categoryAxisLabel, "Number of registered readers", chartDataset);

    chartPanel = new ChartPanel(lineChart);
    this.add(chartPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    this.selectedMonth = month;
    this.selectedYear = year;
    updateChart();
  }

  @Override
  public void updateReaderData() {
    updateChart();
  }
}

