package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.mapper.ITransactionMapper;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.controllers.ReturnManagementController;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import javax.swing.*;

public class RefreshCommand implements Command {
  private final ReturnManagementController returnManagementController;
  private final ReturnBookPanel returnBookPanel;
  private final ITransactionMapper transactionMapper;

  public RefreshCommand(
      ReturnManagementController returnManagementController,
      ReturnBookPanel returnBookPanel,
      ITransactionMapper transactionMapper) {
    this.returnManagementController = returnManagementController;
    this.returnBookPanel = returnBookPanel;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public void execute() {
    returnManagementController.getReturnBookDTOs().clear();
    returnManagementController.initTableData();
    returnBookPanel.setTableData(
        transactionMapper.toReturnBookTableData(returnManagementController.getReturnBookDTOs()));
    returnBookPanel.showPanel(ReturnBookPanel.TABLE_PANEL);

    new ToastNotification(
            JOptionPane.getFrameForComponent(returnBookPanel),
            ToastNotification.Type.INFO,
            ToastNotification.Location.TOP_CENTER,
            "Refreshing....")
        .showNotification();
  }
}
