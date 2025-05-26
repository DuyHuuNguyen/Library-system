package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.entity.Transaction;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.SwitchButton;
import com.g15.library_system.view.overrideComponent.dateChoosers.DateChooser;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAction;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAdapter;
import com.g15.library_system.view.swingComponentBuilders.LabelBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import javax.swing.*;
import javax.swing.border.Border;

public class DetailPanel extends JPanel {
  private JLabel lendDateL, dueDateL, notificationL;
  private JTextField lendDateTF, dueDateTF;
  private DateChooser lendDateChooser, dueDateChooser;
  private SwitchButton notificationSB;

  public DetailPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "Lending Details", 0, 0, Style.FONT_BOLD_20);
    Border padding = BorderFactory.createEmptyBorder(0, 20, 10, 20);
    setBorder(BorderFactory.createCompoundBorder(padding, titled));
    setOpaque(false);
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);

    dueDateL = LabelGenerator.createRequireLabel("Due Date");
    dueDateTF =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(new Dimension(300, 25));

    dueDateChooser = new DateChooser();
    dueDateChooser.setSelectedDate(Date.valueOf(LocalDate.now().plusWeeks(1)));
    dueDateChooser.setDateSelectable(
        date -> {
          LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          return !localDate.isBefore(LocalDate.now()); // this disable past date
        });
    dueDateChooser.setTextField(dueDateTF);

    lendDateL = LabelGenerator.createRequireLabel("Lending Date");
    lendDateTF =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(new Dimension(300, 25));

    lendDateChooser = new DateChooser();
    lendDateChooser.setTextField(lendDateTF);
    lendDateChooser.getSelectedDate();
    lendDateChooser.addActionDateChooserListener(
        new DateChooserAdapter() {
          @Override
          public void dateChanged(java.util.Date date, DateChooserAction action) {
            super.dateChanged(date, action);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            dueDateChooser.setSelectedDate(calendar.getTime());
          }
        });
    lendDateChooser.setDateSelectable(
        date -> {
          LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
          return !localDate.isBefore(LocalDate.now()); // this disable past date
        });

    notificationL =
        LabelBuilder.builder()
            .text("Notification")
            .font(Style.FONT_PLAIN_13)
            .horizontal(SwingConstants.LEFT);
    notificationSB = new SwitchButton();
    notificationSB.doClick();

    gbc.gridy++;
    add(lendDateL, gbc);
    gbc.gridy++;
    add(lendDateTF, gbc);

    gbc.gridy++;
    add(dueDateL, gbc);
    gbc.gridy++;
    add(dueDateTF, gbc);

    gbc.gridy++;
    add(notificationL, gbc);
    gbc.gridy++;
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panel.add(notificationSB);
    panel.setPreferredSize(new Dimension(30, 40));
    panel.setOpaque(false);
    add(panel, gbc);
  }

  public void cancel() {
    if (!notificationSB.isSelected()) notificationSB.doClick();
    lendDateChooser.toDay();
  }

  public void accept(Transaction transaction) {
    transaction.setCreatedAt(this.lendDateChooser.getSelectedDate().getTime());
    transaction.setExpectedReturnAt(this.dueDateChooser.getSelectedDate().getTime());
  }

  public boolean enableNotification() {
    return this.notificationSB.isSelected();
  }
}
