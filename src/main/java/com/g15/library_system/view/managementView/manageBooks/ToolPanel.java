package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.controller.BookController;
import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToolPanel extends JPanel {
  private final Dimension DIMENSION_BUTTON = new Dimension();
  private CustomButton addBt, notificationBt, reloadBt, exportBt, importBt, gotoTableBt, modifyBt;
  private Map<String, Runnable> actionMap = new HashMap<>();

  private CardLayout cardLayout;
  private JPanel panelContent;
  private Map<ApiKey, Runnable> mapApi;
  private TextFieldSearchOption txtSearch;

  private BookController bookController = ApplicationContextProvider.getBean(BookController.class);

  public ToolPanel(CardLayout cardLayout, JPanel panelContent, Map<ApiKey, Runnable> mapApi) {
    this.cardLayout = cardLayout;
    this.panelContent = panelContent;

    this.mapApi = mapApi;

    setLayout(new BorderLayout());

    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
    this.txtSearch = new TextFieldSearchOption();
    txtSearch.popupMenu(
        name -> {
          return bookController.supportSearch(name);
        },
        null);
    txtSearch.setPreferredSize(new Dimension(350, 40));
    txtSearch.addKeyListener(null);
    txtSearch.addEventOptionSelected(
        (option, index) -> {
          txtSearch.setHint("Search by " + option.getName() + "...");
          //          mapApi.get(ApiKey.SEARCH).run();
        });

    txtSearch.addActionListener(e -> mapApi.get(ApiKey.SEARCH).run());

    txtSearch.addOption(
        new SearchOption(
            "Title", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));

    txtSearch.addOption(
        new SearchOption(
            "Author", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/tel.png"))));

    txtSearch.addOption(
        new SearchOption(
            "Publisher",
            new ImageIcon(getClass().getResource("/icons/searchOptionIcons/email.png"))));

    txtSearch.addOption(
        new SearchOption(
            "PublishYear",
            new ImageIcon(getClass().getResource("/icons/searchOptionIcons/address.png"))));

    txtSearch.addOption(
        new SearchOption(
            "Genre",
            new ImageIcon(getClass().getResource("/icons/searchOptionIcons/address.png"))));

    leftPanel.add(txtSearch);

    JPanel actionBtPn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

    modifyBt =
        CustomButtonBuilder.builder()
            .text("Modify")
            .title("Mo")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/table.png", 10);
    modifyBt.addActionListener(
        e -> {
          this.mapApi.get(ApiKey.SELECTED_TABLE).run();
          this.cardLayout.show(panelContent, ManageBookPanel.CONSTRAINT_MODIFY_BOOK);
        });
    gotoTableBt =
        CustomButtonBuilder.builder()
            .text("go to table")
            .title("Go to table")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/table.png", 10);
    gotoTableBt.addActionListener(
        e -> this.cardLayout.show(panelContent, ManageBookPanel.CONSTRAINT_TABLE_BOOK));

    exportBt =
        CustomButtonBuilder.builder()
            .text("export")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/import-export.png", 10);
    exportBt.addActionListener(
        e -> {
          this.mapApi.get(ApiKey.EXPORT_EXCEL).run();
        });
    importBt =
        CustomButtonBuilder.builder()
            .text("import")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(100, 30))
            .icon("/icons/file-import.png", 10);
    importBt.addActionListener(
        e -> {
          // code here
          this.mapApi.get(ApiKey.IMPORT_EXCEL).run();
        });
    reloadBt =
        CustomButtonBuilder.builder()
            .text("Reload")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/reload.png", 10);
    reloadBt.addActionListener(
        e -> {
          // code here
          mapApi.get(ApiKey.RELOAD).run();
        });
    addBt =
        CustomButtonBuilder.builder()
            .text("Add Book")
            .title("Add new book")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/addIcon.png", 10);

    addBt.addActionListener(
        e -> this.cardLayout.show(panelContent, ManageBookPanel.CONSTRAINT_ADD_NEW_BOOK));
    actionBtPn.add(addBt);

    notificationBt =
        CustomButtonBuilder.builder()
            .text("Notification")
            .title("Notification")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
                        .radius(10)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(129, 30))
            .icon("/icons/notification.png", 12);
    notificationBt.addActionListener(
        e -> {
          log.info(" run action ");
          this.cardLayout.show(this.panelContent, ManageBookPanel.CONSTRAINT_NOTIFY);
        });

    actionBtPn.add(modifyBt);
    actionBtPn.add(notificationBt);
    actionBtPn.add(reloadBt);
    actionBtPn.add(importBt);
    actionBtPn.add(exportBt);
    actionBtPn.add(gotoTableBt);
    actionBtPn.setBorder(BorderFactory.createTitledBorder("hehe"));

    add(leftPanel, BorderLayout.EAST);
    add(actionBtPn, BorderLayout.WEST);
    this.setBorder(BorderFactory.createTitledBorder("Application"));
  }

  public String getTextOfTextFieldSearchOption() {
    return this.txtSearch.getText();
  }
}
