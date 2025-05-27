package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.observers.TransactionObserver;
import com.g15.library_system.view.managementView.dashboard.statistics.TransactionStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class LendingTrendsChartPanel extends RoundedShadowPanel implements TransactionObserver {
  private ChartPanel chartPanel;
  private JFreeChart barChart;

  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;
  private ArrayList<Integer> years = new ArrayList<>();
  private String selectedMonth;
  private Integer selectedYear;
  private Map<String, Long> lendingData = new HashMap<>();
  private DefaultCategoryDataset chartDataset;

  LendingTrendsChartPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(730, 450));
    this.setLayout(new BorderLayout());

    // title panel
    TitlePanel titlePn = new TitlePanel("Lending Trends");
    yearComboBox = titlePn.getYearComboBox();
    monthComboBox = titlePn.getMonthComboBox();

    // chart panel
    chartDataset = new DefaultCategoryDataset();

    lendingData =
        TransactionStatistics.aggregateLendingTrendData((int) yearComboBox.getSelectedItem());
    if (lendingData != null && !lendingData.isEmpty()) {
      for (Map.Entry<String, Long> entry : lendingData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }

    barChart =
        JFreeChartGenerator.createBarChart(
            "", "Number of Months", "Number of Books borrowed", chartDataset);

    CategoryPlot plot = (CategoryPlot) barChart.getPlot();
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    BarRenderer renderer = (BarRenderer) plot.getRenderer();

    NumberFormat integerFormat = NumberFormat.getIntegerInstance();

    renderer.setDefaultItemLabelGenerator(
        new StandardCategoryItemLabelGenerator("{2}", integerFormat));
    renderer.setDefaultItemLabelsVisible(true);

    chartPanel = new ChartPanel(barChart);
    this.setLayout(new BorderLayout());
    this.add(chartPanel, BorderLayout.CENTER);
    this.add(titlePn, BorderLayout.NORTH);
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  @Override
  public void update() {}
}
