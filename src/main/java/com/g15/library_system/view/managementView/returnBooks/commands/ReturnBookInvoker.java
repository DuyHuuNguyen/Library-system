package com.g15.library_system.view.managementView.returnBooks.commands;

public class ReturnBookInvoker {
  private ICommand command;

  public void setCommand(ICommand command) {
    this.command = command;
  }

  public void executeCommand() {
    if (command != null) {
      command.execute();
    } else {
      System.out.println("No command set to execute.");
    }
  }
}
