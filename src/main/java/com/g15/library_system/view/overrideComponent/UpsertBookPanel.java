package com.g15.library_system.view.overrideComponent;

import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentGenerators.TextFieldGenerator;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

// @Deprecated
public class UpsertBookPanel extends JPanel {
  private JTextField txtBookTitle;
  private JTextField txtQuantity;
  private JTextField txtAuthor;
  private JTextField txtPublisher;
  private JTextField txtPublisherYear;
  private JTextField txtGenre;

  private ImageDropPanel featurePanel;

  private int width;
  private int height;

  public UpsertBookPanel(int width, int height) {
    this.width = width;
    this.height = height;
    this.initPanel();
  }

  public UpsertBookPanel(int width, int height, Book book) {
    this.width = width;
    this.height = height;
    this.initPanel();

    this.txtBookTitle.setText(book.getTitle());
    txtQuantity.setText(String.valueOf(book.getTotalQuantity()));
    txtAuthor.setText(book.getAuthor());
    txtPublisher.setText(book.getPublisher());
    txtPublisherYear.setText(book.getPublishYear() + "");
    txtGenre.setText(book.getGenreType() + "");

    this.featurePanel.loadImagesFromUrls(book.getImages());
  }

  public void initPanel() {
    this.setPreferredSize(new Dimension(width, height));
    setLayout(new BorderLayout());
    JPanel panel = new JPanel(new BorderLayout(10, 10));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel bookInfoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
    bookInfoPanel.setBorder(BorderFactory.createTitledBorder("Book Information"));

    txtBookTitle =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));
    txtQuantity =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));
    txtAuthor =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));
    txtPublisher =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));
    txtPublisherYear =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));
    txtGenre =
        TextFieldGenerator.createTextField(
            "",
            Style.FONT_PLAIN_13,
            Style.WORD_COLOR_BLACK,
            Style.BLUE_HEADER_TABLE_AND_BUTTON,
            new Dimension(200, 25));

    var panelTitle = createFieldPanel("Book Title *", txtBookTitle);
    panelTitle.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    var panelGenre = createFieldPanel("Genre", txtGenre);
    panelGenre.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    var panelAuthor = createFieldPanel("Author", txtAuthor);
    panelAuthor.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    var panelPublisher = createFieldPanel("Publisher", txtPublisher);
    panelPublisher.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    var panelQuantity = createFieldPanel("Quantity", txtQuantity);
    panelQuantity.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    var panelYear = createFieldPanel("Publish Year", txtPublisherYear);
    panelYear.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    bookInfoPanel.add(panelTitle);
    bookInfoPanel.add(panelGenre);
    bookInfoPanel.add(panelAuthor);
    bookInfoPanel.add(panelPublisher);
    bookInfoPanel.add(panelQuantity);
    bookInfoPanel.add(panelYear);
    bookInfoPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    this.featurePanel = new ImageDropPanel(300, 300);

    featurePanel.setBorder(BorderFactory.createTitledBorder("Features"));
    featurePanel.setPreferredSize(new Dimension(800, 200));
    featurePanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton btnCancel = CustomButtonBuilder.builder().text("cancel");
    JButton btnAddBook = CustomButtonBuilder.builder().text("add");
    buttonPanel.add(btnCancel);
    buttonPanel.add(btnAddBook);
    buttonPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    panel.add(bookInfoPanel, BorderLayout.NORTH);
    panel.add(featurePanel, BorderLayout.CENTER);
    panel.add(buttonPanel, BorderLayout.SOUTH);
    panel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    add(panel);
    setVisible(true);
  }

  private JPanel createFieldPanel(String label, JTextField textField) {
    JPanel panel = new JPanel(new BorderLayout(5, 5));
    JLabel jLabel = new JLabel(label);
    jLabel.setPreferredSize(new Dimension(90, 22));
    panel.add(jLabel, BorderLayout.WEST);
    panel.add(textField, BorderLayout.CENTER);
    return panel;
  }

  public static void main(String[] args) {

    var l = new ArrayList<String>();
    l.add("src/main/resources/icons/removeIconTrashBin.png");
    var b =
        Book.builder()
            .id(5L)
            .createdAt(System.currentTimeMillis())
            .updatedAt(System.currentTimeMillis())
            .author("J.R.R. Tolkien")
            .bookStatus(BookStatus.AVAILABLE)
            .title("The Hobbit")
            .publisher("George Allen & Unwin")
            .publishYear(1937)
            .genreType(GenreType.FANTASY)
            .currentQuantity(7)
            .totalQuantity(35)
            .images(l)
            .build();

    JFrame frame = new JFrame("Top Panel UI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 100);
    frame.add(new UpsertBookPanel(300, 300, b));
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
