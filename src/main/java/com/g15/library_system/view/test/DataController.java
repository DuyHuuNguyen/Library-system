package com.g15.library_system.view.test;

import javax.swing.*;

public class DataController {
  private final MainView view;

  public DataController(MainView view) {
    this.view = view;
  }

  public void switchToAddTab() {
    view.switchToAddTab();
  }

  public void confirmAdd() {
    try {
      String name = view.getNameInput();
      int age = view.getAgeInput();
      ReaderData.getInstance().addData(new Reader(name, age));
      view.switchToTableTab();
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(null, "Tuổi không hợp lệ!");
    }
  }
}
