package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.TransactionStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class BorrowOverviewChart extends RoundedShadowPanel
    implements ReaderObserver, FilterObserver {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;
  private String selectedMonth;
  private Integer selectedYear;
  // data
  private TransactionStatistics transactionStatistics = new TransactionStatistics();
  private DefaultPieDataset chartDataset;
  private Map<String, Long> returnOverviewData = new HashMap<>();

  public BorrowOverviewChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(500, 450));
    this.setLayout(new BorderLayout());
    initTitlePanel();
    registerObservers();
    initChart();
  }

  private void initTitlePanel() {
    TitlePanel titlePanel = new TitlePanel("Borrow Overview");
    this.selectedYear = titlePanel.getSelectedYear();
    this.selectedMonth = titlePanel.getSelectedMonth();
    titlePanel.addObserver(this);
    this.add(titlePanel, BorderLayout.NORTH);
  }

  private void registerObservers() {
    ReaderData.getInstance().registerObserver(this);
  }

  private void initChart() {
    chartDataset = new DefaultPieDataset();
    updateChartDataByMonth(selectedYear);

    pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    chartPanel = new ChartPanel(pieChart);
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
    updateChartData(transactionStatistics.countReturnStatusDistribution(year));
  }

  private void updateChartDataByDay(String month, int year) {
    updateChartData(transactionStatistics.countReturnStatusDistribution(month, year));
  }

  private void updateChartData(Map<String, Long> data) {
    if (data != null && !data.isEmpty()) {
      data.forEach((label, value) -> chartDataset.setValue(label, value));
      renderChart();
    }
  }

  private void renderChart() {
    if (chartPanel != null) {
      this.remove(chartPanel);
    }

    pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    chartPanel = new ChartPanel(pieChart);
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
    SwingUtilities.invokeLater(
        () -> {
          //      System.out.println("BorrowOverviewChart: updateReaderData called");
          updateChart();
        });
  }
}
