package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.BookData;
import com.g15.library_system.observers.BookObserver;
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

public class ReturnOverviewChart extends RoundedShadowPanel
    implements BookObserver, FilterObserver {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;
  private String selectedMonth;
  private Integer selectedYear;
  // data
  private TransactionStatistics transactionStatistics = new TransactionStatistics();
  private DefaultPieDataset chartDataset;
  private Map<String, Long> returnOverviewData = new HashMap<>();

  public ReturnOverviewChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(500, 450));
    BookData.getInstance().registerObserver(this);

    // title panel
    TitlePanel titlePn = new TitlePanel("Return Overview");
    this.selectedYear = titlePn.getSelectedYear();
    this.selectedMonth = titlePn.getSelectedMonth();
    // chart panel
    this.chartDataset = new DefaultPieDataset();

    this.returnOverviewData = transactionStatistics.countReturnStatusDistribution(selectedYear);
    if (hasData()) {
      for (Map.Entry<String, Long> entry : returnOverviewData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
    }

    this.pieChart = JFreeChartGenerator.createPieChart("", chartDataset);

    this.chartPanel = new ChartPanel(pieChart);
    titlePn.addObserver(this);
    this.add(chartPanel, BorderLayout.CENTER);
    this.add(titlePn, BorderLayout.NORTH);
  }

  private boolean hasData() {
    return returnOverviewData != null && !returnOverviewData.isEmpty();
  }

  public void clearChartData() {
    this.chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    this.returnOverviewData = transactionStatistics.countReturnStatusDistribution(year);
    clearChartData();
    if (hasData()) {
      for (Map.Entry<String, Long> entry : returnOverviewData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
      renderChart();
    }
  }

  private void showDailyStatistics(String month, int year) {
    this.returnOverviewData = transactionStatistics.countReturnStatusDistribution(month, year);
    clearChartData();

    if (hasData()) {
      for (Map.Entry<String, Long> entry : returnOverviewData.entrySet()) {
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
