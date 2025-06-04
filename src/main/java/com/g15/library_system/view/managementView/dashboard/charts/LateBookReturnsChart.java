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
    setPreferredSize(new Dimension(730, 450));
    setLayout(new BorderLayout());

    ReaderData.getInstance().registerObserver(this);

    TitlePanel titlePanel = new TitlePanel("Late Book Returns Over Time");
    selectedYear = titlePanel.getSelectedYear();
    selectedMonth = titlePanel.getSelectedMonth();

    chartDataset = new DefaultCategoryDataset();
    updateChartData();

    chartPanel = new ChartPanel(barChart);
    titlePanel.addObserver(this);
    add(chartPanel, BorderLayout.CENTER);
    add(titlePanel, BorderLayout.NORTH);
  }

  private void updateChartData() {
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      lendingData = transactionStatistics.aggregateLateReturnTrend(selectedYear);
      updateDataset("Months");
    } else {
      lendingData = transactionStatistics.aggregateLateReturnTrend(selectedMonth, selectedYear);
      updateDataset("Days");
    }
  }

  private void updateDataset(String categoryAxisLabel) {
    chartDataset.clear();
    if (lendingData != null && !lendingData.isEmpty()) {
      lendingData.forEach((key, value) -> chartDataset.setValue(value, "Books", key));
    }
    renderChart(categoryAxisLabel);
  }

  private void renderChart(String categoryAxisLabel) {
    if (chartPanel != null) {
      remove(chartPanel);
    }

    barChart =
        JFreeChartGenerator.createLineChart(
            "", categoryAxisLabel, "Number of Late Returns", chartDataset);

    chartPanel = new ChartPanel(barChart);
    add(chartPanel, BorderLayout.CENTER);
    revalidate();
    repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    selectedMonth = month;
    selectedYear = year;
    updateChartData();
  }

  @Override
  public void updateReaderData() {
    SwingUtilities.invokeLater(
        () -> {
          updateChartData();
        });
  }
}
