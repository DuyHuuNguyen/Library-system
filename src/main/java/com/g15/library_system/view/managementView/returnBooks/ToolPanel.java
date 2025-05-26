package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.dateChoosers.DateChooser;
import com.g15.library_system.view.overrideComponent.searchFieldOption.SearchOption;
import com.g15.library_system.view.overrideComponent.searchFieldOption.TextFieldSearchOption;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;

import java.awt.*;
import java.awt.event.ActionListener;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;

public class ToolPanel extends JPanel {
  private CustomButton addBt, editBt, exportBt, refreshBt, cancelBt;
  private JButton calenderBt;
  private Map<String, Runnable> actionMap = new HashMap<>();
  private TextFieldSearchOption searchField;
  private final String[] items = {"Add", "Edit", "Export", "Refresh"};
  private DateChooser returnDateChooser;


  public ToolPanel() {
    this.setLayout(new BorderLayout(20, 20));
    this.add(createActionButtonPanel(), BorderLayout.WEST);
    this.add(createSearchPanel(), BorderLayout.EAST);
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
            .title("Create new book return record")
            .icon("/icons/addIcon.png", 5);

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
            .title("Export to excel file")
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
            .title("Refresh table")
            .icon("/icons/refresh.png", 17);

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
            .title("Edit return information")
            .icon("/icons/edit.png", 17);

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
            .title("Undo edit")
            .icon("/icons/cancel.png", 17);

    actionBtPn.add(addBt);
    actionBtPn.add(exportBt);
    actionBtPn.add(refreshBt);
    //    actionBtPn.add(editBt);
    //    actionBtPn.add(cancelBt);

    return actionBtPn;
  }

  private JPanel createSearchPanel() {
    JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));


    DateChooser dueDateChooser = new com.g15.library_system.view.overrideComponent.dateChoosers.DateChooser();
    dueDateChooser.setDateSelectable(
            date -> {
              LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
              return localDate.isBefore(LocalDate.now()); // this disable past date
            });






    calenderBt = new JButton(new ImageIcon(getClass().getResource("/icons/searchOptionIcons/calendar.png")));
    calenderBt.setPreferredSize(new Dimension(40, 40));
    calenderBt.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    calenderBt.setVisible(false);
      calenderBt.addActionListener(e->{
//        Date selectDate = dueDateChooser.getSelectedDate();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(selectDate);
//
//        System.out.println("Selected Date: " + formattedDate);

      });


    searchField = new TextFieldSearchOption();
    searchField.setPreferredSize(new Dimension(400, 40));
    searchField.addEventOptionSelected(
        (option, index) -> {
          searchField.setHint("Search by " + option.getName() + "...");
          if ("Return Date".equals(option.getName())) {
            calenderBt.setVisible(true);

          } else {
            calenderBt.setVisible(false);
//            dateChooser.hidePopup();
          }
        });
    dueDateChooser.setCalendarBtAction(calenderBt, searchField);


    //    txtSearch.popupMenu(
    //            name -> {
    //              return borrowBookController.supportSearch(name);
    //            },
    //            null
    //    );

    searchField.addOption(
        new SearchOption(
            "Name", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/user.png"))));
    searchField.addOption(
        new SearchOption(
            "Tel", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/tel.png"))));
    searchField.addOption(
        new SearchOption(
            "Email", new ImageIcon(getClass().getResource("/icons/searchOptionIcons/mail.png"))));

    searchField.addOption(
        new SearchOption(
            "Return Date",
            new ImageIcon(getClass().getResource("/icons/searchOptionIcons/calendar.png"))));
    searchField.addOption(
        new SearchOption("Staff", new ImageIcon(getClass().getResource("/icons/admin.png"))));

    searchPanel.add(calenderBt);
    searchPanel.add(searchField);
    return searchPanel;
  }

  public void setSearchTxtActionListener(ActionListener actionListener) {
    this.searchField.addActionListener(actionListener);
  }

  public void setAddReturnBookBtListener(ActionListener actionListener) {
    this.addBt.addActionListener(actionListener);
  }

  public void setRefreshBtListener(ActionListener actionListener) {
    this.refreshBt.addActionListener(actionListener);
  }

  public void setExportBtListener(ActionListener actionListener) {
    exportBt.addActionListener(actionListener);
  }

  public String getSearchTextLowerCase() {
    return searchField.getText().trim().toLowerCase();
  }

  public String getSelectedSearchOption() {
    return searchField.getSelectedOption().getName();
  }
}
