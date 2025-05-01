package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ToolPanel extends JPanel {
  private CustomButton addBt, removeBt, mainButton, dropdownButton;
  private Map<String, Runnable> actionMap = new HashMap<>();

  public ToolPanel() {
    setLayout(new BorderLayout());

    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
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
        new SearchOption(
            "Address",
            new ImageIcon(getClass().getResource("/icons/searchOptionIcons/address.png"))));

    leftPanel.add(txt);
    add(leftPanel, BorderLayout.WEST);

    JPanel actionBtPn = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 10));
    addBt =
        CustomButtonBuilder.builder()
            .text("Add Book")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(150, 40))
            .icon("/icons/addIcon.png", 10);
    addBt.addActionListener(
        e ->
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.SUCCESS,
                    ToastNotification.Location.TOP_CENTER,
                    "New book added successfully!")
                .showNotification());
    actionBtPn.add(addBt);

    mainButton =
        CustomButtonBuilder.builder()
            .text("Actions")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(6)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .preferredSize(new Dimension(120, 40))
            .roundedSide(CustomButton.RoundedSide.LEFT)
            .icon("/icons/edit.png", 20);

    dropdownButton =
        CustomButtonBuilder.builder()
            .text("▼")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(6)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .roundedSide(CustomButton.RoundedSide.RIGHT)
            .preferredSize(new Dimension(45, 40));

    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panel.add(mainButton);
    panel.add(dropdownButton);

    JPopupMenu menu = new JPopupMenu();

    String[] items = {"Edit", "Export", "Import", "Refresh"};
    Font menuFont = new Font("Segoe UI", Font.PLAIN, 14);
    int popupWidth =
        mainButton.getPreferredSize().width + dropdownButton.getPreferredSize().width - 2;
    int popupHeight = 35;

    actionMap.put(
        "Edit",
        () ->
            new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.INFO,
                    ToastNotification.Location.TOP_CENTER,
                    "editing")
                .showNotification());
    actionMap.put("Export", () -> JOptionPane.showMessageDialog(this, "Exporting..."));
    actionMap.put("Import", () -> JOptionPane.showMessageDialog(this, "Importing..."));
    actionMap.put("Refresh", () -> JOptionPane.showMessageDialog(this, "Refreshing..."));

    for (String itemText : items) {
      JMenuItem item = new JMenuItem(itemText);
      item.setFont(menuFont);
      item.setPreferredSize(new Dimension(popupWidth, popupHeight));

      item.addActionListener(
          e -> {
            mainButton.setText(itemText);
            for (ActionListener al : mainButton.getActionListeners()) {
              mainButton.removeActionListener(al);
            }
            mainButton.addActionListener(
                ev -> {
                  Runnable action = actionMap.get(itemText);
                  if (action != null) action.run();
                });
          });

      menu.add(item);
    }

    dropdownButton.addActionListener(
        e -> {
          menu.show(panel, 0, panel.getHeight());
        });
    actionBtPn.add(panel);

    // ------------------------------------------------------------
    removeBt =
        CustomButtonBuilder.builder()
            .text("Remove")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(220, 68, 84))
            .hoverColor(new Color(220, 68, 84).darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(135, 40))
            .icon("/icons/deleteIcon.png", 12);
    actionBtPn.add(removeBt);

    add(actionBtPn, BorderLayout.EAST);
  }
}
