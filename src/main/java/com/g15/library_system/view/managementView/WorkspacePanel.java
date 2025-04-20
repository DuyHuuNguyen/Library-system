package com.g15.library_system.view.managementView;

import com.g15.library_system.enums.NavigationType;
import com.g15.library_system.view.managementView.dashboard.*;
import com.g15.library_system.view.managementView.lendedBooks.LendedBookPanel;
import com.g15.library_system.view.managementView.librarians.LibrarianPanel;
import com.g15.library_system.view.managementView.manageBooks.ManageBookPanel;
import com.g15.library_system.view.managementView.myAccount.MyAccountPanel;
import com.g15.library_system.view.managementView.readers.ReaderPanel;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.setting.SettingPanel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WorkspacePanel extends JPanel {
  private CardLayout cardLayout = new CardLayout();
  private DashBoardPanel dashBoardPanel;
  private ManageBookPanel manageBookPanel;
  private LendedBookPanel lendedBookPanel;
  private ReturnBookPanel returnBookPanel;
  private ReaderPanel readerPanel;
  private LibrarianPanel librarianPanel;
  private SettingPanel settingPanel;
  private MyAccountPanel myAccountPanel;

  WorkspacePanel() {
    dashBoardPanel = new DashBoardPanel();
    manageBookPanel = new ManageBookPanel();
    lendedBookPanel = new LendedBookPanel();
    returnBookPanel = new ReturnBookPanel();
    readerPanel = new ReaderPanel();
    librarianPanel = new LibrarianPanel();
    settingPanel = new SettingPanel();
    myAccountPanel = new MyAccountPanel();

    setLayout(cardLayout);

    add(dashBoardPanel, NavigationType.DASHBOARD.getCardName());
    add(manageBookPanel, NavigationType.MANAGE_BOOKS.getCardName());
    add(lendedBookPanel, NavigationType.LENDED_BOOKS.getCardName());
    add(returnBookPanel, NavigationType.RETURN_BOOKS.getCardName());
    add(readerPanel, NavigationType.READERS.getCardName());
    add(librarianPanel, NavigationType.LIBRARIANS.getCardName());
    add(settingPanel, NavigationType.SETTINGS.getCardName());
    add(myAccountPanel, NavigationType.MY_ACCOUNT.getCardName());

    showPanel(NavigationType.DASHBOARD);
  }

  public void showPanel(NavigationType navigationType) {
    cardLayout.show(this, navigationType.getCardName());
  }

  public void setTotalBooksCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setTotalBooksCardButtonListener(listener);
  }

  public void setLendedBooksCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setLendedBooksCardButtonListener(listener);
  }

  public void setReturnedBooksCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setReturnedBooksCardButtonListener(listener);
  }

  public void setAvailableBooksCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setAvailableBooksCardButtonListener(listener);
  }

  public void setTotalUsersCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setTotalUsersCardButtonListener(listener);
  }

  public void setOverdueBooksCardButtonListener(ActionListener listener) {
    this.dashBoardPanel.setOverdueBooksCardButtonListener(listener);
  }
}
