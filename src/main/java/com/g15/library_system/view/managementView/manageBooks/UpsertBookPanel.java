package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.verifier.NumberVerifier;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.util.Map;
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

  private ImageDropPanel dropImagePanel;

  private Optional<Book> book;
  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);
  private Map<ApiKey, Runnable> mapApi;

  private int width;
  private int height;

  public UpsertBookPanel(int width, int height, Map<ApiKey, Runnable> mapApi) {
    this.mapApi = mapApi;
    this.width = width;
    this.height = height;
    this.initPanel();
  }

  public UpsertBookPanel() {}

  public UpsertBookPanel(int width, int height, Optional<Book> book, Map<ApiKey, Runnable> mapApi) {
    this.mapApi = mapApi;
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

    this.dropImagePanel.loadImagesFromUrls(this.book.get().getImages());
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
              .images(this.dropImagePanel.getImageUrls())
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
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(new Dimension(300, 25));
    txtQuantity =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .addInputVerifier(new NumberVerifier());
    ;

    txtAuthor =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .popupMenu(name -> bookController.supportSearch(name), null);

    txtPublisher =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .popupMenu(name -> bookController.supportSearch(name), null);
    txtPublisherYear =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .addInputVerifier(new NumberVerifier());

    txtGenre =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(new Dimension(300, 25))
            .popupMenu(name -> GenreType.findByName(name), null);

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

    this.dropImagePanel = new ImageDropPanel(300, 300);

    dropImagePanel.setBorder(BorderFactory.createTitledBorder("Drop Image"));
    dropImagePanel.setPreferredSize(new Dimension(800, 200));
    dropImagePanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

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
          this.clearDataInPanel();
        });

    buttonPanel.add(btnCancel);
    buttonPanel.add(btnAddBook);
    buttonPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    panel.add(bookInfoPanel, BorderLayout.NORTH);
    panel.add(dropImagePanel, BorderLayout.CENTER);
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
    dropImagePanel.clearALlImages();
  }

  public void addData(Optional<Book> bookModify) {
    this.txtBookTitle.setText(bookModify.get().getTitle());
    txtQuantity.setText(String.valueOf(bookModify.get().getTotalQuantity()));
    txtAuthor.setText(bookModify.get().getAuthor());
    txtPublisher.setText(bookModify.get().getPublisher());
    txtPublisherYear.setText(bookModify.get().getPublishYear() + "");
    txtGenre.setText(bookModify.get().getGenreType() + "");

    this.dropImagePanel.loadImagesFromUrls(bookModify.get().getImages());
  }

  public void addNewBook() {
    log.error("url {}", dropImagePanel.getImageUrls());
    var images = dropImagePanel.getImageUrls();
    var book =
        Book.builder()
            .title(this.txtBookTitle.getText())
            .currentQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .totalQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .publisher(this.txtPublisher.getText())
            .publishYear(Integer.parseInt(this.txtPublisherYear.getText()))
            .genreType(GenreType.valueOf(txtGenre.getText()))
            .images(images)
            .build();
    log.info("new book {}", book);
    this.bookController.addNewBook(book);
    // src/main/resources/bookImages/Ảnh chụp màn hình 2025-05-18 203552.png
    ToastNotification panel =
        new ToastNotification(
            JOptionPane.getFrameForComponent(this),
            ToastNotification.Type.INFO,
            ToastNotification.Location.TOP_CENTER,
            "Add successful");
    panel.showNotification();
    this.mapApi.get(ApiKey.RELOAD).run();
  }
}
