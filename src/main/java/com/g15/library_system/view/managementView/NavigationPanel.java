package com.g15.library_system.view.managementView;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CircularImage;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import com.g15.library_system.view.swingComponentGenerators.ButtonGenerator;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

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
    setLayout(new GridBagLayout());
    //      setBackground(Style.MENU_BACKGROUND_COLOR);
//    setBackground(Color.WHITE);
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 15, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.NONE;

    appLogo =
            ButtonGenerator.createCustomButton(
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
    ButtonGenerator.setButtonIcon(
            "src/main/java/view/LibraryUI/images/fit_nlu_logo.jpg", appLogo, 10);
    appLogo.setIsDarkerWhenPress(false);
    add(appLogo, gbc);
    gbc.gridy++;

    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    separatorBot.setPreferredSize(new Dimension(220, 1));
    gbc.insets = new Insets(5, 10, 5, 10);
    add(separatorBot, gbc);
    gbc.gridy++;

    dashBoardBt =
            ButtonGenerator.createCustomButton(
                    " Dashboard",
                    Style.FONT_PLAIN_13,
                    Color.WHITE,
                    Style.PURPLE_MAIN_THEME,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/dashboardIcon.png", dashBoardBt, 20);
    add(dashBoardBt, gbc);
    gbc.gridy++;

    dashBoardBt =

    manageBookBt =
            ButtonGenerator.createCustomButton(
                    " Manage Books",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon(
            "src/main/java/view/LibraryUI/icons/manageBook.png", manageBookBt, 18);
    add(manageBookBt, gbc);
    gbc.gridy++;

    lendedBookBt =
            ButtonGenerator.createCustomButton(
                    " Lended Books",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon(
            "src/main/java/view/LibraryUI/icons/lendBookIcon.png", lendedBookBt, 15);
    add(lendedBookBt, gbc);
    gbc.gridy++;

    returnBookBt =
            ButtonGenerator.createCustomButton(
                    " Return Books",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/customerIcon.png", returnBookBt, 20);
    add(returnBookBt, gbc);
    gbc.gridy++;

    readerBt =
            ButtonGenerator.createCustomButton(
                    " Readers",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/orderIcon.png", readerBt, 20);
    add(readerBt, gbc);
    gbc.gridy++;

    librarianBt =
            ButtonGenerator.createCustomButton(
                    " Librarians",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/inventoryIcon.png", librarianBt, 20);
    add(librarianBt, gbc);
    gbc.gridy++;

    settingBt =
            ButtonGenerator.createCustomButton(
                    " Settings",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/user_15094854.png", settingBt, 20);
    add(settingBt, gbc);
    gbc.gridy++;

    myAccountBt =
            ButtonGenerator.createCustomButton(
                    " My Account",
                    Style.FONT_PLAIN_13,
                    Color.BLACK,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon("src/main/java/Icon/iconNotification.png", myAccountBt, 20);
    add(myAccountBt, gbc);
    gbc.gridy++;

    logoutBt =
            ButtonGenerator.createCustomButton(
                    " Logout",
                    Style.FONT_PLAIN_13,
                    Style.LOGOUT_RED,
                    Color.WHITE,
                    Style.HOVER_WHITE_DARKER,
                    20,
                    SwingConstants.LEFT,
                    new Dimension(220, 45));
    ButtonGenerator.setButtonIcon(
            "src/main/java/view/LibraryUI/icons/logoutIcon.png", logoutBt, 25);
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int) screenSize.getHeight();
    int buttonHeight = 50 + 75 + 50 * 8 + 5 + 50;
    int logoutHeight = 40 + 45;
    gbc.insets = new Insets(height - (buttonHeight + logoutHeight), 10, 5, 10);
    add(logoutBt, gbc);
    setOpaque(false);
  }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    // Tạo màu gradient: từ màu trên (Color.BLUE) xuống màu dưới (Color.CYAN)
    int width = getWidth();
    int height = getHeight();

    GradientPaint gp = new GradientPaint(0, 0,new  Color(23, 159, 219), 0, height, new Color(11, 24, 63));

    g2d.setPaint(gp);
    g2d.fillRect(0, 0, width, height);
  }


  class ButtonsPanel extends JPanel {
    public ButtonsPanel() {
      setLayout(new GridBagLayout());
      //      setBackground(Style.MENU_BACKGROUND_COLOR);
      setBackground(Color.WHITE);
      GridBagConstraints gbc = new GridBagConstraints();

      gbc.gridx = 0;
      gbc.gridy = 0;
      gbc.insets = new Insets(10, 10, 15, 10);
      gbc.anchor = GridBagConstraints.WEST;
      gbc.fill = GridBagConstraints.NONE;

      appLogo =
          ButtonGenerator.createCustomButton(
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
      ButtonGenerator.setButtonIcon(
          "src/main/java/view/LibraryUI/images/fit_nlu_logo.jpg", appLogo, 10);
      appLogo.setIsDarkerWhenPress(false);
      add(appLogo, gbc);
      gbc.gridy++;

      JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
      separatorBot.setPreferredSize(new Dimension(220, 1));
      gbc.insets = new Insets(5, 0, 5, 0);
      add(separatorBot, gbc);
      gbc.gridy++;

      dashBoardBt =
          ButtonGenerator.createCustomButton(
              " Dashboard",
              Style.FONT_PLAIN_13,
              Color.WHITE,
              Style.PURPLE_MAIN_THEME,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/dashboardIcon.png", dashBoardBt, 20);

      add(dashBoardBt, gbc);
      gbc.gridy++;

      manageBookBt =
          ButtonGenerator.createCustomButton(
              " Manage Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon(
          "src/main/java/view/LibraryUI/icons/manageBook.png", manageBookBt, 18);
      add(manageBookBt, gbc);
      gbc.gridy++;

      lendedBookBt =
          ButtonGenerator.createCustomButton(
              " Lended Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon(
          "src/main/java/view/LibraryUI/icons/lendBookIcon.png", lendedBookBt, 15);
      add(lendedBookBt, gbc);
      gbc.gridy++;

      returnBookBt =
          ButtonGenerator.createCustomButton(
              " Return Books",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/customerIcon.png", returnBookBt, 20);
      add(returnBookBt, gbc);
      gbc.gridy++;

      readerBt =
          ButtonGenerator.createCustomButton(
              " Readers",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/orderIcon.png", readerBt, 20);
      add(readerBt, gbc);
      gbc.gridy++;

      librarianBt =
          ButtonGenerator.createCustomButton(
              " Librarians",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/inventoryIcon.png", librarianBt, 20);
      add(librarianBt, gbc);
      gbc.gridy++;

      settingBt =
          ButtonGenerator.createCustomButton(
              " Settings",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/user_15094854.png", settingBt, 20);
      add(settingBt, gbc);
      gbc.gridy++;

      myAccountBt =
          ButtonGenerator.createCustomButton(
              " My Account",
              Style.FONT_PLAIN_13,
              Color.BLACK,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon("src/main/java/Icon/iconNotification.png", myAccountBt, 20);
      add(myAccountBt, gbc);
      gbc.gridy++;

      logoutBt =
          ButtonGenerator.createCustomButton(
              " Logout",
              Style.FONT_PLAIN_13,
              Style.LOGOUT_RED,
              Color.WHITE,
              Style.HOVER_WHITE_DARKER,
              20,
              SwingConstants.LEFT,
              new Dimension(220, 45));
      ButtonGenerator.setButtonIcon(
          "src/main/java/view/LibraryUI/icons/logoutIcon.png", logoutBt, 25);
      Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
      int height = (int) screenSize.getHeight();
      int buttonHeight = 50 + 75 + 50 * 8 + 5 + 50;
      int logoutHeight = 40 + 45;
      gbc.insets = new Insets(height - (buttonHeight + logoutHeight), 0, 5, 0);
      add(logoutBt, gbc);
      setOpaque(false);
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
