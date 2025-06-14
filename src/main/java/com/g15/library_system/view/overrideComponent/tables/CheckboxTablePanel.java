package com.g15.library_system.view.overrideComponent.tables;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.tables.tableModel.CustomTableModel;
import com.g15.library_system.view.overrideComponent.tables.tableRenderers.*;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;
import javax.swing.table.*;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckboxTablePanel extends JPanel {
  private static final Logger log = LoggerFactory.getLogger(CheckboxTablePanel.class);
  private String[] columnNames;
  private String[] statuses = {"Returned", "Lost", "Overdue"};
  private Object[][] tableData;
  @Getter private JTable table;
  private CustomTableModel tableModel;
  @Getter private TableColumn checkboxCol;
  private TableColumnModel columnModel;
  private boolean isSelectAll = false;
  private boolean isEditMode = false; // edit mode (button turn it on)
  private Set<Integer> alwaysEditableColumns =
      new HashSet<>(); // always allow columns with indexes in set to be edited
  private Set<Integer> editableColumns =
      new HashSet<>(); // columns that are editable when edit mode is enabled

  public CheckboxTablePanel(String[] columnNames, Object[][] tableData) {
    this.setBorder(BorderFactory.createLineBorder(Style.BLUE_HEADER_TABLE_AND_BUTTON));
    this.columnNames = columnNames;
    this.tableData = tableData;
    setLayout(new BorderLayout());
    // table
    tableModel = new CustomTableModel(tableData, columnNames);
    table = new JTable(tableModel);
    table.setRowHeight(30);
    table.setFillsViewportHeight(true);
    table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
    TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
    table.setRowSorter(sorter);
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

    // table model
    tableModel.setEditableColumns(editableColumns);
    tableModel.setAlwaysEditableColumns(alwaysEditableColumns);

    // column model
    columnModel = table.getColumnModel();
    columnModel.getColumn(1).setPreferredWidth(50);

    var isHaveThirdColumn = this.columnNames.length > 3;
    if (isHaveThirdColumn) columnModel.getColumn(3).setPreferredWidth(120);

    setupCheckBoxColumn();
    setupTableHeader();
    setupStatusColumn();
    setupBooksNameColumnRenderer();
    setupBookCoverImageRenderer();
    setupQuantityColumn(40);
    setupReturnQuantityCol();
    resizeNotesColumn(265);
    this.add(createScrollPane(table), BorderLayout.CENTER);

    table.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mousePressed(MouseEvent e) {
            int row = table.rowAtPoint(e.getPoint());
            if (row == -1) {
              table.clearSelection();
            }
          }
        });
  }

  public void removeAllDataTable() {
    this.tableModel.setRowCount(0);
  }

  public void addDataToTable(Object[][] data) {
    for (int i = 0; i < data.length; i++) {
      if (data[i].length == columnNames.length - 1) {
        Object[] newRow = new Object[columnNames.length];
        newRow[0] = Boolean.FALSE;
        System.arraycopy(data[i], 0, newRow, 1, data[i].length);
        data[i] = newRow;
      } else if (!(data[i][0] instanceof Boolean)) {
        data[i][0] = Boolean.FALSE;
      }
    }

    this.tableData = data;
    for (Object[] row : data) {
      this.tableModel.addRow(row);
    }
  }

  public void setNewDataForTable(Object[][] data) {
    this.tableData = data;
    this.tableModel.setRowCount(0);
    this.addDataToTable(data);
    this.table.revalidate();
    this.table.repaint();
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

  /** @return the indices of all selected rows in the table */
  public int[] getSelectedRows() {
    return table.getSelectedRows();
  }

  /** @return the indices of all rows that is checked in the table */
  public List<Integer> getCheckedRows() {
    List<Integer> checkedRows = new ArrayList<>();
    for (int i = 0; i < tableModel.getRowCount(); i++) {
      if (Boolean.TRUE.equals(tableModel.getValueAt(i, 0))) {
        checkedRows.add(i);
      }
    }
    return checkedRows;
  }

  /**
   * get all selected rows data
   *
   * @return List<Object[]> of the selected rows
   */
  public List<Object[]> getSelectedRowsData() {
    List<Integer> selectedRows = getCheckedRows();
    List<Object[]> selectedData = new ArrayList<>();

    for (Integer row : selectedRows) {
      Object[] rowData = new Object[columnNames.length - 1];
      for (int i = 1; i < columnNames.length; i++) {
        int modelRow = table.convertRowIndexToModel(row);
        rowData[i - 1] = tableModel.getValueAt(modelRow, i);
      }
      selectedData.add(rowData);
    }

    return selectedData;
  }

  private class HeaderCheckboxRenderer implements TableCellRenderer {
    private final JPanel panel;
    private final JCheckBox checkBox;

    public HeaderCheckboxRenderer() {
      panel = new JPanel(new BorderLayout());
      panel.setOpaque(true);
      panel.setBackground(Color.WHITE);

      checkBox = new JCheckBox();
      checkBox.setHorizontalAlignment(SwingConstants.CENTER);
      checkBox.setOpaque(false);

      panel.setBorder(
          BorderFactory.createMatteBorder(0, 0, 2, 0, Style.BLUE_HEADER_TABLE_AND_BUTTON));
      panel.add(checkBox, BorderLayout.CENTER);
    }

    @Override
    public Component getTableCellRendererComponent(
        JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      checkBox.setSelected(isSelectAll);
      return panel;
    }
  }

  private void setupBookCoverImageRenderer() {
    int coverImgColIndex = Arrays.asList(columnNames).indexOf("Cover Image");
    int avaImgColIndex = Arrays.asList(columnNames).indexOf("Avatar Image");

    if (coverImgColIndex >= 0) {
      columnModel.getColumn(coverImgColIndex).setPreferredWidth(80);
      table.setRowHeight(120);
      columnModel.getColumn(coverImgColIndex).setCellRenderer(new ImageRenderer(90, 130));
    }
    if (avaImgColIndex >= 0) { // set col, row size for avatar image
      columnModel.getColumn(avaImgColIndex).setPreferredWidth(50);
      table.setRowHeight(80);
      columnModel.getColumn(avaImgColIndex).setCellRenderer(new ImageRenderer(70, 70));
    }
  }

  private void setupBooksNameColumnRenderer() {
    int imageColumnIndex = Arrays.asList(columnNames).indexOf("Returned Books");
    if (imageColumnIndex >= 0) {
      table.setRowHeight(50);
      columnModel.getColumn(imageColumnIndex).setCellRenderer(new MultiLineCellRenderer());
      columnModel.getColumn(imageColumnIndex).setPreferredWidth(245);
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
    if (columnNames[0].equals("")) {
      checkboxCol.setCellRenderer(new CustomCheckBoxRenderer());
      checkboxCol.setCellEditor(new CustomCheckBoxEditor());
      checkboxCol.setPreferredWidth(40);
      checkboxCol.setMaxWidth(40);
      checkboxCol.setMinWidth(40);
      checkboxCol.setHeaderRenderer(new HeaderCheckboxRenderer());
    }
  }

  private void setupQuantityColumn(int width) {
    int quantityColumnIndex = Arrays.asList(columnNames).indexOf("Quantity");
    if (quantityColumnIndex >= 0) {
      columnModel.getColumn(quantityColumnIndex).setPreferredWidth(width);
    }
  }

  private void setupReturnQuantityCol() {
    JComboBox<String> returnQuantityCbb = new JComboBox<>(statuses);
    returnQuantityCbb.setEditable(false);

    int quantityColumnIndex = Arrays.asList(columnNames).indexOf("Quantity");
    int returnQuantityColIndex = Arrays.asList(columnNames).indexOf("Return Quantity");
    if (returnQuantityColIndex >= 0) {
      columnModel.getColumn(returnQuantityColIndex).setPreferredWidth(40);
      columnModel
          .getColumn(returnQuantityColIndex)
          .setCellEditor(new QuantityComboBoxEditor(table, quantityColumnIndex));
    }
  }

  private JScrollPane createScrollPane(JTable table) {
    JScrollPane scrollPane = new JScrollPane(table);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane
        .getViewport()
        .addMouseListener(
            new MouseAdapter() {
              @Override
              public void mousePressed(MouseEvent e) {
                Point p =
                    SwingUtilities.convertPoint(scrollPane.getViewport(), e.getPoint(), table);
                int row = table.rowAtPoint(p);
                if (row == -1) {
                  table.clearSelection();
                }
              }
            });
    return scrollPane;
  }

  public void resizeNotesColumn(int width) {
    int notesColumnIndex = Arrays.asList(columnNames).indexOf("Notes");
    if (notesColumnIndex > 0) {
      columnModel.getColumn(notesColumnIndex).setPreferredWidth(width);
    }
  }

  public void clearHeaderCheckboxSelection() {
    isSelectAll = false;
    table.getTableHeader().repaint();
  }

  private void enterEditMode(CustomButton editButton) {
    boolean hasSelection = false;
    Set<Integer> editableRows = new HashSet<>();

    for (int viewRow = 0; viewRow < table.getRowCount(); viewRow++) {
      int modelRow = table.convertRowIndexToModel(viewRow);
      boolean isSelectedByCheckbox = Boolean.TRUE.equals(tableModel.getValueAt(modelRow, 0));
      boolean isSelectedByMouse = table.isRowSelected(viewRow);

      if (isSelectedByCheckbox || isSelectedByMouse) {
        editableRows.add(modelRow);
        hasSelection = true;
        if (tableModel.getColumnName(0).equals("")) {
          tableModel.setValueAt(true, modelRow, 0);
        }
      }
    }

    if (!hasSelection) {
      JOptionPane.showMessageDialog(this, "Please select at least one row to edit!");
      return;
    }

    isEditMode = true;
    editButton.setText("Save");
    editButton.setIcon("/icons/save.png", 15);
    tableModel.setEditableRows(editableRows);
  }

  private void saveEdits(CustomButton editButton) {
    isEditMode = false;
    editButton.setText("Edit");
    editButton.setIcon("/icons/edit.png", 15);

    for (Integer modelRow : tableModel.getEditableRows()) {
      Object[] rowData = new Object[tableModel.getColumnCount()];
      for (int col = 0; col < tableModel.getColumnCount(); col++) {
        rowData[col] = tableModel.getValueAt(modelRow, col);
      }
      // Find the corresponding Book in BookData (e.g., by ID column)
      //          Long bookId = (Long) rowData[idColumnIndex]; // set idColumnIndex to your ID
      // column
      //          Book book = BookData.getInstance().findById(bookId);
      //          if (book != null) {
      //            // Update book fields with new values from rowData
      //            book.setTitle((String) rowData[titleColIndex]);
      //          }
    }

    new ToastNotification(
            JOptionPane.getFrameForComponent(this),
            ToastNotification.Type.INFO,
            ToastNotification.Location.TOP_CENTER,
            "Your changes have been saved.")
        .showNotification();

    for (int i = 0; i < tableModel.getRowCount(); i++) {
      if (tableModel.getColumnName(0).equals("")) {
        tableModel.setValueAt(false, i, 0);
      }
    }

    tableModel.clearEditableRows();
    table.clearSelection();
  }

  public void setEditableColumns(Set<Integer> columns) {
    editableColumns.clear();
    editableColumns.addAll(columns);
  }

  public void setAlwaysEditableColumns(Set<Integer> columns) {
    alwaysEditableColumns.clear();
    alwaysEditableColumns.addAll(columns);
  }

  public void setStatuses(String[] statuses) {
    this.statuses = statuses;
  }

  public void setColumnSize(int columnIndex, int width) {
    columnModel.getColumn(columnIndex).setPreferredWidth(width);
  }

  public void setRowHeight(int height) {
    table.setRowHeight(height);
  }

  public void setRowSelectionHandler(Consumer<Integer> rowHandler) {
    table
        .getSelectionModel()
        .addListSelectionListener(
            event -> {
              if (!event.getValueIsAdjusting()) {
                int viewRow = table.getSelectedRow();
                if (viewRow != -1) {
                  int modelRow = table.convertRowIndexToModel(viewRow);
                  rowHandler.accept(modelRow);
                }
              }
            });
  }
}
