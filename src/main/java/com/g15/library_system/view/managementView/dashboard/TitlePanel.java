package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import com.g15.library_system.view.swingComponentGenerators.MonthComboBoxGenerator;
import com.g15.library_system.view.swingComponentGenerators.YearComboBoxGenerator;

import javax.swing.*;
import java.awt.*;

public class TitlePanel extends JPanel {
    private String title;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> monthComboBox;

    public TitlePanel(String title) {
        this.title = title;

        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        JLabel chartTitle =
                LabelGenerator.createLabel(
                        "   "+title, Style.FONT_BOLD_18, Color.BLACK, SwingConstants.LEFT);

        JPanel sortBarPn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        sortBarPn.setBackground(Color.WHITE);

        JLabel monthLabel =
                LabelGenerator.createLabel("Month", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);

        this.monthComboBox = MonthComboBoxGenerator.createMonthComboBox();
        this.monthComboBox.addActionListener(e -> {});

        JLabel yearLabel =
                LabelGenerator.createLabel(" Year", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.CENTER);

        this.yearComboBox = YearComboBoxGenerator.createYearComboBox(2000);
        this.yearComboBox.addActionListener(e -> {});


        sortBarPn.add(monthLabel);
        sortBarPn.add(monthComboBox);
        sortBarPn.add(yearLabel);
        sortBarPn.add(yearComboBox);
        this.add(chartTitle, BorderLayout.WEST);
        this.add(sortBarPn, BorderLayout.EAST);




    }






}
