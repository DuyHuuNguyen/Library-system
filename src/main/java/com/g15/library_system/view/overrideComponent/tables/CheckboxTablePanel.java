package com.g15.library_system.view.overrideComponent.tables;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.tableRenderers.*;
import com.g15.library_system.view.swingComponentGenerators.TableGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckboxTablePanel extends JPanel {
  private static final Logger log = LoggerFactory.getLogger(CheckboxTablePanel.class);
  private String[] columnNames;
  private String[] statuses = {"Returned", "Lost", "Damaged", "Overdue"};
  private Object[][] tableData;
  private JTable table;
  private CustomTableModel tableModel;
  private TableColumnModel columnModel;
  private boolean isSelectAll = false;
  private boolean isEditMode = false;
  private boolean isStatusEditable = false;
  private Set<Integer> editableColumns = new HashSet<>();
  private TableColumn checkboxCol;

  public CheckboxTablePanel(String[] columnNames, Object[][] tableData) {
    this.setBorder(BorderFactory.createLineBorder(Style.BLUE_HEADER_TABLE_AND_BUTTON));
    this.columnNames = columnNames;
    this.tableData = tableData;
    setLayout(new BorderLayout());
    tableModel = new CustomTableModel(tableData, columnNames);
    table = new JTable(tableModel);
    tableModel.setTable(table);

    table.setRowHeight(30);
    table.setFillsViewportHeight(true);
    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    table.setRowSorter(sorter);
    columnModel = table.getColumnModel();

    columnModel.getColumn(1).setPreferredWidth(50);

    var isHaveThirdColumn = this.columnNames.length > 3;
    if (isHaveThirdColumn) columnModel.getColumn(3).setPreferredWidth(80);

    table.setDefaultRenderer(
        Object.class,
        new DefaultTableCellRenderer() {
          public Component getTableCellRendererComponent(
              JTable table,
              Object value,
              boolean isSelected,
              boolean hasFocus,
              int row,
              int column) {
            Component c =
                super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
            if (!isSelected) {
              c.setBackground(row % 2 == 0 ? Color.WHITE : Style.LIGHT_BLUE_TABLE_ROW_COLOR);
            } else {
              c.setBackground(table.getSelectionBackground());
            }
            return c;
          }
        });

    setupCheckBoxColumn();
    setupTableHeader();
    setupStatusColumn();
    setupBooksNameColumnRenderer();
    setupBookCoverImageRenderer();
    resizeNotesColumn(300);

    this.add(createScrollPane(table), BorderLayout.CENTER);
  }

  public void removeAllDataTable() {
    this.tableModel.setRowCount(0);
  }

  public void addDataToTable(Object[][] data) {
    this.tableData = data;
    this.tableModel.addRow(this.tableData);
  }

  public Object[] getSelectedRowData() {
    int selectedRow = table.getSelectedRow();

    if (selectedRow == -1) {
      return null;
    }

    Object[] rowData = new Object[this.columnNames.length];

    for (int i = 0; i < rowData.length; i++) {
      rowData[i] = table.getValueAt(selectedRow, i);
    }

    return rowData;
  }

  private class CustomTableModel extends DefaultTableModel {
    private JTable table;

    public CustomTableModel(Object[][] data, Object[] columnNames) {
      super(data, columnNames);
    }

    public void setTable(JTable table) {
      this.table = table;
    }

    public boolean isCellEditable(int row, int column) {
      if (column == 0) return true;
      if (column == Arrays.asList(columnNames).indexOf("Status") && isStatusEditable) {
        return true;
      }
      if (!isEditMode) return false;

      boolean isChecked = Boolean.TRUE.equals(getValueAt(row, 0));
      int modelRow = row;
      int[] selectedViewRows = table.getSelectedRows();
      boolean isSelected =
          Arrays.stream(selectedViewRows)
              .map(table::convertRowIndexToModel)
              .anyMatch(r -> r == modelRow);
      return (isChecked || isSelected) && editableColumns.contains(column);
    }

    public Class<?> getColumnClass(int column) {
      return column == 0 ? Boolean.class : String.class;
    }
  }

  private class HeaderCheckboxRenderer implements TableCellRenderer {
    private JCheckBox checkBox = new JCheckBox();

    public HeaderCheckboxRenderer() {
      checkBox.setHorizontalAlignment(SwingConstants.CENTER);
      checkBox.setOpaque(false);
    }

    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      checkBox.setSelected(isSelectAll);
      return checkBox;
    }
  }

  private void setupBookCoverImageRenderer() {
    int imageColumnIndex = Arrays.asList(columnNames).indexOf("Cover Image");
    if (imageColumnIndex >= 0) {
      columnModel.getColumn(imageColumnIndex).setPreferredWidth(50);
      table.setRowHeight(120);
      columnModel.getColumn(imageColumnIndex).setCellRenderer(new ImageRenderer(70, 120));
    }
  }

  private void setupBooksNameColumnRenderer() {
    int imageColumnIndex = Arrays.asList(columnNames).indexOf("Returned Books");
    if (imageColumnIndex >= 0) {
      table.setRowHeight(50);
      columnModel.getColumn(imageColumnIndex).setCellRenderer(new MultiLineCellRenderer());
      columnModel.getColumn(imageColumnIndex).setPreferredWidth(200);
    }
  }

  private void setupTableHeader() {
    JTableHeader header = table.getTableHeader();
    header.setReorderingAllowed(false);
    header.addMouseListener(
        new MouseAdapter() {
          public void mouseClicked(MouseEvent e) {
            int col = table.columnAtPoint(e.getPoint());
            if (col == 0) {
              isSelectAll = !isSelectAll;
              for (int i = 0; i < tableModel.getRowCount(); i++) {
                tableModel.setValueAt(isSelectAll, i, 0);
              }
              header.repaint();
            }
          }
        });
    header.setPreferredSize(new Dimension(header.getWidth(), 45));
    header.setBackground(Style.BLUE_HEADER_TABLE_AND_BUTTON);
  }

  private void setupStatusColumn() {
    JComboBox<String> statusComboBox = new JComboBox<>(statuses);
    statusComboBox.setEditable(false);

    int statusColumnIndex = Arrays.asList(columnNames).indexOf("Status");
    if (statusColumnIndex >= 0) {
      columnModel.getColumn(statusColumnIndex).setCellEditor(new DefaultCellEditor(statusComboBox));
      columnModel.getColumn(statusColumnIndex).setCellRenderer(new StatusCellRenderer());
      columnModel.getColumn(statusColumnIndex).setPreferredWidth(60);
    }
  }

  private void setupCheckBoxColumn() {
    checkboxCol = columnModel.getColumn(0);
    checkboxCol.setCellRenderer(new CustomCheckBoxRenderer());
    checkboxCol.setCellEditor(new CustomCheckBoxEditor());
    checkboxCol.setPreferredWidth(40);
    checkboxCol.setMaxWidth(40);
    checkboxCol.setMinWidth(40);
    checkboxCol.setHeaderRenderer(new HeaderCheckboxRenderer());
  }

  private JScrollPane createScrollPane(JTable table) {
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    TableGenerator.setColorScrollPane(
        scrollPane, Style.BLUE_MENU_BACKGROUND_COLOR, Style.CHART_BACKGROUND_COLOR);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    return scrollPane;
  }

  public Runnable getActionForEditingTable(CustomButton editButton) {
    Runnable runnable =
        () -> {
          if (!isEditMode) {
            boolean hasCheckboxSelected = false;
            for (int i = 0; i < tableModel.getRowCount(); i++) {
              if (Boolean.TRUE.equals(tableModel.getValueAt(i, 0))) {
                hasCheckboxSelected = true;
                break;
              }
            }
            boolean hasMouseSelection = table.getSelectedRowCount() > 0;

            if (!hasCheckboxSelected && !hasMouseSelection) {
              JOptionPane.showMessageDialog(this, "Please select at least one row to edit!");
              return;
            }

            isEditMode = true;
            editButton.setText("Save");
            editButton.setIcon("/icons/save.png", 15);

            for (int viewRow = 0; viewRow < table.getRowCount(); viewRow++) {
              int modelRow = table.convertRowIndexToModel(viewRow);
              if (table.isRowSelected(viewRow)
                  || Boolean.TRUE.equals(tableModel.getValueAt(modelRow, 0))) {
                tableModel.setValueAt(true, modelRow, 0);
              }
            }
          } else {
            isEditMode = false;
            editButton.setText("Edit");
            editButton.setIcon("/icons/edit.png", 15);

            for (int i = 0; i < tableModel.getRowCount(); i++) {
              if (Boolean.TRUE.equals(tableModel.getValueAt(i, 0))) {
                tableModel.setValueAt(false, i, 0);
              }
            }

            table.clearSelection();
          }

          table.repaint();
        };
    return runnable;
  }

  public void setEditableColumns(Set<Integer> columns) {
    editableColumns.clear();
    editableColumns.addAll(columns);
  }

  public void setStatusEditable(boolean statusEditable) {
    isStatusEditable = statusEditable;
  }

  public void resizeNotesColumn(int width) {
    int notesColumnIndex = Arrays.asList(columnNames).indexOf("Notes");
    if (notesColumnIndex > 0) {
      columnModel.getColumn(notesColumnIndex).setPreferredWidth(width);
    }
  }

  public void setColumnSize(int columnIndex, int width) {
    columnModel.getColumn(columnIndex).setPreferredWidth(width);
  }
}
