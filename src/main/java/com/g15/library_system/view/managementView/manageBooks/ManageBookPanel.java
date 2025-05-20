package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.dto.request.ImportExcelRequest;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.mapper.impl.BookMapperImpl;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.OptionPaneInputFileExcel;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import javax.swing.*;
import org.apache.commons.math3.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManageBookPanel extends JPanel {
  private static final Logger log = LoggerFactory.getLogger(ManageBookPanel.class);
  private JPanel panelContent;

  private CheckboxTablePanel checkboxTablePanel;
  private final String[] columns = {
    "",
    "Title",
    "Author",
    "Publisher",
    "Publish year",
    "Genre",
    "Current quantity",
    "Total quantity",
    "Status"
  };
  private Object[][] data;

  private JPanel bookFormAndDropImagesPanel;
  private UpsertBookPanel bookFormPanel;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);
  private BookMapper bookMapper = ApplicationContextProvider.getBean(BookMapperImpl.class);

  private java.util.List<BookResponse> bookResponses = new ArrayList<>();
  private Optional<Book> bookModify;

  private Map<ApiKey, Runnable> mapAPIs =
      Map.of(
          ApiKey.SELECTED_TABLE,
          () -> this.findBookModifySelected(),
          ApiKey.ADD,
          () -> this.addNewBook(),
          ApiKey.RELOAD,
          () -> this.loadDataTable(),
          ApiKey.SEARCH,
          () -> this.findByTextOfTextFieldSearchOptionUpDataToTable(),
          ApiKey.EXPORT_EXCEL,
          () -> this.exportExcel(),
          ApiKey.IMPORT_EXCEL,
          () -> this.importExcel());

  private UpsertBookPanel addNewBookPanel;
  private UpsertBookPanel modifyBookPanel;

  private CardLayout cardLayout;

  private ToolPanel toolPanel;

  public static final String CONSTRAINT_TABLE_BOOK = "book_table";
  public static final String CONSTRAINT_ADD_NEW_BOOK = "add_new_book";
  public static final String CONSTRAINT_MODIFY_BOOK = "modify_book";
  public static final String CONSTRAINT_NOTIFY = "notify_new_book";

  public ManageBookPanel() {

    setLayout(new BorderLayout());
    this.cardLayout = new CardLayout();
    this.panelContent = new JPanel(cardLayout);

    this.toolPanel = new ToolPanel(cardLayout, panelContent, mapAPIs);
    this.add(toolPanel, BorderLayout.NORTH);

    this.panelContent.setBackground(Color.GREEN);

    this.initData();

    this.checkboxTablePanel = new CheckboxTablePanel(columns, data);
    this.panelContent.add(checkboxTablePanel, CONSTRAINT_TABLE_BOOK);

    this.bookFormAndDropImagesPanel = new JPanel(new BorderLayout());
    this.bookFormAndDropImagesPanel.setBackground(Color.PINK);
    this.bookFormPanel = new UpsertBookPanel(1000, 500,mapAPIs);

    this.bookFormAndDropImagesPanel.add(bookFormPanel, BorderLayout.CENTER);

    this.panelContent.add(bookFormAndDropImagesPanel, CONSTRAINT_ADD_NEW_BOOK);

    this.addNewBookPanel = new UpsertBookPanel(1000, 500,mapAPIs);
    this.panelContent.add(this.addNewBookPanel, CONSTRAINT_MODIFY_BOOK);

    this.panelContent.add(new NotifyNewBookPanel(), CONSTRAINT_NOTIFY);

    this.modifyBookPanel = new UpsertBookPanel(100, 500,mapAPIs);
    this.panelContent.add(modifyBookPanel, CONSTRAINT_MODIFY_BOOK);

    add(panelContent, BorderLayout.CENTER);
    this.setBackground(Style.LIGHT_WHITE_BACKGROUND);
  }

  private void removeAllDataTable() {
    this.checkboxTablePanel.removeAllDataTable();
  }

  private void loadDataTable() {
    this.removeAllDataTable();
    var data = bookController.findALl();

    this.bookResponses.clear();

    this.bookResponses.addAll(data);
    this.checkboxTablePanel.addDataToTable(this.bookMapper.toBookData(this.bookResponses));
  }

  private void initData() {
    var br = bookController.findALl();
    this.data = this.bookMapper.toBookData(br);
  }

  private void findBookModifySelected() {
    var data = this.checkboxTablePanel.getSelectedRowData();
    this.bookModify = this.bookController.findByTitle(data[1].toString());
    this.modifyBookPanel.addData(bookModify);
    log.info("selected book {}", bookModify.toString());
  }

  private void addNewBook() {
    var newBook = this.addNewBookPanel.getNewBook();
    this.bookController.addNewBook(newBook.get());
  }

  private void findByTextOfTextFieldSearchOptionUpDataToTable() {
    var text = this.toolPanel.getTextOfTextFieldSearchOption();
    this.removeAllDataTable();

    this.bookResponses.clear();

    this.bookResponses.addAll(this.bookController.findByTextOfTextFieldSearchOption(text));
    for (var item : this.bookResponses) {
      log.info("data {}", item);
    }
    this.checkboxTablePanel.addDataToTable(this.bookMapper.toBookData(this.bookResponses));
  }

  private void exportExcel() {
    Pair<String, String> fileAndHeaderSheet = OptionPaneInputFileExcel.buildPane();
    if (fileAndHeaderSheet == null) {
      ToastNotification panel =
          new ToastNotification(
              JOptionPane.getFrameForComponent(this),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.TOP_CENTER,
              "Export fail");
      panel.showNotification();
      return;
    }

    ToastNotification panel =
        new ToastNotification(
            JOptionPane.getFrameForComponent(this),
            ToastNotification.Type.SUCCESS,
            ToastNotification.Location.TOP_CENTER,
            "Export successfully");
    panel.showNotification();

    var exportExcelRequest =
        com.g15.library_system.dto.request.ExportExcelRequest.builder()
            .nameFile(fileAndHeaderSheet.getFirst())
            .headerSheet(fileAndHeaderSheet.getSecond())
                .books(this.bookController.getAll())
            .build();
    this.bookController.exportExcel(exportExcelRequest);
  }

  public void importExcel() {
    JFileChooser fileChooser = new JFileChooser();
    int option = fileChooser.showOpenDialog(this);

    if (option == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      String path = selectedFile.getAbsolutePath();

      ToastNotification panel = new
              ToastNotification(JOptionPane.getFrameForComponent(this), ToastNotification.Type.INFO,
              ToastNotification.Location.TOP_CENTER, "File name : " + selectedFile.getName());
      panel.showNotification();

      log.info("Url import excel {}", selectedFile.getName());
      this.bookController.importExcel(ImportExcelRequest.builder().url(path).build());

    } else {
      log.error("😒😒😒😒 error import file excel");
    }
  }
}
