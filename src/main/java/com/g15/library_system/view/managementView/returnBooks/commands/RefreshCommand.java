package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.controllers.IReturnController;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import javax.swing.*;

public class RefreshCommand implements Command {
  private final IReturnController returnManagementController;
  private final ReturnBookPanel returnBookPanel;

  public RefreshCommand(
      IReturnController returnManagementController, ReturnBookPanel returnBookPanel) {
    this.returnManagementController = returnManagementController;
    this.returnBookPanel = returnBookPanel;
  }

  @Override
  public void execute() {
    returnManagementController.refreshTable();

    new ToastNotification(
            JOptionPane.getFrameForComponent(returnBookPanel),
            ToastNotification.Type.INFO,
            ToastNotification.Location.TOP_CENTER,
            "Refreshing....")
        .showNotification();
  }
}
