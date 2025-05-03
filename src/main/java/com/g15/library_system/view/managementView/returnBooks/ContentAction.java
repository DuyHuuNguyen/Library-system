package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.CustomButton;

public interface ContentAction {
  public Runnable editTable(CustomButton editButton);

  public void exportExcel();

  public void importExcel();

  public void refreshTable();
}
