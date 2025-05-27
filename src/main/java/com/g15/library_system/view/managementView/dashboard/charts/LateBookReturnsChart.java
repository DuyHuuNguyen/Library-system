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
import org.jfree.data.category.DefaultCategoryDataset;

public class LateBookReturnsChart extends RoundedShadowPanel
    implements ReaderObserver, FilterObserver {
  private ChartPanel chartPanel;
  private JFreeChart barChart;

  private TransactionStatistics transactionStatistics = new TransactionStatistics();
  private String selectedMonth;
  private Integer selectedYear;
  private Map<String, Long> lendingData = new HashMap<>();
  private DefaultCategoryDataset chartDataset;

  public LateBookReturnsChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(730, 450));
    this.setLayout(new BorderLayout());
    ReaderData.getInstance().registerObserver(this); // important to update the chart*
    // title panel
    TitlePanel titlePn = new TitlePanel("Late Book Returns Over Time");
    this.selectedYear = titlePn.getSelectedYear();
    this.selectedMonth = titlePn.getSelectedMonth();

    // chart panel
    chartDataset = new DefaultCategoryDataset();

    lendingData = transactionStatistics.aggregateLateReturnTrend(selectedYear);
    if (lendingData != null && !lendingData.isEmpty()) {
      for (Map.Entry<String, Long> entry : lendingData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }

    barChart =
        JFreeChartGenerator.createLineChart("", "Months", "Number of Late Returns", chartDataset);

    chartPanel = new ChartPanel(barChart);
    titlePn.addObserver(this);
    this.add(chartPanel, BorderLayout.CENTER);
    this.add(titlePn, BorderLayout.NORTH);
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    lendingData = transactionStatistics.aggregateLateReturnTrend(year);
    clearChartData();

    if (lendingData != null && !lendingData.isEmpty()) {
      for (Map.Entry<String, Long> entry : lendingData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
      renderChart("Months");
    }
  }

  private void showDailyStatistics(String month, int year) {
    lendingData = transactionStatistics.aggregateLateReturnTrend(month, year);
    clearChartData();

    if (lendingData != null && !lendingData.isEmpty()) {
      for (Map.Entry<String, Long> entry : lendingData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }
    renderChart("Days");
  }

  private void renderChart(String categoryAxisLabel) {
    barChart =
        JFreeChartGenerator.createLineChart(
            "", categoryAxisLabel, "Number of Late Returns", chartDataset);
    this.remove(chartPanel);
    chartPanel = new ChartPanel(barChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
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

  @Override
  public void updateReaderData() {
    updateBasedOnComboBox(selectedMonth, selectedYear);
  }
}
