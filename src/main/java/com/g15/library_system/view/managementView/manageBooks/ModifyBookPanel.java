package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.dto.ChangeInfoBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.util.SaveImageFileHelper;
import com.g15.library_system.verifier.NumberVerifier;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ModifyBookPanel extends JPanel {
  private JTextField publisherDate;

  private DisplayImagePanel imagePanel;

  private JTextField txtBookTitle;
  private JTextField txtQuantity;
  private JTextField txtAuthor;
  private JTextField txtPublisher;
  private JTextField txtPublisherYear;
  private JTextField txtGenre;

  private JFileChooser fileChooser;
  private CustomButtonBuilder chooserFileBtn;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);

  private int width;
  private int height;

  private String pathImage;

  private Book book;

  public ModifyBookPanel(int width, int height) {
    this.width = width;
    this.height = height;
    this.initPanel();
  }

  public ModifyBookPanel() {}

  public void initPanel() {
    this.setBorder(BorderFactory.createTitledBorder("Modify book"));
    this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    this.setPreferredSize(new Dimension(width, height));
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(1, 1, 10, 10)); // Chỉ còn một phần
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel bookInfoPanel = new JPanel();
    bookInfoPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    GroupLayout layout = new GroupLayout(bookInfoPanel);
    bookInfoPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);

    Dimension textFieldSize = new Dimension(200, 20);

    txtBookTitle =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(name -> this.bookController.supportSearch(name), null);
    txtQuantity =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .addInputVerifier(new NumberVerifier());

    txtAuthor =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(name -> this.bookController.supportSearch(name), null);

    txtPublisher =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(name -> this.bookController.supportSearch(name), null);

    txtPublisherYear = new JTextField();
    txtPublisherYear.setPreferredSize(textFieldSize);
    publisherDate =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(textFieldSize);

    txtGenre =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(name -> GenreType.findByName(name), null);

    JLabel lblBookTitle = new JLabel("Book Title *");
    JLabel lblGenre = new JLabel("Genre");
    JLabel lblAuthor = new JLabel("Author");
    JLabel lblPublisher = new JLabel("Publisher");
    JLabel lblQuantity = new JLabel("Quantity");
    JLabel lblPublisherYear = new JLabel("Publish Year");

    chooserFileBtn = CustomButtonBuilder.builder().text("Choose File");
    fileChooser = new JFileChooser();

    imagePanel = new DisplayImagePanel(200, 200);
    imagePanel.setBorder(BorderFactory.createTitledBorder("Image Preview"));
    imagePanel.setBackground(Color.WHITE);

    chooserFileBtn.addActionListener(
        e -> {
          int result = fileChooser.showOpenDialog(this);
          if (result == JFileChooser.APPROVE_OPTION) {
            var selectedFile = fileChooser.getSelectedFile();
            log.info(selectedFile.getAbsolutePath());
            imagePanel.addImageToPanel(selectedFile.getAbsolutePath());
            try {
              this.pathImage = SaveImageFileHelper.saveImage(selectedFile.getAbsolutePath());
            } catch (IOException ex) {
              throw new RuntimeException(ex);
            }
            // TODO: Load and display the image in the imagePanel
          } else {
            JOptionPane.showMessageDialog(this, "File selection cancelled.");
          }
        });

    layout.setHorizontalGroup(
        layout
            .createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(
                layout
                    .createSequentialGroup()
                    .addGroup(
                        layout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(lblBookTitle)
                            .addComponent(lblGenre)
                            .addComponent(lblAuthor)
                            .addComponent(lblPublisher)
                            .addComponent(lblQuantity)
                            .addComponent(lblPublisherYear)
                            .addComponent(chooserFileBtn))
                    .addGroup(
                        layout
                            .createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(txtBookTitle)
                            .addComponent(txtGenre)
                            .addComponent(txtAuthor)
                            .addComponent(txtPublisher)
                            .addComponent(txtQuantity)
                            .addComponent(txtPublisherYear)))
            .addComponent(imagePanel));

    layout.setVerticalGroup(
        layout
            .createSequentialGroup()
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBookTitle)
                    .addComponent(txtBookTitle))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGenre)
                    .addComponent(txtGenre))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAuthor)
                    .addComponent(txtAuthor))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPublisher)
                    .addComponent(txtPublisher))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuantity)
                    .addComponent(txtQuantity))
            .addGroup(
                layout
                    .createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPublisherYear)
                    .addComponent(txtPublisherYear))
            .addComponent(chooserFileBtn)
            .addGap(20)
            .addComponent(imagePanel));

    mainPanel.add(bookInfoPanel);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton btnCancel = CustomButtonBuilder.builder().text("Cancel");
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

    JButton btnAddBook = CustomButtonBuilder.builder().text("Add");
    btnAddBook.addActionListener(
        e -> {
          if (txtBookTitle.getText().trim().isBlank()
              || txtPublisher.getText().trim().isBlank()
              || txtBookTitle.getText().trim().isBlank()
              || txtPublisherYear.getText().trim().isBlank()
              || txtAuthor.getText().trim().isBlank()
              || txtQuantity.getText().trim().isBlank()
              || txtGenre.getText().trim().isBlank()) {

            ToastNotification notification =
                new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.INFO,
                    ToastNotification.Location.TOP_CENTER,
                    "nhap zzo");
            notification.showNotification();
          }

          this.changeInfoBook();
          this.clearDataInPanel();
          this.imagePanel.removeImage();
          this.book = null;
        });

    buttonPanel.add(btnCancel);
    buttonPanel.add(btnAddBook);
    buttonPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    add(mainPanel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);
    setVisible(true);
  }

  public void clearDataInPanel() {
    this.txtBookTitle.setText("");
    txtQuantity.setText("");
    txtAuthor.setText("");
    txtPublisher.setText("");
    txtPublisherYear.setText("");
    txtGenre.setText("");
    this.imagePanel.removeImage();
  }

  public Book getNewBook() {
    var book =
        Book.builder()
            .title(this.txtBookTitle.getText())
            .totalQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .author(txtAuthor.getText())
            .publisher(txtPublisher.getText())
            .publishYear(Integer.parseInt(txtPublisherYear.getText()))
            .currentQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .genreType(GenreType.find(this.txtGenre.getText()))
            .build();

    book.addImage(this.pathImage);
    return book;
  }

  public void addOldBook(Book book) {
    this.book = book;
    this.setDateBookToPanel();
  }

  private void setDateBookToPanel() {
    if (book == null) return;
    this.txtBookTitle.setText(this.book.getTitle());
    txtQuantity.setText(String.valueOf(this.book.getTotalQuantity()));
    txtAuthor.setText(this.book.getAuthor());
    txtPublisher.setText(this.book.getPublisher());
    txtPublisherYear.setText(this.book.getPublishYear() + "");
    txtGenre.setText(this.book.getGenreType() + "");
    this.imagePanel.addImageToPanel(this.book.getFirstImage());
    this.imagePanel.setSizeBookImage(500, 500);
  }

  private void changeInfoBook() {

    var changeInfoBookDTO =
        ChangeInfoBookDTO.builder()
            .title(this.txtBookTitle.getText())
            .totalQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .author(txtAuthor.getText())
            .publisher(txtPublisher.getText())
            .publishYear(Integer.parseInt(txtPublisherYear.getText()))
            .image(this.pathImage)
            .currentQuantity(Integer.parseInt(this.txtQuantity.getText()))
            .genreType(GenreType.find(this.txtGenre.getText()))
            .build();
    log.info("change {}", changeInfoBookDTO);
    this.book.changeInfo(changeInfoBookDTO);
  }

  public void setSizeBookImage(int w, int h) {
    this.imagePanel.setSizeBookImage(w, h);
  }
}
