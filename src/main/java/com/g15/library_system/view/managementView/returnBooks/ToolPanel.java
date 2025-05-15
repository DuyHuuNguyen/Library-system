package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.data.BookData;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.returnBooks.commands.Command;
import com.g15.library_system.view.managementView.returnBooks.commands.EditCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.ExportCommand;
import com.g15.library_system.view.managementView.returnBooks.commands.RefreshCommand;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ToolPanel extends JPanel {
  private CustomButton addBt, editBt, exportBt, refreshBt, cancelBt;
  private Map<String, Runnable> actionMap = new HashMap<>();
  private ContentAction contentPnAction;
  private final String[] items = {"Add","Edit", "Export", "Refresh"};
    private Command editCommand = new EditCommand();
    private Command exportCommand = new ExportCommand();
    private Command refreshCommand = new RefreshCommand();

  public ToolPanel(ContentAction contentPnAction) {
    this.contentPnAction = contentPnAction;
    setLayout(new BorderLayout(20, 20));

    add(createActionButtonPanel(), BorderLayout.WEST);
    add(createSearchPanel(), BorderLayout.EAST);
  }

  private JPanel createActionButtonPanel() {
    JPanel actionBtPn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
    addBt =
        CustomButtonBuilder.builder()
            .text("Return Book")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_BACKGROUND_COLOR.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(170, 40))
            .icon("/icons/addIcon.png", 5);

    editBt =
        CustomButtonBuilder.builder()
            .text("Edit")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .preferredSize(new Dimension(120, 40))
            .icon("/icons/edit.png", 17);

    exportBt =
        CustomButtonBuilder.builder()
            .text("Export")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .preferredSize(new Dimension(120, 40))
            .icon("/icons/export.png", 17);

    refreshBt =
        CustomButtonBuilder.builder()
            .text("Refresh")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .preferredSize(new Dimension(120, 40))
            .icon("/icons/refresh.png", 17);

      cancelBt =
        CustomButtonBuilder.builder()
            .text("Cancel")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .backgroundColor(Color.white)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
                .visible(false)
                .enabled(false)
            .preferredSize(new Dimension(120, 40))
            .icon("/icons/cancel.png", 17);

    setActionForActionMap();

    editBt.addActionListener(e -> actionMap.get("Edit").run());
    exportBt.addActionListener(e -> actionMap.get("Export").run());
    refreshBt.addActionListener(e -> actionMap.get("Refresh").run());

      editBt.addActionListener(e -> editCommand.execute());
      exportBt.addActionListener(e -> exportCommand.execute());
      refreshBt.addActionListener(e -> refreshCommand.execute());

    actionBtPn.add(addBt);
    actionBtPn.add(exportBt);
    actionBtPn.add(refreshBt);
    actionBtPn.add(editBt);
    actionBtPn.add(cancelBt);

    return actionBtPn;
  }

  private JPanel createSearchPanel() {
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
    TextFieldSearchOption txt = new TextFieldSearchOption();
    txt.setPreferredSize(new Dimension(350, 40));
    txt.addEventOptionSelected(
        (option, index) -> txt.setHint("Search by " + option.getName() + "..."));

    txt.addOption(
        new SearchOption(
            "Name", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));
    txt.addOption(
        new SearchOption(
            "Tel", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/tel.png"))));
    txt.addOption(
        new SearchOption(
            "Email", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/email.png"))));
    txt.addOption(
        new SearchOption("Staff", new ImageIcon(getClass().getResource("/icons/admin.png"))));

    searchPanel.add(txt);
    return searchPanel;
  }

  private void setActionForActionMap() {
    actionMap.put("Edit", contentPnAction.editTable(editBt, cancelBt));
    actionMap.put(
        "Export",
        () -> {
          Book newBook =
              Book.builder()
                  .id(1L)
                  .createdAt(System.currentTimeMillis())
        
                  .author("J.K. Rowling")
                  .bookStatus(BookStatus.OVERDUE)
                  .title("Harry Potter and the Sorcerer's Stone")
                  .publisher("Bloomsbury")
                  .publishYear(1997)
                  .genreType(GenreType.FANTASY)
                  .currentQuantity(10)
                  .totalQuantity(100)
                  .build();
          BookData.getInstance().add(newBook);
          System.out.println(BookData.getInstance().getBooks().toString());

          JOptionPane.showMessageDialog(this, "Exporting...");
        });
    actionMap.put("Import", () -> JOptionPane.showMessageDialog(this, "Importing..."));
    actionMap.put("Refresh", () -> JOptionPane.showMessageDialog(this, "Refreshing..."));
  }

  public void setAddButtonListener(ActionListener actionListener) {
    this.addBt.addActionListener(actionListener);
  }
}
