package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;

public class DetailPanel extends JPanel {
  private JLabel lendDateL, dueDateL, copiesL;
  private JTextField copiesTF, lendDateTF, dueDateTF;

  public DetailPanel() {
    setBorder(BorderFactory.createTitledBorder("Lending Details"));
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;

    lendDateL = LabelGenerator.createRequireLabel("Lending Date");
    lendDateTF =
        TextFieldGenerator.createTextFieldWithPlaceholder(
            "Choose a date",
            Style.FONT_PLAIN_13,
            Color.GRAY,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    dueDateL = LabelGenerator.createRequireLabel("Due Date");
    dueDateTF =
        TextFieldGenerator.createTextFieldWithPlaceholder(
            "Choose a date",
            Style.FONT_PLAIN_13,
            Color.GRAY,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    copiesL = LabelGenerator.createRequireLabel("Copies Lent");
    copiesTF =
        TextFieldGenerator.createTextFieldWithPlaceholder(
            "Enter no of copies",
            Style.FONT_PLAIN_13,
            Color.GRAY,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    gbc.gridy = 0;
    add(lendDateL, gbc);
    gbc.gridy = 1;
    add(lendDateTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(dueDateL, gbc);
    gbc.gridy = 1;
    add(dueDateTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(copiesL, gbc);
    gbc.gridy = 1;
    add(copiesTF, gbc);
  }
}
