package com.g15.library_system.view.managementView;

import com.g15.library_system.enums.NavigationType;
import com.g15.library_system.view.LoginFrame;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {
  private NavigationPanel navigationPanel;
  private WorkspacePanel workspacePanel;

  public MainFrame() {
    setTitle("Library Management");
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) screenSize.getWidth();
    int height = (int) screenSize.getHeight();
    setSize(new Dimension(width, height - 40));
    setResizable(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLayout(new BorderLayout());
    setIconImage(
        new ImageIcon("src/main/resources/icons/libraryIconLogo.png").getImage());
    try {
      UIManager.put("ComboBox.focus", UIManager.get("ComboBox.background"));
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      SwingUtilities.updateComponentTreeUI(this);
    } catch (Exception e) {
      e.printStackTrace();
    }
    navigationPanel = new NavigationPanel();
    workspacePanel = new WorkspacePanel();
    add(navigationPanel, BorderLayout.WEST);
    add(workspacePanel, BorderLayout.CENTER);

    navigationPanel.setDashBoardBtListener(
        e -> {
          workspacePanel.showPanel(NavigationType.DASHBOARD);
          setHover(NavigationType.DASHBOARD);
        });

    navigationPanel.setManageBookButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.MANAGE_BOOKS);
          setHover(NavigationType.MANAGE_BOOKS);
        });

    navigationPanel.setLendedBookButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.LENDED_BOOKS);
          setHover(NavigationType.LENDED_BOOKS);
        });

    navigationPanel.setReturnBookButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.RETURN_BOOKS);
          setHover(NavigationType.RETURN_BOOKS);
        });

    navigationPanel.setOverdueBookButtonListener(
              e -> {
                  workspacePanel.showPanel(NavigationType.OVERDUE_BOOKS);
                  setHover(NavigationType.OVERDUE_BOOKS);
              });

    navigationPanel.setReaderButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.READERS);
          setHover(NavigationType.READERS);
        });

    navigationPanel.setLibrarianButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.LIBRARIANS);
          setHover(NavigationType.LIBRARIANS);
        });

    navigationPanel.setSettingButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.SETTINGS);
          setHover(NavigationType.SETTINGS);
        });

    navigationPanel.setMyAccountButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.MY_ACCOUNT);
          setHover(NavigationType.MY_ACCOUNT);
        });

    navigationPanel.setLogoutButtonListener(
        e -> {
          int confirmed =
              JOptionPane.showConfirmDialog(
                  null,
                  "Are you sure you want to logout?",
                  "Logout Confirmation",
                  JOptionPane.YES_NO_OPTION);

          if (confirmed == JOptionPane.YES_OPTION) {
            this.dispose();
            new LoginFrame();
          }
        });

    workspacePanel.setTotalBooksCardButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.MANAGE_BOOKS);
          setHover(NavigationType.MANAGE_BOOKS);
        });

    workspacePanel.setLendedBooksCardButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.LENDED_BOOKS);
          setHover(NavigationType.LENDED_BOOKS);
        });

    workspacePanel.setReturnedBooksCardButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.RETURN_BOOKS);
          setHover(NavigationType.RETURN_BOOKS);
        });

    workspacePanel.setOverdueBooksCardButtonListener(
              e -> {
                  workspacePanel.showPanel(NavigationType.OVERDUE_BOOKS);
                  setHover(NavigationType.OVERDUE_BOOKS);
              });

    workspacePanel.setTotalUsersCardButtonListener(
        e -> {
          workspacePanel.showPanel(NavigationType.READERS);
          setHover(NavigationType.READERS);
        });

    setVisible(true);
  }

  public void setHover(NavigationType navigationType) {
    CustomButton[] buttons = {
      navigationPanel.dashBoardBt,
      navigationPanel.manageBookBt,
      navigationPanel.lendedBookBt,
      navigationPanel.returnBookBt,
            navigationPanel.overdueBookBt,
      navigationPanel.readerBt,
      navigationPanel.librarianBt,
      navigationPanel.settingBt,
      navigationPanel.myAccountBt,
    };

    NavigationType[] constraints = {
      NavigationType.DASHBOARD,
      NavigationType.MANAGE_BOOKS,
      NavigationType.LENDED_BOOKS,
      NavigationType.RETURN_BOOKS,
            NavigationType.OVERDUE_BOOKS,
      NavigationType.READERS,
      NavigationType.LIBRARIANS,
      NavigationType.SETTINGS,
      NavigationType.MY_ACCOUNT,
    };
    for (int i = 0; i < buttons.length; i++) {
      buttons[i].setBackgroundColor(
          navigationType.equals(constraints[i]) ? Style.BLUE_MENU_BUTTON_COLOR : new Color(0,0,0,0));
    }
  }

  public static void main(String[] args) {
    new MainFrame();
  }
}
