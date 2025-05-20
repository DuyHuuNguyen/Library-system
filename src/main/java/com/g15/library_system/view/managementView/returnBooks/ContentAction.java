package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.CustomButton;

public interface ContentAction {
  Runnable editTable(CustomButton editButton, CustomButton cancelButton);

  void exportExcel();

  void refreshTable();
}
