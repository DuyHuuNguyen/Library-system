package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.util.SaveImageFileHelper;
import com.g15.library_system.verifier.NumberVerifier;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.manageBooks.observer.ObserverNotifyNewBook;
import com.g15.library_system.view.managementView.manageBooks.observer.SubjectNotifyNewBook;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AddNewBookPanel extends JPanel implements SubjectNotifyNewBook {
  private JTextField publisherDate;
  private java.util.List<ObserverNotifyNewBook> observerNotifyNewBooks;

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

  public AddNewBookPanel(int width, int height) {
    this.width = width;
    this.height = height;
    this.observerNotifyNewBooks = new ArrayList<>();
    this.initPanel();
  }

  public AddNewBookPanel() {}

  public void initPanel() {
    this.setBorder(BorderFactory.createTitledBorder("Add new book"));
    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(width, height));
    setLayout(new BorderLayout());

    JPanel mainPanel = new JPanel(new GridLayout(1, 1, 10, 10)); // Chỉ còn một phần
    mainPanel.setBackground(Color.WHITE);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    JPanel bookInfoPanel = new JPanel();

    bookInfoPanel.setBackground(Color.WHITE);

    GroupLayout layout = new GroupLayout(bookInfoPanel);
    bookInfoPanel.setLayout(layout);
    layout.setAutoCreateGaps(true);
    layout.setAutoCreateContainerGaps(true);


    Dimension textFieldSize = new Dimension(200, 20);

    txtBookTitle =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(textFieldSize);
    txtQuantity =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .addInputVerifier(new NumberVerifier());

    txtAuthor =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(e -> this.bookController.supportSearch(txtAuthor.getText()), null);

    txtPublisher =
        TextFieldBuilder.builder()
            .font(Style.FONT_PLAIN_13)
            .preferredSize(textFieldSize)
            .popupMenu(e -> this.bookController.supportSearch(txtPublisher.getText()), null);

    txtPublisherYear = new JTextField();
    txtPublisherYear.setPreferredSize(textFieldSize);

    publisherDate =
        TextFieldBuilder.builder().font(Style.FONT_PLAIN_13).preferredSize(textFieldSize);
    publisherDate.setInputVerifier(new NumberVerifier());

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

    chooserFileBtn = CustomButtonBuilder.builder()
            .text("Chooser file")
            .title("Chooser file")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(10)
            .alignment(SwingConstants.LEFT)
            .icon("/icons/executionIcon.png",10)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30));
    fileChooser = new JFileChooser();

    imagePanel = new DisplayImagePanel(90, 90);
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
    buttonPanel.setBackground(Color.WHITE);
    JButton btnCancel = CustomButtonBuilder.builder()
            .text("Cancel")
            .title("Cancel")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(10)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30));
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

    JButton btnAddBook = CustomButtonBuilder.builder().text("Add")
            .title("Add")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(10)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30));
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

          this.bookController.addNewBook(this.getNewBook());
          this.clearDataInPanel();
          this.notifyAllNewBook();
        });

    buttonPanel.add(btnCancel);
    buttonPanel.add(btnAddBook);

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

  @Override
  public void notifyAllNewBook() {
    log.debug("Observer thong bao sach moi");
    this.observerNotifyNewBooks.forEach(
        observerNotifyNewBook -> {
          observerNotifyNewBook.notifyNewBook();
        });
  }

  @Override
  public void addObserverNotifyNewBook(ObserverNotifyNewBook observerNotifyNewBook) {
    this.observerNotifyNewBooks.add(observerNotifyNewBook);
  }
}
