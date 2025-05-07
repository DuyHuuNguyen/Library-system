package com.g15.library_system.view.managementView;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NavigationPanel extends JPanel {
  private JLabel role, name, credit;

  public CustomButton dashBoardBt,
      manageBookBt,
      lendedBookBt,
      returnBookBt,
      overdueBookBt,
      readerBt,
      librarianBt,
      settingBt,
      myAccountBt,
      logoutBt,
      appLogo;

  private GridBagConstraints gbc;

  public NavigationPanel() {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.insets = new Insets(10, 10, 15, 10);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.fill = GridBagConstraints.NONE;

    appLogo =
        CustomButtonBuilder.builder()
            .text("FIT Library")
            .font(Style.FONT_BOLD_25)
            .textColor(Style.BLUE_TEXT_COLOR)
            .backgroundColor(Color.WHITE)
            .hoverColor(null)
            .radius(10)
            .alignment(SwingConstants.CENTER)
            .drawBorder(false)
            .opaque(false)
            .darkerWhenPress(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 65))
            .icon("/images/fit_nlu_logo.jpg", 15);
    add(appLogo, gbc);
    gbc.gridy++;

    JSeparator separatorTop = new JSeparator(SwingConstants.HORIZONTAL);
    separatorTop.setPreferredSize(new Dimension(220, 2));
    separatorTop.putClientProperty("JSeparator.style", "color: #FFFFFF");
    gbc.insets = new Insets(5, 10, 5, 10);
    add(separatorTop, gbc);
    gbc.gridy++;

    dashBoardBt =
        CustomButtonBuilder.builder()
            .text("   Dashboard")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(Style.BLUE_MENU_BUTTON_COLOR)
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/computer.png", 20);
    add(dashBoardBt, gbc);
    gbc.gridy++;

    manageBookBt =
        CustomButtonBuilder.builder()
            .text("   Manage Books")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/menuBookIcon.png", 20);
    add(manageBookBt, gbc);
    gbc.gridy++;

    lendedBookBt =
        CustomButtonBuilder.builder()
            .text("   Lended Books")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/pending_actions.png", 20);
    add(lendedBookBt, gbc);
    gbc.gridy++;

    returnBookBt =
        CustomButtonBuilder.builder()
            .text("   Return Books")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/book_2.png", 20);
    add(returnBookBt, gbc);
    gbc.gridy++;

    overdueBookBt =
        CustomButtonBuilder.builder()
            .text("   Overdue Books")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/assignment_late.png", 20);
    add(overdueBookBt, gbc);
    gbc.gridy++;

    JSeparator separatorMid = new JSeparator(SwingConstants.HORIZONTAL);
    separatorMid.setPreferredSize(new Dimension(220, 2));
    separatorMid.putClientProperty("JSeparator.style", "color: #FFFFFF");
    add(separatorMid, gbc);
    gbc.gridy++;

    readerBt =
        CustomButtonBuilder.builder()
            .text("   Readers")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/groups.png", 20);
    add(readerBt, gbc);
    gbc.gridy++;

    librarianBt =
        CustomButtonBuilder.builder()
            .text("   Librarians")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/users.png", 20);
    add(librarianBt, gbc);
    gbc.gridy++;

    JSeparator separatorBot = new JSeparator(SwingConstants.HORIZONTAL);
    separatorBot.setPreferredSize(new Dimension(220, 2));
    separatorBot.putClientProperty("JSeparator.style", "color: #FFFFFF");
    add(separatorBot, gbc);
    gbc.gridy++;

    myAccountBt =
        CustomButtonBuilder.builder()
            .text("   My Account")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/account_circle.png", 20);
    add(myAccountBt, gbc);
    gbc.gridy++;

    settingBt =
        CustomButtonBuilder.builder()
            .text("   Settings")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/settingsIcon.png", 20);
    add(settingBt, gbc);
    gbc.gridy++;

    logoutBt =
        CustomButtonBuilder.builder()
            .text("   Logout")
            .font(Style.FONT_SANS_SERIF_PLAIN_15)
            .textColor(Color.WHITE)
            .backgroundColor(new Color(0, 0, 0, 0))
            .hoverColor(Style.BLUE_MENU_HOVER_COLOR)
            .radius(12)
            .alignment(SwingConstants.LEFT)
            .drawBorder(false)
            .opaque(false)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(220, 40))
            .icon("/icons/exit_to_app.png", 20);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int height = (int) screenSize.getHeight();
    int buttonHeight = 50 + 75 + 50 * 9 + 5 + 50;
    int logoutHeight = 40 + 45;
    gbc.insets = new Insets(height - (buttonHeight + logoutHeight), 10, 5, 10);
    add(logoutBt, gbc);
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;
    int width = getWidth();
    int height = getHeight();

    GradientPaint gp =
        new GradientPaint(
            0, 0, Style.BLUE_MENU_BACKGROUND_COLOR, 0, height, new Color(11, 24, 63)); // blue
    //        GradientPaint gp = new GradientPaint(0, 0,new  Color(75, 107, 220), 0, height, new
    // Color(164, 115, 186));
    g2d.setPaint(gp);
    g2d.fillRect(0, 0, width, height);
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

  public void setOverdueBookButtonListener(ActionListener listener) {
    overdueBookBt.addActionListener(listener);
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
