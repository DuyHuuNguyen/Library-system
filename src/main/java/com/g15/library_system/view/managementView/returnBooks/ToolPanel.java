package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.data.BookData;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.view.Style;
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
  private CustomButton addBt, mainBt, dropdownBt;
  private Map<String, Runnable> actionMap = new HashMap<>();
  private ContentAction contentPnAction;
  private final String[] items = {"Edit", "Export", "Refresh"};

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
    actionBtPn.add(addBt);

    mainBt =
        CustomButtonBuilder.builder()
            .text("Actions")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .preferredSize(new Dimension(120, 40))
            .roundedSide(CustomButton.RoundedSide.LEFT);

    dropdownBt =
        CustomButtonBuilder.builder()
            .text("▼")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BACKGROUND_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR.darker())
            .radius(6)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .roundedSide(CustomButton.RoundedSide.RIGHT)
            .preferredSize(new Dimension(45, 40));

    JPanel dropdownBtPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    dropdownBtPanel.add(mainBt);
    dropdownBtPanel.add(dropdownBt);

    JPopupMenu menu = new JPopupMenu();
    Font menuFont = new Font("Segoe UI", Font.PLAIN, 14);
    int popupWidth = mainBt.getPreferredSize().width + dropdownBt.getPreferredSize().width - 2;
    int popupHeight = 35;

    setActionForActionMap();

    for (String itemText : items) {
      JMenuItem item = new JMenuItem(itemText);
      item.setFont(menuFont);
      item.setPreferredSize(new Dimension(popupWidth, popupHeight));

      item.addActionListener(
          e -> {
            mainBt.setText(itemText);
            mainBt.setIcon("/icons/" + itemText.toLowerCase() + ".png", 17);
            actionMap.get(itemText).run();

            for (ActionListener al : mainBt.getActionListeners()) {
              mainBt.removeActionListener(al);
            }
            mainBt.addActionListener(
                ev -> {
                  Runnable action = actionMap.get(itemText);
                  if (action != null) action.run();
                });
          });

      menu.add(item);
    }

    dropdownBt.addActionListener(
        e -> {
          menu.show(dropdownBtPanel, 0, dropdownBtPanel.getHeight());
        });
    actionBtPn.add(dropdownBtPanel);

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
    actionMap.put("Edit", contentPnAction.editTable(mainBt));
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
