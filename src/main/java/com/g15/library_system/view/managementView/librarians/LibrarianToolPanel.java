package com.g15.library_system.view.managementView.librarians;

import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class LibrarianToolPanel extends JPanel {
  private CustomButton addBt, modifyBt, reloadBt, gotoTableBt;
  private CardLayout cardLayout;
  private JPanel panelContent;
  private TextFieldSearchOption txtSearch;
  private Map<ApiKey, Runnable> mapApi;

  public LibrarianToolPanel(
      CardLayout cardLayout, JPanel panelContent, Map<ApiKey, Runnable> mapApi) {
    this.cardLayout = cardLayout;
    this.panelContent = panelContent;
    this.mapApi = mapApi;

    setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        txtSearch = new TextFieldSearchOption();
        txtSearch.setPreferredSize(new Dimension(350, 40));
        txtSearch.popupMenu(null, null); // placeholder
        txtSearch.addOption(new SearchOption("First name", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));
        txtSearch.addOption(new SearchOption("Last name", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));
        txtSearch.addOption(new SearchOption("Email", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/email.png"))));
        txtSearch.addOption(new SearchOption("Phone number", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/tel.png"))));
        txtSearch.addOption(new SearchOption("Address", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/address.png"))));

    txtSearch.addActionListener(e -> mapApi.get(ApiKey.SEARCH).run());

    leftPanel.add(txtSearch);

    JPanel actionBtPn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));

    addBt =
        CustomButtonBuilder.builder()
            .text("Add librarian")
            .title("Add librarian")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(1)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/addIcon.png", 10);
    addBt.addActionListener(
        e -> this.cardLayout.show(panelContent, LibrarianPanel.CONSTRAINT_ADD_NEW_LIBRARIAN));

    modifyBt =
        CustomButtonBuilder.builder()
            .text("Modify")
            .title("Modify")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(1)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/table.png", 10);
    modifyBt.addActionListener(
        e -> {
          this.mapApi.get(ApiKey.SELECTED_TABLE).run();
          this.cardLayout.show(panelContent, LibrarianPanel.CONSTRAINT_MODIFY_LIBRARIAN);
        });

    reloadBt =
        CustomButtonBuilder.builder()
            .text("Reload")
            .title("Reload")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(1)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/reload.png", 10);
    reloadBt.addActionListener(
        e -> {
          if (mapApi != null) mapApi.get(ApiKey.RELOAD).run();
        });

    gotoTableBt =
        CustomButtonBuilder.builder()
            .text("Go to table")
            .title("Go to table")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(1)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(110, 30))
            .icon("/icons/table.png", 10);
    gotoTableBt.addActionListener(
        e -> this.cardLayout.show(panelContent, LibrarianPanel.CONSTRAINT_TABLE));

    actionBtPn.add(addBt);
    actionBtPn.add(modifyBt);
    actionBtPn.add(reloadBt);
    actionBtPn.add(gotoTableBt);

    add(leftPanel, BorderLayout.WEST);
    add(actionBtPn, BorderLayout.EAST);
    setBorder(BorderFactory.createTitledBorder("Application"));
  }

    public String getTextOfTextFieldSearchOption() {
        return this.txtSearch.getText();
    }

}

