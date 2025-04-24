package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class UserPanel extends JPanel {
  private JTextField nameTF, idTF, contactNumberTF;
  private JLabel nameL, idL, contactNumberL;

  public UserPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    setBorder(
        BorderFactory.createTitledBorder(whiteLine, "User Information", 0, 0, Style.FONT_BOLD_20));
    setOpaque(false);
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;

    nameL =
        LabelGenerator.createLabel(
            "Lender Name", Style.FONT_PLAIN_13, Style.WORD_COLOR_BLACK, SwingConstants.LEFT, 0);
    nameTF =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    idL = LabelGenerator.createRequireLabel("Membership ID");
    idTF =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    contactNumberL = LabelGenerator.createRequireLabel("Contact Number");
    contactNumberTF =
        TextFieldGenerator.createTextFieldWithPlaceholder(
            "www.example.com",
            Style.FONT_PLAIN_13,
            Color.GRAY,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    separatorBot.setPreferredSize(new Dimension(200, 1));
    gbc.insets = new Insets(5, 0, 5, 0);
    add(separatorBot, gbc);

    gbc.gridy = 0;
    add(nameL, gbc);
    gbc.gridy = 1;
    add(nameTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(idL, gbc);
    gbc.gridy = 1;
    add(idTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(contactNumberL, gbc);
    gbc.gridy = 1;
    add(contactNumberTF, gbc);
  }
}
