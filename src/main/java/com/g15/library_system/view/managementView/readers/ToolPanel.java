package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import org.springframework.cglib.core.Local;

import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class ToolPanel extends JPanel {
  //  private ReaderPanel readerPn = new ReaderPanel();
  //  private FormPanel formPn = readerPn.contentPn.showPn.formPn;
  private CustomButton addBt, removeBt, mainButton, dropdownButton;
  private Map<String, Runnable> actionMap = new HashMap<>();

  ToolPanel(ReaderPanel readerPn) {
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
    removeBt =
        CustomButtonBuilder.builder()
            .text("Remove")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.DELETE_BUTTON_COLOR_RED)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(160, 40))
            .icon("/icons/deleteIcon.png", 10);
    removeBt.setVisible(false);
    removeBt.addActionListener(e -> {});

    actionBtPn.add(removeBt);

    addBt =
        CustomButtonBuilder.builder()
            .text("Add Member")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.PURPLE_MAIN_THEME)
            .hoverColor(Style.PURPLE_MAIN_THEME.darker())
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(160, 40))
            .icon("/icons/addIcon.png", 10);

    addBt.addActionListener(
        e -> {
          clearTextFields(readerPn.contentPn.showInforPn.formPn); // Xóa trắng
          enableTextFields(readerPn.contentPn.showInforPn.formPn, true); // Cho phép nhập
          readerPn.contentPn.showInforPn.btnPn.setMode(ButtonPanelMode.ADD);
        });
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
            .alignment(SwingConstants.LEFT)
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

    add(actionBtPn, BorderLayout.EAST);
  }

  //  public void setAddButtonListener(ActionListener actionListener) {
  //    this.addBt.addActionListener(actionListener);
  //  }

  public void clearTextFields(JPanel panel) {
    for (Component comp : panel.getComponents()) {
      if (comp instanceof JTextField) {
        ((JTextField) comp).setText("");
      }
    }
  }

  public void enableTextFields(FormPanel panel, boolean editable) {
    for (Component comp : panel.getComponents()) {
      if (comp instanceof JTextField) {
        if (comp == panel.getReaderTypeField()) {
          panel.getReaderTypeField().setVisible(!editable);
          panel.getReaderTypeJcb().setVisible(editable);
        }
        if (comp == panel.getBirthDateField()) {
          panel.getBirthDateField().setEnabled(editable);
        }
        if (comp == panel.getMembershipDateField()) {
          panel.getMembershipDateField().setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
          panel.getMembershipDateField().setEnabled(false);
        }
        if (comp == panel.getTotalFineField()) {
          panel.getTotalFineField().setEditable(false);
        } else
          ((JTextField) comp).setEditable(editable);

      }
    }
  }

  public CustomButton getRemoveBt() {
    return removeBt;
  }
}
