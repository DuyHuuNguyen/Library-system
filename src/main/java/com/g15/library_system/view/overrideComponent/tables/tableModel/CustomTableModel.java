package com.g15.library_system.view.overrideComponent.tables.tableModel;

import java.util.HashSet;
import java.util.Set;
import javax.swing.table.DefaultTableModel;

public class CustomTableModel extends DefaultTableModel {
  private Set<Integer> editableRows = new HashSet<>();
  private Set<Integer> editableColumns = new HashSet<>();
  private Set<Integer> alwaysEditableColumns = new HashSet<>();
  private boolean hasCheckbox;

  public CustomTableModel(Object[][] data, String[] columnNames) {
    super(processData(data, columnNames), processColumnNames(columnNames));
    hasCheckbox = columnNames.length > 0 && columnNames[0].equals("");
  }

  private static Object[][] processData(Object[][] data, String[] columnNames) {
    if (columnNames.length == 0 || !columnNames[0].equals("")) {
      return data;
    }

    if (data == null || data.length == 0) {
      return new Object[0][columnNames.length];
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

    if (alwaysEditableColumns.contains(column)) {
      return true;
    }

    return editableRows.contains(row) && editableColumns.contains(column);
  }

  public void setEditableRows(Set<Integer> rows) {
    editableRows = rows;
  }

  public void clearEditableRows() {
    editableRows.clear();
  }

  public void setEditableColumns(Set<Integer> columns) {
    editableColumns = columns;
  }

  public void clearEditableColumns() {
    editableColumns.clear();
  }

  public void setAlwaysEditableColumns(Set<Integer> columns) {
    alwaysEditableColumns = columns;
  }

  public void addAlwaysEditableColumn(int column) {
    alwaysEditableColumns.add(column);
  }

  public void clearAlwaysEditableColumns() {
    alwaysEditableColumns.clear();
  }
}
