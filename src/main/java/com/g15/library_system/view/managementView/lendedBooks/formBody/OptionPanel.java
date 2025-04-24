package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.SwitchButton;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;

public class OptionPanel extends JPanel {
  private JLabel finePolicyL, lendingStatusL, notificationL;
  private SwitchButton notificationSB;
  private JTextField fineTF;
  private JComboBox<String> statusCB;

  public OptionPanel() {
    setBorder(BorderFactory.createTitledBorder("Additional Options"));
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;

    notificationL =
        LabelGenerator.createLabel(
            "Notifications", Style.FONT_PLAIN_13, Style.WORD_COLOR_BLACK, SwingConstants.LEFT, 0);
    notificationSB = new SwitchButton();

    finePolicyL = LabelGenerator.createRequireLabel("Fine Policy");
    fineTF =
        TextFieldGenerator.createTextFieldWithPlaceholder(
            "Enter Amount",
            Style.FONT_PLAIN_13,
            Color.GRAY,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    lendingStatusL = LabelGenerator.createRequireLabel("Lending Status");
    statusCB = new JComboBox<>();

    gbc.gridy = 0;
    add(notificationL, gbc);
    gbc.gridy = 1;
    add(notificationSB, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(finePolicyL, gbc);
    gbc.gridy = 1;
    add(fineTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(lendingStatusL, gbc);
    gbc.gridy = 1;
    add(statusCB, gbc);
  }
}
