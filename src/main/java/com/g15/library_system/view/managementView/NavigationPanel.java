package com.g15.library_system.view.managementView;


import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CircularImage;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentGenerators.ButtonGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class NavigationPanel extends JPanel {
  private JLabel role, name, credit;
  private CircularImage avatar;
  public CustomButton dashBoardBt,
          manageBookBt,
          lendedBookBt,
          returnBookBt,
          readerBt,
          librarianBt,
          settingBt,
          myAccountBt,
          logoutBt,
          appLogo;

  private GridBagConstraints gbc;

  public NavigationPanel() {
    setLayout(new FlowLayout());
    setPreferredSize(new Dimension(220, 900));
    setBackground(Color.WHITE);
//    gbc = new GridBagConstraints();
//    gbc.gridx = 1;
//    gbc.insets = new Insets(5, 15, 5, 15);
//    gbc.fill = GridBagConstraints.HORIZONTAL;


//    ComponentTop componentTop = new ComponentTop();
//    gbc.gridy = 0;
//    gbc.weighty = 0.4;
//    add(componentTop, gbc);

    ButtonsPanel buttons = new ButtonsPanel();
//    gbc.gridy++;
//    gbc.weighty = 0.4;
//    gbc.fill = GridBagConstraints.BOTH;
    add(buttons);

//    ComponentBottom componentBottom = new ComponentBottom();
//    gbc.gridy++;
//    gbc.weighty = 0.4;
//    gbc.anchor = GridBagConstraints.PAGE_END;
//    add(componentBottom, gbc);
  }

  class ComponentTop extends JPanel {
    public ComponentTop() {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBackground(new Color(227, 242, 253)); // good
      setAlignmentX(Component.CENTER_ALIGNMENT);

//      avatar = new CircularImage(CurrentUser.URL, 100, 100, true);
//      avatar.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//      name =
//          LabelConfig.createLabel(
//              "<html>" + CurrentUser.CURRENT_USER_V2.getFullName() + "<html>",
//              Style.FONT_BOLD_20,
//              Color.BLACK,
//              SwingConstants.CENTER);
//      name.setPreferredSize(new Dimension(230, 50));
//      name.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//      role =
//          LabelConfig.createLabel("Manager", Style.FONT_PLAIN_15, Color.GRAY, SwingConstants.LEFT);
//      role.setPreferredSize(new Dimension(100, 100));
//      role.setAlignmentX(Component.CENTER_ALIGNMENT);
//
//      JSeparator separatorTop = new JSeparator(SwingConstants.HORIZONTAL);
//      separatorTop.setPreferredSize(new Dimension(320, 5));
//
//      add(avatar);
//      add(Box.createRigidArea(new Dimension(0, 10)));
//      add(name);
//      add(role);
//      add(Box.createRigidArea(new Dimension(0, 5)));
//      add(separatorTop);
    }
  }

  class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
      setLayout(new GridBagLayout());
//      setBackground(Style.MENU_BACKGROUND_COLOR);
      setBackground(Color.WHITE);
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.insets = new Insets(10, 5, 15, 5);
      gbc.anchor = GridBagConstraints.CENTER;
      gbc.fill = GridBagConstraints.NONE;

      appLogo = ButtonGenerator.createCustomButton(
              " FIT NLU",
              Style.FONT_BOLD_25,
              Style.PURPLE_MAIN_THEME,
              Color.WHITE,
              null,
//              Color.GRAY,
//              1,
              10,
              SwingConstants.CENTER,
              new Dimension(220, 60));
      ButtonGenerator.setButtonIcon("src/main/java/view/LibraryUI/images/fit_nlu_logo.jpg", appLogo, 10);
      appLogo.setIsDarkerWhenPress(false);
      add(appLogo, gbc);
      gbc.gridy++;

      JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
      separatorBot.setPreferredSize(new Dimension(200, 1));
      gbc.insets = new Insets(5, 0, 5, 0);
      add(separatorBot, gbc);
      gbc.gridy++;

      dashBoardBt = ButtonGenerator.createCustomButton(
              " Dashboard",
              Style.FONT_PLAIN_13,
              Color.WHITE,
              Style.PURPLE_MAIN_THEME,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/dashboardIcon.png", dashBoardBt, 20);
      add(dashBoardBt, gbc);
      gbc.gridy++;

      manageBookBt = ButtonGenerator.createCustomButton(
              " Manage Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/view/LibraryUI/icons/manageBook.png", manageBookBt, 18);
      add(manageBookBt, gbc);
      gbc.gridy++;

      lendedBookBt = ButtonGenerator.createCustomButton(
              " Lended Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/view/LibraryUI/icons/lendBookIcon.png", lendedBookBt, 15);
      add(lendedBookBt, gbc);
      gbc.gridy++;

      returnBookBt = ButtonGenerator.createCustomButton(
              " Return Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/customerIcon.png", returnBookBt, 20);
      add(returnBookBt, gbc);
      gbc.gridy++;

      readerBt = ButtonGenerator.createCustomButton(
              " Readers",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/orderIcon.png", readerBt, 20);
      add(readerBt, gbc);
      gbc.gridy++;

      librarianBt = ButtonGenerator.createCustomButton(
              " Librarians",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/inventoryIcon.png", librarianBt, 20);
      add(librarianBt, gbc);
      gbc.gridy++;

      settingBt = ButtonGenerator.createCustomButton(
              " Settings",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/user_15094854.png", settingBt, 20);
      add(settingBt, gbc);
      gbc.gridy++;

      myAccountBt = ButtonGenerator.createCustomButton(
              " My Account",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/iconNotification.png", myAccountBt, 20);
      add(myAccountBt, gbc);
      gbc.gridy++;

      logoutBt = ButtonGenerator.createCustomButton(
              " Logout",
              Style.FONT_PLAIN_13,
              Style.LOGOUT_RED,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              12,
              SwingConstants.LEFT,
              new Dimension(200, 40));
      ButtonGenerator.setButtonIcon("src/main/java/view/LibraryUI/icons/logoutIcon.png", logoutBt, 25);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int height = (int) screenSize.getHeight();
      int buttonHeight = 50+ 75+ 45*8 +5+50;
      int logoutHeight = 40 +45;
      gbc.insets = new Insets(height - (buttonHeight + logoutHeight), 0, 5, 0);
      add(logoutBt, gbc);
    }


  }

  class ComponentBottom extends JPanel {
    public ComponentBottom() {
      setLayout(new BorderLayout());
      setBackground(Style.MENU_BACKGROUND_COLOR);
      JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
      separatorBot.setPreferredSize(new Dimension(320, 1));
      add(separatorBot, BorderLayout.NORTH);

      JPanel wrapperPn = new JPanel(new GridLayout(0, 1, 0, 1));
      wrapperPn.setBackground(Style.MENU_BACKGROUND_COLOR);
      wrapperPn.setMaximumSize(new Dimension(200, 30));




      add(wrapperPn, BorderLayout.CENTER);

      credit = new JLabel(" Group 2");
      add(credit, BorderLayout.SOUTH);
    }
  }

  public static void setIcon(String url, JButton that) {
    ImageIcon iconButton = new ImageIcon(url);
    Image image = iconButton.getImage();
    Dimension buttonSize = that.getPreferredSize();
    Image resizedImage =
        image.getScaledInstance(
            buttonSize.height - 5, buttonSize.height - 5, Image.SCALE_SMOOTH); // Resize
    that.setIcon(new ImageIcon(resizedImage));
  }

  public void setDashBoardBtListener(ActionListener listener) {
    dashBoardBt.addActionListener(listener);
  }

  public void setManageBookButtonListener(ActionListener listener) {
    manageBookBt.addActionListener(listener);
  }

  public void setLendedBookButtonListener(ActionListener listener) {
    lendedBookBt.addActionListener(listener);
  }

  public void setReturnBookButtonListener(ActionListener listener) {
    returnBookBt.addActionListener(listener);
  }

  public void setReaderButtonListener(ActionListener listener) {
    readerBt.addActionListener(listener);
  }

  public void setLibrarianButtonListener(ActionListener listener) {
    librarianBt.addActionListener(listener);
  }

  public void setSettingButtonListener(ActionListener listener) {
    settingBt.addActionListener(listener);
  }

  public void setMyAccountButtonListener(ActionListener listener) {
    myAccountBt.addActionListener(listener);
  }

  public void setLogoutButtonListener(ActionListener listener) {
    logoutBt.addActionListener(listener);
  }

}
