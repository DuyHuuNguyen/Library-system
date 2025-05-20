package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentGenerators.TextFieldGenerator;
import java.awt.*;
import java.util.Optional;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UpsertBookPanel extends JPanel {
  private JTextField txtBookTitle;
  private JTextField txtQuantity;
  private JTextField txtAuthor;
  private JTextField txtPublisher;
  private JTextField txtPublisherYear;
  private JTextField txtGenre;

  private ImageDropPanel featurePanel;

  private Optional<Book> book;
  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);
  private int width;
  private int height;

  public UpsertBookPanel(int width, int height) {
    this.width = width;
    this.height = height;
    this.initPanel();
  }

  public UpsertBookPanel() {}

  public UpsertBookPanel(int width, int height, Optional<Book> book) {
    this.book = book;
    this.width = width;
    this.height = height;
    this.initPanel();

    if (book == null) {
      log.error("Book not found");
      return;
    }
    this.txtBookTitle.setText(this.book.get().getTitle());
    txtQuantity.setText(String.valueOf(this.book.get().getTotalQuantity()));
    txtAuthor.setText(this.book.get().getAuthor());
    txtPublisher.setText(this.book.get().getPublisher());
    txtPublisherYear.setText(this.book.get().getPublishYear() + "");
    txtGenre.setText(this.book.get().getGenreType() + "");

    this.featurePanel.loadImagesFromUrls(this.book.get().getImages());
  }

  public Optional<Book> getNewBook() {
    if (book == null || book.isEmpty()) {
      var newBook =
          Book.builder()
              .title(this.txtBookTitle.getText())
              .totalQuantity(Integer.parseInt(this.txtQuantity.getText()))
              .author(txtAuthor.getText())
              .publisher(txtPublisher.getText())
              .publishYear(Integer.parseInt(txtPublisherYear.getText()))
              .images(this.featurePanel.getImageUrls())
              .currentQuantity(Integer.parseInt(this.txtQuantity.getText()))
              .genreType(GenreType.find(this.txtGenre.getText()))
              .build();
      log.info("new book {}", newBook.toString());
      return this.book = Optional.of(newBook);
    }
    return this.book;
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
    btnCancel.addActionListener(
        e -> {
          this.clearDataInPanel();
          ToastNotification notification =
              new ToastNotification(
                  JOptionPane.getFrameForComponent(this),
                  ToastNotification.Type.INFO,
                  ToastNotification.Location.TOP_CENTER,
                  "Cancel successful");
          notification.showNotification();
        });
    JButton btnAddBook = CustomButtonBuilder.builder().text("add");

    btnAddBook.addActionListener(
        e -> {
          var book = this.getNewBook();
          this.bookController.addNewBook(book.get());
          this.clearDataInPanel();

          ToastNotification notification =
              new ToastNotification(
                  JOptionPane.getFrameForComponent(this),
                  ToastNotification.Type.INFO,
                  ToastNotification.Location.TOP_CENTER,
                  "Add book successful");
          notification.showNotification();
        });

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

  public void clearDataInPanel() {
    this.txtBookTitle.setText("");
    txtQuantity.setText("");
    txtAuthor.setText("");
    txtPublisher.setText("");
    txtPublisherYear.setText("");
    txtGenre.setText("");
    featurePanel.clearALlImages();
  }

  public void addData(Optional<Book> bookModify) {
    this.txtBookTitle.setText(bookModify.get().getTitle());
    txtQuantity.setText(String.valueOf(bookModify.get().getTotalQuantity()));
    txtAuthor.setText(bookModify.get().getAuthor());
    txtPublisher.setText(bookModify.get().getPublisher());
    txtPublisherYear.setText(bookModify.get().getPublishYear() + "");
    txtGenre.setText(bookModify.get().getGenreType() + "");

    this.featurePanel.loadImagesFromUrls(bookModify.get().getImages());
  }
}
