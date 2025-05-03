package com.g15.library_system.view.overrideComponent.dateChoosers.otherCalendar;

import com.formdev.flatlaf.FlatLightLaf;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import java.time.LocalDate;
import javax.swing.*;

public class DateRangeExample {
  public static void main(String[] args) {
    JFrame frame = new JFrame("Chọn khoảng thời gian");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 200);
    frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception e) {
      e.printStackTrace();
    }
    // Tạo 2 DatePicker: bắt đầu và kết thúc
    DatePickerSettings startSettings = new DatePickerSettings();
    startSettings.setAllowEmptyDates(false);
    DatePicker startDatePicker = new DatePicker(startSettings);
    startDatePicker.setDateToToday();

    DatePickerSettings endSettings = new DatePickerSettings();
    endSettings.setAllowEmptyDates(false);
    DatePicker endDatePicker = new DatePicker(endSettings);
    endDatePicker.setDateToToday();

    frame.add(new JLabel("Ngày bắt đầu:"));
    frame.add(startDatePicker);
    frame.add(new JLabel("Ngày kết thúc:"));
    frame.add(endDatePicker);

    JButton btnXemKhoang = new JButton("In khoảng thời gian");
    btnXemKhoang.addActionListener(
        e -> {
          LocalDate start = startDatePicker.getDate();
          LocalDate end = endDatePicker.getDate();
          JOptionPane.showMessageDialog(frame, "Từ ngày: " + start + " đến ngày: " + end);
        });

    frame.add(btnXemKhoang);
    frame.setVisible(true);
  }
}
