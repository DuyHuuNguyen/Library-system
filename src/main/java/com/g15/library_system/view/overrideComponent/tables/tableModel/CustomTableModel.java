package com.g15.library_system.view.overrideComponent.tables.tableModel;

import java.util.HashSet;
import java.util.Set;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {

  private Set<Integer> editableRows = new HashSet<>();
  private boolean hasCheckbox;
  private JTable table;

  public CustomTableModel(Object[][] tableData, String[] columnNames) {
    super(processData(tableData, columnNames), processColumnNames(columnNames));
    hasCheckbox = columnNames.length > 0 && columnNames[0].equals("");
  }

  private static Object[][] processData(Object[][] data, String[] columnNames) {
    if (columnNames.length == 0 || !columnNames[0].equals("")) {
      return data;
    }

    Object[][] newData = new Object[data.length][data[0].length + 1];
    for (int i = 0; i < data.length; i++) {
      newData[i][0] = false;
      System.arraycopy(data[i], 0, newData[i], 1, data[i].length);
    }
    return newData;
  }

  private static String[] processColumnNames(String[] columnNames) {
    return columnNames;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    if (hasCheckbox && columnIndex == 0) {
      return Boolean.class;
    }
    return super.getColumnClass(columnIndex);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    if (hasCheckbox && column == 0) {
      return true;
    }

    return editableRows.contains(row);
  }

  public void setTable(JTable table) {
    this.table = table;
  }

  public void setEditableRows(Set<Integer> rows) {
    editableRows = rows;
  }

  public void clearEditableRows() {
    editableRows.clear();
  }
}
