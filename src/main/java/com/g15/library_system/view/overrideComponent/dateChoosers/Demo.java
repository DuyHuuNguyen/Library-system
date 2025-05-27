package com.g15.library_system.view.overrideComponent.dateChoosers;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAction;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAdapter;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import javax.swing.*;

public class Demo extends JFrame {

  public Demo() {
    setTitle("Date Chooser Demo");
    setSize(new Dimension(500, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception e) {
      e.printStackTrace();
    }
    init();
  }

  private void init() {
    getContentPane().setLayout(new BorderLayout());
    JPanel panel = new JPanel(new FlowLayout());
    getContentPane().add(panel);
    DateChooser ch = new DateChooser();

    // disable past date
    ch.setDateSelectable(
        new DateSelectable() {
          @Override
          public boolean isDateSelectable(Date date) {
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            return !localDate.isBefore(LocalDate.now());
          }
        });
    ch.addActionDateChooserListener(
        new DateChooserAdapter() {
          @Override
          public void dateChanged(Date date, DateChooserAction action) {
            System.out.println("date single selected...");
          }

          @Override
          public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
            System.out.println(date);
          }
        });

    JTextField txt = new JTextField(30);
    ch.setTextField(txt);
    panel.add(txt);

    JButton cmd = new JButton("Selected Date");
    ch.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
    // ch.toDay();
    //    cmd.addActionListener(
    //        e -> {
    //           SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
    //           DateBetween between = ch.getSelectedDateBetween();
    //            System.out.println(df.format(between.getFromDate()) + " to " +
    //           df.format(between.getToDate()));
    //
    //            System.out.println(df.format(ch.getSelectedDate()));
    //          ch.setSelectedDateBetween(3, 3, 2022, 5, 7, 2022, true);
    //        });
    panel.add(cmd);
    JButton cmdMode = new JButton("Change theme");
    cmdMode.addActionListener(
        e -> {
          if (!FlatLaf.isLafDark()) {
            EventQueue.invokeLater(
                () -> {
                  FlatAnimatedLafChange.showSnapshot();
                  FlatDarculaLaf.setup();
                  FlatLaf.updateUI();
                  FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
          } else {
            EventQueue.invokeLater(
                () -> {
                  FlatAnimatedLafChange.showSnapshot();
                  FlatLightLaf.setup();
                  FlatLaf.updateUI();
                  FlatAnimatedLafChange.hideSnapshotWithAnimation();
                });
          }
        });
    panel.add(cmdMode);
  }

  public static void main(String[] args) {
    //        FlatRobotoFont.install();
    FlatLaf.registerCustomDefaultsSource("com.raven.datechooser.demo");
    UIManager.put("defaultFont", new Font("Arial", Font.PLAIN, 13));
    FlatDarculaLaf.setup();
    EventQueue.invokeLater(() -> new Demo().setVisible(true));
  }
}
