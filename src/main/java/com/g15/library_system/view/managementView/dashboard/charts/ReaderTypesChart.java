package com.g15.library_system.view.managementView.dashboard.charts;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.FilterObserver;
import com.g15.library_system.view.managementView.dashboard.chartObserver.TitlePanel;
import com.g15.library_system.view.managementView.dashboard.statistics.ReaderStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ReaderTypesChart extends RoundedShadowPanel implements ReaderObserver, FilterObserver {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;
  private String selectedMonth;
  private Integer selectedYear;
  // data
  private ReaderStatistics readerStatistics = new ReaderStatistics();
  private DefaultPieDataset chartDataset;
  private Map<String, Long> readerTypeData = new HashMap<>();

  public ReaderTypesChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setLayout(new BorderLayout());
    this.setPreferredSize(new Dimension(500, 450));
    ReaderData.getInstance().registerObserver(this);

    // title panel
    TitlePanel titlePn = new TitlePanel("Reader Types");
    this.selectedYear = titlePn.getSelectedYear();
    this.selectedMonth = titlePn.getSelectedMonth();
    // chart panel
    this.chartDataset = new DefaultPieDataset();

    this.readerTypeData = readerStatistics.aggregateReaderTypeData(selectedYear);
    if (hasData()) {
      for (Map.Entry<String, Long> entry : readerTypeData.entrySet()) {
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
    return readerTypeData != null && !readerTypeData.isEmpty();
  }

  public void clearChartData() {
    this.chartDataset.clear();
  }

  private void showMonthlyStatistics(int year) {
    this.readerTypeData = readerStatistics.aggregateReaderTypeData(year);
    clearChartData();
    if (hasData()) {
      for (Map.Entry<String, Long> entry : readerTypeData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
      renderChart();
    }
  }

  private void showDailyStatistics(String month, int year) {
    this.readerTypeData = readerStatistics.aggregateReaderTypeData(month, year);
    clearChartData();

    if (hasData()) {
      for (Map.Entry<String, Long> entry : readerTypeData.entrySet()) {
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
  public void updateReaderData() {
    updateBasedOnComboBox(selectedMonth, selectedYear);
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
}
