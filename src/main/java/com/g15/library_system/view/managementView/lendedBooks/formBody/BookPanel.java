package com.g15.library_system.view.managementView.lendedBooks.formBody;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class BookPanel extends JPanel {
  private JTextField titleTF, authorTF;
  private JComboBox<GenreType> genreCB;
  private JLabel titleL, genreL, authorL;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);

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

    genreL = LabelGenerator.createRequireLabel("Genre/Category");
    genreCB = new JComboBox<>(GenreType.values());

    authorL = LabelGenerator.createRequireLabel("Author(s)");
    authorTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

    titleL = LabelGenerator.createRequireLabel("Book Title");
    titleTF =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .popupMenu(
                text -> {
                  return bookController.searchTitleContains(text);
                },
                selectedTitle -> {
                  Book book = bookController.findByTitle(selectedTitle).orElse(null);
                  if (book != null) {
                    genreCB.setSelectedItem(book.getGenreType());
                    authorTF.setText(book.getAuthor());
                    KeyboardFocusManager.getCurrentKeyboardFocusManager()
                        .focusNextComponent(authorTF);
                  }
                })
            .preferredSize(new Dimension(300, 25))
            .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);

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
    add(titleTF, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(genreL, gbc);
    gbc.gridy = 2;
    add(genreCB, gbc);

    gbc.gridx++;
    gbc.gridy = 1;
    add(authorL, gbc);
    gbc.gridy = 2;
    add(authorTF, gbc);
  }

  public void cancel() {
    genreCB.setSelectedIndex(-1);
    JTextField[] TFs = {titleTF, authorTF};
    for (JTextField TF : TFs) {
      TF.setText("");
    }
  }
}
