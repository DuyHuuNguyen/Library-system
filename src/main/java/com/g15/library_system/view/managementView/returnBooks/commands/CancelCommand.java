package com.g15.library_system.view.managementView.returnBooks.commands;

import com.g15.library_system.view.managementView.returnBooks.AddReturnBookPanel;
import com.g15.library_system.view.managementView.returnBooks.ReturnBookPanel;

public class CancelCommand implements ICommand {
  private ReturnBookPanel returnBookPanel;
  private AddReturnBookPanel addReturnBookPanel;

  public CancelCommand(ReturnBookPanel returnBookPanel, AddReturnBookPanel addReturnBookPanel) {
    this.returnBookPanel = returnBookPanel;
    this.addReturnBookPanel = addReturnBookPanel;
  }

  @Override
  public void execute() {
    this.addReturnBookPanel.clearAllTextField();
    this.addReturnBookPanel.clearTableData();
    this.returnBookPanel.showPanel(ReturnBookPanel.TABLE_PANEL);
  }
}
