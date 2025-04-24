package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;

public class BookPanel extends JPanel {
  private JLabel titleL, isbnL, genreL, authorL;
  private JComboBox<String> titleCB, authorCB, genreCB;
  private JTextField isbnTF;

  public BookPanel() {
    setBorder(BorderFactory.createTitledBorder("Book Information"));
    init();
  }

  private void init() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 10, 5, 10);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.gridx = 0;

    titleL =
        LabelGenerator.createLabel(
            "Book title", Style.FONT_PLAIN_13, Style.WORD_COLOR_BLACK, SwingConstants.LEFT, 0);
    titleCB = new JComboBox<>(new String[] {"Select form list"});

    isbnL = LabelGenerator.createRequireLabel("ISBN/ISSN");
    isbnTF =
        TextFieldGenerator.createTextField(
            "978-92-95055-02-5",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    genreL = LabelGenerator.createRequireLabel("Genre/Category");
    genreCB = new JComboBox<>(new String[] {"Select form list"});

    authorL = LabelGenerator.createRequireLabel("Author(s)");
    authorCB = new JComboBox<>(new String[] {"Select form list"});

    gbc.gridy = 0;
    add(titleL, gbc);
    gbc.gridy = 1;
    add(titleCB, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(isbnL, gbc);
    gbc.gridy = 1;
    add(isbnTF, gbc);

    gbc.gridx++;
    gbc.gridy = 0;
    add(genreL, gbc);
    gbc.gridy = 1;
    add(genreCB, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    add(authorL, gbc);
    gbc.gridy = 3;
    add(authorCB, gbc);
  }
}
