package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.view.managementView.returnBooks.AddReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.controllers.AddReturnBookController;
import com.g15.library_system.view.managementView.returnBooks.controllers.IReturnController;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import javax.swing.*;

public class ConfirmReturnCommand implements Command {
  private AddReturnBookController addReturnBookController;
  private IReturnController returnController;
  private AddReturnBookPanel addReturnBookPanel;

  public ConfirmReturnCommand(
      AddReturnBookController returnBookController,
      IReturnController returnController,
              AddReturnBookPanel addReturnBookPanel) {
    this.addReturnBookController = returnBookController;
    this.returnController = returnController;
    this.addReturnBookPanel = addReturnBookPanel;
  }

  @Override
  public void execute() {
    if (addReturnBookController.validateDataAndProcessReturn()) {
      new ToastNotification(
              JOptionPane.getFrameForComponent(addReturnBookPanel),
              ToastNotification.Type.SUCCESS,
              ToastNotification.Location.TOP_CENTER,
              "New book returned successfully!")
          .showNotification();

      returnController.refreshTable();
    }
  }
}
