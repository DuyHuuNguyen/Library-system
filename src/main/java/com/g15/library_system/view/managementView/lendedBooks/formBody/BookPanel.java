package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BookPanel extends JPanel {
  private JLabel titleL, isbnL, genreL, authorL;
  private JComboBox<String> titleCB, genreCB, authorCB;
  private JTextField isbnTF;

  public BookPanel() {
    Border whiteLine = BorderFactory.createLineBorder(Color.WHITE);
    Border titled =
        BorderFactory.createTitledBorder(whiteLine, "Book Information", 0, 0, Style.FONT_BOLD_20);
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
    gbc.gridx = 0;

    titleL = LabelGenerator.createRequireLabel("Book Title");
    titleCB = new JComboBox<>();

    isbnL = LabelGenerator.createRequireLabel("ISBN/ISSN");
    isbnTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    genreL = LabelGenerator.createRequireLabel("Genre/Category");
    genreCB = new JComboBox<>();

    authorL = LabelGenerator.createRequireLabel("Author(s)");
    authorCB = new JComboBox<>();

    gbc.gridy = 0;
    gbc.gridwidth = 3;
    gbc.weightx = 1;
    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    add(separatorBot, gbc);

    gbc.insets = new Insets(5, 5, 5, 10);
    gbc.gridwidth = 1;
    gbc.weightx = 0;
    gbc.gridy = 1;
    add(titleL, gbc);
    gbc.gridy = 2;
    add(titleCB, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(isbnL, gbc);
    gbc.gridy = 2;
    add(isbnTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(genreL, gbc);
    gbc.gridy = 2;
    add(genreCB, gbc);

    gbc.gridx = 0;
    gbc.gridy = 3;
    add(authorL, gbc);
    gbc.gridy = 4;
    add(authorCB, gbc);
  }

  public void cancel() {
    JComboBox[] CBs = {titleCB, genreCB, authorCB};
    for (JComboBox CB : CBs) {
      if (CB.getItemCount() > 0) CB.setSelectedIndex(0);
    }
  }
}
