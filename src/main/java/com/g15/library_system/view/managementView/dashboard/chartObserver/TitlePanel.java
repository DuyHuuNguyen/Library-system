package com.g15.library_system.view.managementView.dashboard.chartObserver;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import com.g15.library_system.view.swingComponentGenerators.MonthComboBoxGenerator;
import com.g15.library_system.view.swingComponentGenerators.YearComboBoxGenerator;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import lombok.Getter;
import lombok.Setter;

public class TitlePanel extends JPanel implements ChartFilterSubject {
  @Setter private String title;
  @Getter private JComboBox<Integer> yearComboBox;
  @Getter private JComboBox<String> monthComboBox;
  @Setter private List<FilterObserver> observers = new ArrayList<>();

  public TitlePanel(String title) {
    this.title = title;

    this.setLayout(new BorderLayout());
    this.setOpaque(false);
    JLabel chartTitle =
        LabelGenerator.createLabel(
            "   " + title, Style.FONT_BOLD_18, Color.BLACK, SwingConstants.LEFT);

    JPanel sortBarPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    sortBarPn.setBackground(Color.WHITE);

    JLabel monthLabel =
        LabelGenerator.createLabel("Month", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);

    this.monthComboBox = MonthComboBoxGenerator.createMonthComboBox();
    this.monthComboBox.addActionListener(
        e -> {
          notifyObservers();
        });

    JLabel yearLabel =
        LabelGenerator.createLabel(" Year", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);

    this.yearComboBox = YearComboBoxGenerator.createYearComboBox(2005);
    this.yearComboBox.addActionListener(
        e -> {
          notifyObservers();
        });

    sortBarPn.add(monthLabel);
    sortBarPn.add(monthComboBox);
    sortBarPn.add(yearLabel);
    sortBarPn.add(yearComboBox);
    this.add(chartTitle, BorderLayout.WEST);
    this.add(sortBarPn, BorderLayout.EAST);
  }

  public String getSelectedMonth() {
    return (String) monthComboBox.getSelectedItem();
  }
    public int getSelectedYear() {
        return (Integer) yearComboBox.getSelectedItem();
    }

  @Override
  public void addObserver(FilterObserver observer) {
    this.observers.add(observer);
  }

  @Override
  public void removeObserver(FilterObserver observer) {
    if (!this.observers.isEmpty()) {
      this.observers.remove(observer);
    }
  }

  @Override
  public void notifyObservers() {
    String selectedMonth = (String) monthComboBox.getSelectedItem();
    int selectedYear = (Integer) yearComboBox.getSelectedItem();
    for (FilterObserver observer : observers) {
      observer.updateBasedOnComboBox(selectedMonth, selectedYear);
    }
  }
}
