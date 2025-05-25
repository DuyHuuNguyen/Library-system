package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.overrideComponent.tables.tableRenderers.CustomCheckBoxEditor;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lombok.Setter;

public class BorrowedHistoryPanel extends JPanel {
  private JTable table;
  private DefaultTableModel tableModel;
  public CheckboxTablePanel tablePanel;
  // Book data
  public Object[][] borrowedData;
  public String[] columnNames;
  @Setter private Reader reader;
  Object[] months = {"All", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

  Object[] years = {"All", 2021, 2022, 2023, 2024, 2025};

  JComboBox<Object> monthComboBox = new JComboBox<>(months);
  JComboBox<Object> yearComboBox = new JComboBox<>(years);

  public BorrowedHistoryPanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(createTitleLineBorder("Borrowed History"));
    setPreferredSize(new Dimension(this.getWidth(), 210));
    setVisible(false);

    columnNames =
        new String[] {"", "BookID", "Name", "Quantity", "Create At", "Expected Return At"};

    reader = ReaderData.getInstance().findId(1L);
    borrowedData = ReaderMapper.getCurrentBorrowedBooksAsTable(reader);

    tablePanel = new CheckboxTablePanel(Arrays.copyOfRange(columnNames, 0, 6), borrowedData);
    //    tablePanel.setEditableColumns(Set.of(4));
    //            tablePanel.setStatusEditable(true);

    // Căn giữa data giữa các ô trong bảng
    // ------------------------------------------------------------------
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

    JTable table = tablePanel.getTable(); // Giả sử bạn đã có JTable

    for (int i = 1; i < table.getColumnCount(); i++) {
      table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
    }
    // ------------------------------------------------------------------

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

    // Xử lí sự kiện lọc tháng và năm được chọn
    // ------------------------------------------------------------------
    ActionListener filterListener = e -> filterByMonthYear();

    monthComboBox.addActionListener(filterListener);
    yearComboBox.addActionListener(filterListener);
    // ------------------------------------------------------------------
  }

  private void filterByMonthYear() {
    Object selectedMonth = monthComboBox.getSelectedItem();
    Object selectedYear = yearComboBox.getSelectedItem();

    boolean allMonths = selectedMonth instanceof String && selectedMonth.equals("All");
    boolean allYears = selectedYear instanceof String && selectedYear.equals("All");

    List<Object[]> filteredRows = new ArrayList<>();

    for (Object[] row : borrowedData) {
      try {
        //        System.out.println("row[3] = " + row[4] + ", type = " + row[4].getClass());
        LocalDate createdDate =
            LocalDate.parse((String) row[4], DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        boolean matchesMonth = allMonths || createdDate.getMonthValue() == (Integer) selectedMonth;
        boolean matchesYear = allYears || createdDate.getYear() == (Integer) selectedYear;

        if (matchesMonth && matchesYear) {
          filteredRows.add(row);
        }
      } catch (Exception ex) {
        ex.printStackTrace(); // hoặc bỏ qua dòng bị lỗi
      }
    }

    // Cập nhật JTable
    Object[][] filteredData = filteredRows.toArray(new Object[0][0]);
    this.refreshFilterTable(filteredData);
  }

  public void refreshTable() {
    //    DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();

    // Lấy danh sách sách mượn từ reader mới truyền vào
    borrowedData = ReaderMapper.getCurrentBorrowedBooksAsTable(reader);

    System.out.println(Arrays.deepToString(borrowedData));

    // Xoá tất cả các dòng cũ
    tablePanel.removeAllDataTable();

    tablePanel.addDataToTable(borrowedData);

    //    System.out.println(Arrays.deepToString(tablePanel.getTableData()));

  }

  public void refreshFilterTable(Object[][] filteredData) {
    // Xoá tất cả các dòng cũ
    tablePanel.removeAllDataTable();

    // Thêm lại
    tablePanel.addDataToTable(filteredData);
  }

  public TitledBorder createTitleLineBorder(String title) {
    LineBorder border = new LineBorder(Style.BLUE_MENU_BACKGROUND_COLOR, 2);
    TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
    titledBorder.setTitleColor(Style.BLUE_MENU_BACKGROUND_COLOR);
    titledBorder.setTitleFont(Style.FONT_BOLD_20);
    return titledBorder;
  }
}
