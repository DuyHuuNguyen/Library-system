package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class LendingTrendsChartPanel extends RoundedShadowPanel {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;

  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;
  private ArrayList<Integer> years = new ArrayList<>();
  private String selectedMonth;
  private Integer selectedYear;
  private Map<String, Long> lendingData = new HashMap<>();

  LendingTrendsChartPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    this.setPreferredSize(new Dimension(730, 450));
    this.setLayout(new BorderLayout());

    JFreeChart chart =
            JFreeChartGenerator.createBarChart(
                    "", "Number of Months", "Number of Books borrowed", lendingData);

    chartPanel = new ChartPanel(chart);
    this.add(chartPanel, BorderLayout.CENTER);



    TitlePanel titlePn = new TitlePanel("Lending Trends");
    add(titlePn, BorderLayout.NORTH);

  }

//  public void clearChartData() {
//    chartDataset.clear();
//  }

}
