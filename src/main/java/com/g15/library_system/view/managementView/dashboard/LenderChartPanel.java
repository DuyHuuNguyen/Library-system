package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentGenerators.JFreeChartGenerator;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import com.g15.library_system.view.swingComponentGenerators.MonthComboBoxGenerator;
import com.g15.library_system.view.swingComponentGenerators.YearComboBoxGenerator;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

public class LenderChartPanel extends RoundedShadowPanel {
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
  private ArrayList<Integer> years = new ArrayList<>();
  private Map<String, Integer> lendingData;

  LenderChartPanel() {
    super(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
    setPreferredSize(new Dimension(700, 450));
    this.setLayout(new BorderLayout());
    this.setBackground(Color.WHITE);
    setLayout(new BorderLayout());

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
    monthComboBox.addActionListener(
        e -> {
          //            selectedMonth = (String) monthComboBox.getSelectedItem();
          //            selectedYear = (Integer) yearComboBox.getSelectedItem();
          //            if(selectedMonth != null) {
          //                updateData(selectedMonth,selectedYear);
          //                if(selectedMonth.equals("All")) {
          //                    CategoryAxis categoryAxis = revenuePlot.getDomainAxis();
          //                    categoryAxis.setLabel("Months");
          //                }else{
          //                    CategoryAxis categoryAxis = revenuePlot.getDomainAxis();
          //                    categoryAxis.setLabel("Days");
          //                }
          //
          //                revenueChartPanel.repaint();
          //            }
        });

    JLabel yearLabel =
        LabelGenerator.createLabel(" Year", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);
    years.add(2025);
    years.add(2024);
    years.add(2023);

    yearComboBox = YearComboBoxGenerator.createYearComboBox(2000);
    yearComboBox.addActionListener(
        e -> {
          //                    selectedMonth = (String) monthComboBox.getSelectedItem();
          //                    selectedYear = (Integer) yearComboBox.getSelectedItem();
          //                    if (selectedYear != null) {
          //                        updateData(selectedMonth,selectedYear);
          //                        revenueChartPanel.repaint();
          //                    }
        });

    //        updateData((String) monthComboBox.getSelectedItem(), LocalDate.now().getYear());
    //
    //        revenueChart = createChart(revenueData);
    //        revenueChartPanel = new ChartPanel(revenueChart);

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
      lendingData =
          Map.ofEntries(
              Map.entry("Jan", 120),
              Map.entry("Feb", 135),
              Map.entry("Mar", 128),
              Map.entry("Apr", 142),
              Map.entry("May", 155),
              Map.entry("Jun", 148),
              Map.entry("Jul", 160),
              Map.entry("Aug", 500),
              Map.entry("Sep", 165),
              Map.entry("Oct", 178),
              Map.entry("Nov", 185),
              Map.entry("Dec", 192));
      JPanel chart =
          JFreeChartGenerator.createLineChart(
              "", "Number of Months", "Number of Books borrowed", lendingData);

      this.add(chart);
    }
  }
}
