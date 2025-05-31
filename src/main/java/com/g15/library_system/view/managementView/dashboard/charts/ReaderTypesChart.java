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
    this.setPreferredSize(new Dimension(500, 450));
    this.setLayout(new BorderLayout());
    initTitlePanel();
    registerObservers();
    initChart();
  }

  private void initTitlePanel() {
    TitlePanel titlePanel = new TitlePanel("Reader Types");
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
    updateChartData(selectedYear);

    pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    chartPanel = new ChartPanel(pieChart);
    this.add(chartPanel, BorderLayout.CENTER);
  }

  private void clearChartData() {
    chartDataset.clear();
  }

  private void updateChart() {
    clearChartData();
    if (selectedMonth == null || selectedMonth.equalsIgnoreCase("All")) {
      updateChartData(selectedYear);
    } else {
      updateChartData(selectedMonth, selectedYear);
    }
  }

  private void updateChartData(int year) {
    readerTypeData = readerStatistics.aggregateReaderTypeData(year);
    updateDataset();
  }

  private void updateChartData(String month, int year) {
    readerTypeData = readerStatistics.aggregateReaderTypeData(month, year);
    updateDataset();
  }

  private void updateDataset() {
    if (hasData()) {
      for (Map.Entry<String, Long> entry : readerTypeData.entrySet()) {
        chartDataset.setValue(entry.getKey(), entry.getValue());
      }
      renderChart();
    }
  }

  private boolean hasData() {
    return readerTypeData != null && !readerTypeData.isEmpty();
  }

  private void renderChart() {
    if (chartPanel != null) {
      this.remove(chartPanel);
    }

    pieChart = JFreeChartGenerator.createPieChart("", chartDataset);
    chartPanel = new ChartPanel(pieChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void updateBasedOnComboBox(String month, int year) {
    this.selectedMonth = month;
    this.selectedYear = year;
    this.updateChart();
  }

  @Override
  public void updateReaderData() {
    System.out.println("Reader data updated.");
    SwingUtilities.invokeLater(() -> {
      this.updateChart();
    });
  }
}
