package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.overrideComponent.tables.tableRenderers.CustomCheckBoxEditor;
import java.awt.*;
import java.util.Arrays;
import java.util.Set;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class BorrowedHistoryPanel extends JPanel {
  private JTable table;
  private DefaultTableModel tableModel;
  public CheckboxTablePanel tablePanel;
  // Book data
  public Object[][] borrowedData;
  public String[] columnNames;
  String[] months = {
    "All",
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
  };

  String[] years = {"All", "2021", "2022", "2023", "2024", "2025"};

  JComboBox<String> monthComboBox = new JComboBox<>(months);
  JComboBox<String> yearComboBox = new JComboBox<>(years);

  public BorrowedHistoryPanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(createTitleLineBorder("Borrowed History"));
    setPreferredSize(new Dimension(this.getWidth(), 210));
    setVisible(false);

    columnNames = new String[] {"", "BookID", "Name", "Status"};
    borrowedData =
        new Object[][] {
          {"#11111", "The false in our star", "overdue"},
          {"#11112", "The Science", "returned"},
          {"#11113", "A Moon", "returned"}
        };

    tablePanel = new CheckboxTablePanel(Arrays.copyOfRange(columnNames, 0, 4), borrowedData);
    tablePanel.setEditableColumns(Set.of(4));
    //            tablePanel.setStatusEditable(true);

    // Column checkbox giả định là cột 0
    TableColumn checkboxColumn = tablePanel.getTable().getColumnModel().getColumn(0);
    CustomCheckBoxEditor editor = new CustomCheckBoxEditor();

    // Lắng nghe thay đổi trong dữ liệu model
    tablePanel
        .getTable()
        .getModel()
        .addTableModelListener(
            e -> {
              int selectedCount = 0;
              for (int i = 0; i < tablePanel.getTable().getModel().getRowCount(); i++) {
                Object val = tablePanel.getTable().getModel().getValueAt(i, 0);
                if (val instanceof Boolean && (Boolean) val) {
                  selectedCount++;
                }
              }
              //                readerPn.toolPn.getRemoveBt().setVisible(selectedCount > 0);
            });

    // Thiết lập editor cho cột checkbox (ví dụ cột 0)
    tablePanel.getCheckboxCol().setCellEditor(editor);

    add(tablePanel, BorderLayout.CENTER);

    JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    filterPanel.add(new JLabel("Month:"));
    filterPanel.add(monthComboBox);
    filterPanel.add(new JLabel("Year:"));
    filterPanel.add(yearComboBox);

    add(filterPanel, BorderLayout.NORTH);
  }

  public TitledBorder createTitleLineBorder(String title) {
    LineBorder border = new LineBorder(Style.BLUE_MENU_BACKGROUND_COLOR, 2);
    TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
    titledBorder.setTitleColor(Style.BLUE_MENU_BACKGROUND_COLOR);
    titledBorder.setTitleFont(Style.FONT_BOLD_20);
    return titledBorder;
  }
}
