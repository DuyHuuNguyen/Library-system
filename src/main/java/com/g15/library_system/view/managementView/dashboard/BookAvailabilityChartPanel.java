package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import com.g15.library_system.view.swingComponentGenerators.MonthComboBoxGenerator;
import com.g15.library_system.view.swingComponentGenerators.YearComboBoxGenerator;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.*;

public class BookAvailabilityChartPanel extends RoundedShadowPanel {
  private JComboBox<Integer> yearComboBox;
  private JComboBox<String> monthComboBox;
  private String[] months = {
    "All",
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  };
  private List<Integer> years = new ArrayList<Integer>();
  private Map<String, Integer> lendingData;

  private Map<String, Integer> bookAvailabilityData;

  public BookAvailabilityChartPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(480, 450));

    JPanel titlePn = new JPanel(new GridLayout(1, 2));
    titlePn.setBackground(Color.WHITE);
    JLabel chartTitle =
        LabelGenerator.createLabel(
            "   Books Lending Trends", Style.FONT_BOLD_18, Color.BLACK, SwingConstants.LEFT);

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
    titlePn.add(chartTitle);
    titlePn.add(sortBarPn);
    add(titlePn, BorderLayout.NORTH);
    ChartPanel chartPanel = new ChartPanel();
    add(chartPanel, BorderLayout.CENTER);
  }

  public class ChartPanel extends JPanel {
    ChartPanel() {
      setLayout(new BorderLayout());
      bookAvailabilityData = new HashMap<>();
      bookAvailabilityData.put("Available", 120);
      bookAvailabilityData.put("Borrowed", 25);
      bookAvailabilityData.put("Returned", 35);
      JPanel chart = JFreeChartGenerator.createPieChart("", bookAvailabilityData);

      this.add(chart);
    }
  }
}
