package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.observers.BookLendedObserver;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.dashboard.statistics.BookStatistics;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import com.g15.library_system.view.swingComponentGenerators.MonthComboBoxGenerator;
import com.g15.library_system.view.swingComponentGenerators.YearComboBoxGenerator;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class BookAvailabilityChartPanel extends RoundedShadowPanel implements BookLendedObserver {
  private ChartPanel chartPanel;
  private JFreeChart pieChart;
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;


  private final List<Integer> years = new ArrayList<>();
  private Map<String, Long> bookAvailabilityData = new HashMap<>();
  private DefaultPieDataset chartDataset;
  public BookAvailabilityChartPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(500, 450));

    bookAvailabilityData = BookStatistics.aggregateBookAvailabilityData();
    pieChart = JFreeChartGenerator.createPieChart("", bookAvailabilityData);
    PiePlot plot = (PiePlot) pieChart.getPlot();
    chartDataset = (DefaultPieDataset) plot.getDataset();

    chartPanel = new ChartPanel(pieChart);
    add(chartPanel, BorderLayout.CENTER);

    TitlePn titlePn = new TitlePn();
    add(titlePn, BorderLayout.NORTH);
  }

  private class TitlePn extends JPanel {
    public TitlePn() {
      this.setLayout(new BorderLayout());
      this.setOpaque(false);
      JLabel chartTitle =
              LabelGenerator.createLabel(
                      " Books Availability", Style.FONT_BOLD_18, Color.BLACK, SwingConstants.LEFT);

      JPanel sortBarPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
      sortBarPn.setBackground(Color.WHITE);

      JLabel monthLabel =
              LabelGenerator.createLabel("Month", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);

      monthComboBox = MonthComboBoxGenerator.createMonthComboBox();
      monthComboBox.addActionListener(e -> {});

      JLabel yearLabel =
              LabelGenerator.createLabel(" Year", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);
      years.add(2025);
      years.add(2024);
      years.add(2023);

      yearComboBox = YearComboBoxGenerator.createYearComboBox(2000);
      yearComboBox.addActionListener(e -> {});


      sortBarPn.add(monthLabel);
      sortBarPn.add(monthComboBox);
      sortBarPn.add(yearLabel);
      sortBarPn.add(yearComboBox);
      this.add(chartTitle, BorderLayout.WEST);
      this.add(sortBarPn, BorderLayout.EAST);
    }
  }

  public void clearChartData() {
    chartDataset.clear();
  }

  @Override
  public void update() {
    clearChartData();

    Map<String, Long> newData = BookStatistics.aggregateBookAvailabilityData();
    for (Map.Entry<String, ? extends Number> entry : newData.entrySet()) {
      chartDataset.setValue(entry.getKey(), entry.getValue());
    }
    chartPanel.repaint();
  }
}
