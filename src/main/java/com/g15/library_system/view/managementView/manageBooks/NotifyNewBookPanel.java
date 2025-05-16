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
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotifyNewBookPanel extends JPanel {
  private CheckboxTablePanel checkboxTablePanel;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);
  private ReaderController readerController =
      ApplicationContextProvider.getBean(ReaderController.class);

  private BookMapper bookMapper = ApplicationContextProvider.getBean(BookMapperImpl.class);
  private java.util.List<NotifyBookResponse> notifyBookResponses = new ArrayList<>();

  private Map<ApiKey, Runnable> mapApi =
      Map.of(ApiKey.SEND_EMAIL, () -> this.sendEmailNotifyNewBook());

  public NotifyNewBookPanel() {
    this.initPanel();
  }

  private void initPanel() {
    setLayout(new BorderLayout());

    JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

    String[] columns = new String[] {"", "Title", "Author"};

    Object[][] data = {
      {"b1", "n2", "df"},
      {"b1", "n2", "df"},
      {"b1", "n2", "df"},
      {"b1", "n2", "df"}
    };

    RoundedShadowPanel roundedShadowPanel = new RoundedShadowPanel();
    this.checkboxTablePanel = new CheckboxTablePanel(columns, data);
    this.checkboxTablePanel.setPreferredSize(new Dimension(400, 650));
    roundedShadowPanel.add(this.checkboxTablePanel);

    RoundedShadowPanel roundedShadowPanelEmailForm = new RoundedShadowPanel();
    JPanel emailContentPanel = new EmailFormPanel(this.mapApi);
    emailContentPanel.setPreferredSize(new Dimension(750, 650));
    emailContentPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    roundedShadowPanelEmailForm.add(emailContentPanel);

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
            .emails(this.readerController.getAllEmailAcceptNotifyNewBook())
            .titleAndFirstImageDTOS(this.buildEmailNotificationNewBooks())
            .build();
    log.info(" 😁😁😁😁😁 Build content send email {}", content);
    // continue...
  }
}
