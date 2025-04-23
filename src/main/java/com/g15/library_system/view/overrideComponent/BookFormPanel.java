package com.g15.library_system.view.overrideComponent;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.TextFieldGenerator;
import java.awt.*;
import javax.swing.*;

@Deprecated
public class BookFormPanel extends JPanel {
  private JTextField txtBookTitle;
  private JTextField txtQuantity;
  private JTextField txtAuthor;
  private JTextField txtAvailableCopies;
  private JTextField txtTotalCopies;
  JTextField txtGenre;

  public BookFormPanel() {

    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel bookInfoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    bookInfoPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

    txtBookTitle =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));
    txtQuantity =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));
    txtAuthor =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));
    txtAvailableCopies =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));
    txtTotalCopies =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));
    txtGenre =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.PURPLE_MAIN_THEME,
            new Dimension(200, 25));

    bookInfoPanel.add(createFieldPanel("Book Title *", txtBookTitle));
    bookInfoPanel.add(createFieldPanel("Genre", txtGenre));
    bookInfoPanel.add(createFieldPanel("Author", txtAuthor));
    bookInfoPanel.add(createFieldPanel("Publisher", txtAvailableCopies));
    bookInfoPanel.add(createFieldPanel("Quantity", txtQuantity));
    bookInfoPanel.add(createFieldPanel("Publish Year", txtTotalCopies));

    JPanel featurePanel = new ImageDropPanel();
    featurePanel.setBorder(BorderFactory.createTitledBorder("Features"));
    featurePanel.setPreferredSize(new Dimension(800, 200));

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton btnCancel = new JButton("Cancel");
    JButton btnAddBook = new JButton("Add Book");
    buttonPanel.add(btnCancel);
    buttonPanel.add(btnAddBook);

    panel.add(bookInfoPanel, BorderLayout.NORTH);
    panel.add(featurePanel, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);

    add(panel);
    setVisible(true);
  }

  private JPanel createFieldPanel(String label, JTextField textField) {
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    JLabel jLabel = new JLabel(label);
    jLabel.setPreferredSize(new Dimension(100, 25));
    panel.add(jLabel, BorderLayout.WEST);
    panel.add(textField, BorderLayout.CENTER);
    return panel;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Top Panel UI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 100);
    frame.add(new BookFormPanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
