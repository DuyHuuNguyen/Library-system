package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.view.managementView.returnBooks.AddReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.controllers.AddReturnBookController;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;

import javax.swing.*;

public class ConfirmReturnCommand implements Command {
      private final AddReturnBookController returnBookController;
      private AddReturnBookPanel addReturnBookPanel;

    public ConfirmReturnCommand(AddReturnBookController returnBookController, AddReturnBookPanel addReturnBookPanel) {
        this.returnBookController = returnBookController;
        this.addReturnBookPanel = addReturnBookPanel;
    }


    @Override
  public void execute() {
      if (returnBookController.validateDataAndProcessReturn()) {
        new ToastNotification(
                JOptionPane.getFrameForComponent(addReturnBookPanel),
                ToastNotification.Type.SUCCESS,
                ToastNotification.Location.TOP_CENTER,
                "New book returned successfully!")
                .showNotification();
        returnBookController.refreshTable();
      }
  }
}
