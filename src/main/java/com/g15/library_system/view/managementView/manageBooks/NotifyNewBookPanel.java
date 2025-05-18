package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.controller.ReaderController;
import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.TitleAndFirstImageBookDTO;
import com.g15.library_system.dto.response.NotifyBookResponse;
import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.mapper.impl.BookMapperImpl;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;

import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyNewBookPanel extends JPanel {
  private CheckboxTablePanel checkboxTablePanel;
  private EmailFormPanel emailContentPanel;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  private String[] emailReceiveNotification = new String[0];
  private java.util.List<TitleAndFirstImageBookDTO> titleAndFirstImageBookDTOS = new ArrayList<>();

  private BookMapper bookMapper = ApplicationContextProvider.getBean(BookMapperImpl.class);
  private java.util.List<NotifyBookResponse> notifyBookResponses = new ArrayList<>();

  private Map<ApiKey, Runnable> mapApi =
      Map.of(ApiKey.SEND_EMAIL, () -> this.sendEmailNotifyNewBook()
      ,ApiKey.RELOAD,
              ()-> this.upDataIntoTable() );

  public NotifyNewBookPanel() {
    this.initPanel();
  }

  private void initPanel() {
    setLayout(new BorderLayout());

    JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

    String[] columns = new String[] {"", "Title", "Author"};

    Object[][] data = {};

    RoundedShadowPanel roundedShadowPanel = new RoundedShadowPanel();
    this.checkboxTablePanel = new CheckboxTablePanel(columns, data);
    this.checkboxTablePanel.setPreferredSize(new Dimension(400, 650));
    roundedShadowPanel.add(this.checkboxTablePanel);

    RoundedShadowPanel roundedShadowPanelEmailForm = new RoundedShadowPanel();
    this.emailContentPanel = new EmailFormPanel(this.mapApi);

    this.emailContentPanel.setPreferredSize(new Dimension(750, 650));
    this.emailContentPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    roundedShadowPanelEmailForm.add(this.emailContentPanel);

    centerPanel.add(roundedShadowPanel, BorderLayout.CENTER);
    centerPanel.add(roundedShadowPanelEmailForm, BorderLayout.EAST);

    add(centerPanel, BorderLayout.CENTER);
    this.upDataIntoTable();
  }

  private void upDataIntoTable() {
    this.checkboxTablePanel.removeAllDataTable();
    this.notifyBookResponses.addAll(this.bookController.getAllNewBooks());
    this.checkboxTablePanel.addDataToTable(
    this.bookMapper.toDataNotifyBookTable(this.notifyBookResponses));
    this.initData();
  }

  private java.util.List<TitleAndFirstImageBookDTO> buildEmailNotificationNewBooks() {
    return this.notifyBookResponses.stream()
        .map(
            notifyBookResponses -> this.bookMapper.toTitleAndFirstImageBookDTO(notifyBookResponses))
        .toList();
  }

  private void sendEmailNotifyNewBook() {

    var content =
        EmailNotificationNewBooksDTO.builder()
            .emails(this.emailReceiveNotification)
            .titleAndFirstImageDTOS(this.titleAndFirstImageBookDTOS)
            .build();

    log.info(" 😁😁😁😁😁 Build content send email {}", content);
    this.bookController.sendEmailNotificationNewBook(content);

    ToastNotification panel = new
            ToastNotification(JOptionPane.getFrameForComponent(this), ToastNotification.Type.INFO,
            ToastNotification.Location.TOP_CENTER, "Send notification successful");
    panel.showNotification();
    this.reloadDataInPanel();
  }

  public void initData() {
    this.emailReceiveNotification = this.readerController.getAllEmailAcceptNotifyNewBook();
    this.titleAndFirstImageBookDTOS = this.buildEmailNotificationNewBooks();
    this.loadEmails();
    this.loadContentEmails();
    this.loadImages();
  }

  public void loadImages() {
    var images =
        this.titleAndFirstImageBookDTOS.stream()
            .map(TitleAndFirstImageBookDTO::getFirstImage)
            .toList();
    this.emailContentPanel.loadImages(images);
  }

  public void loadContentEmails() {

    StringBuilder content = new StringBuilder();
    content.append("""
                Friendly & inviting:\n
                Have You Seen Our New Books Yet? \n
                New Books Are Waiting for You!\n
                Fresh Reads Just Arrived!\n
                Check Out What’s New at the Library!\n
                """);
    for (int i = 0; i < this.titleAndFirstImageBookDTOS.size(); i++) {
      content.append(i + 1 + "." + this.titleAndFirstImageBookDTOS.get(i).getTitle() + "\n");
    }
    this.emailContentPanel.loadContent(content.toString() ,"Fit Library, New book");
  }

  public void loadEmails() {
    this.emailContentPanel.loadEmail(this.emailReceiveNotification);
  }

  public void reloadDataInPanel(){
    this.emailContentPanel.loadEmail(null);
    this.emailContentPanel.loadContent("","");
    this.emailContentPanel.removeAllImages();
    this.checkboxTablePanel.removeAllDataTable();
  }



}
