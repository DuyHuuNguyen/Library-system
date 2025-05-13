package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.managementView.dashboard.statistics.TransactionStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

public class ReaderSignupTrendsChart extends RoundedShadowPanel {
  private ChartPanel chartPanel;
  private JFreeChart lineChart;

  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;

  private ArrayList<Integer> years = new ArrayList<>();
  private Map<String, Long> readerSignUpTrendsData;
  private DefaultCategoryDataset chartDataset;


  ReaderSignupTrendsChart() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    setPreferredSize(new Dimension(700, 450));
    this.setLayout(new BorderLayout());
    this.setBackground(Color.WHITE);
    setLayout(new BorderLayout());

    TitlePanel titlePn = new TitlePanel("Reader Signup Trends");
    this.yearComboBox= titlePn.getYearComboBox();
    this.monthComboBox= titlePn.getMonthComboBox();


    chartDataset = new DefaultCategoryDataset();

    readerSignUpTrendsData = TransactionStatistics.aggregateLendingTrendData((int) yearComboBox.getSelectedItem());
    if (readerSignUpTrendsData != null && !readerSignUpTrendsData.isEmpty()) {
      for (Map.Entry<String, Long> entry : readerSignUpTrendsData.entrySet()) {
        chartDataset.setValue(entry.getValue(), "Books", entry.getKey());
      }
    }


    lineChart = JFreeChartGenerator.createLineChart(
            "", "Number of Months", "Number of Books borrowed", chartDataset);

    CategoryPlot plot = (CategoryPlot) lineChart.getPlot();
    NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();

    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    plot.setRenderer(renderer);

    NumberFormat integerFormat = NumberFormat.getIntegerInstance();

    renderer.setDefaultItemLabelGenerator(
            new StandardCategoryItemLabelGenerator("{2}", integerFormat));
    renderer.setDefaultItemLabelsVisible(true);


     chartPanel = new ChartPanel(lineChart);
    this.add(chartPanel, BorderLayout.CENTER);
    this.add(titlePn, BorderLayout.NORTH);
  }

}
