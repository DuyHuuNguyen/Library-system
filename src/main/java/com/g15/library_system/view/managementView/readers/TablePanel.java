package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import com.g15.library_system.view.overrideComponent.tables.tableRenderers.CustomCheckBoxEditor;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import lombok.Getter;

public class TablePanel extends JPanel {
  private JTable table;
  private DefaultTableModel tableModel;
  @Getter public CheckboxTablePanel tablePanel;
  // Book data
  @Getter public Object[][] memberData;
  public List<Reader> readerList = ReaderData.getInstance().getReaders();
  public String[] columnNames;

  public TablePanel(ReaderPanel readerPn) {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(createTitleLineBorder("Member List"));

    columnNames =
        new String[] {
          "",
          "ID",
          "Cover Image",
          "Name",
          "Reader Type",
          "User Status",
          "Birth Date",
          "Phone",
          "Email",
          "Address",
          "Membership date",
          "Total Fine"
        };
    //    memberData =
    //        new Object[][] {
    //          {
    //            "U001",
    //            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
    //            "John Doe",
    //            "Student",
    //                  UserStatus.ACTIVE,
    //            "1/1/2000",
    //            "0123456",
    //            "john.doe@example.com",
    //            "Linh Trung, Thu Duc, Tp.HCM",
    //            "20/5/2024",
    //            5
    //          },
    //          {
    //            "U002",
    //            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
    //            "John Wick",
    //            "Student",
    //                  UserStatus.INACTIVE,
    //            "1/2/2000",
    //            "012345678",
    //            "john.wick@example.com",
    //            "Quan Go Vap, Tp.HCM",
    //            "21/5/2024",
    //            10
    //          },
    //          {
    //            "U003",
    //            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
    //            "John Cena",
    //            "Student",
    //                  UserStatus.BANNED,
    //            "1/3/2000",
    //            "09876543",
    //            "john.cena@example.com",
    //            "Tan Binh, Tp.HCM",
    //            "22/5/2024",
    //            15
    //          }
    //        };
    memberData = ReaderMapper.mapAllReadersToTableData(readerList, true);

    tablePanel = new CheckboxTablePanel(Arrays.copyOfRange(columnNames, 0, 6), memberData);
    //    tablePanel.setEditableColumns(Set.of(6));
    //    tablePanel.setStatusEditable(true);

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
              readerPn.toolPn.getRemoveBt().setVisible(selectedCount > 0);
            });

    // Thiết lập editor cho cột checkbox (ví dụ cột 0)
    tablePanel.getCheckboxCol().setCellEditor(editor);

    add(tablePanel, BorderLayout.CENTER);
  }

  public TitledBorder createTitleLineBorder(String title) {
    LineBorder border = new LineBorder(Style.BLUE_MENU_BACKGROUND_COLOR, 2);
    TitledBorder titledBorder = BorderFactory.createTitledBorder(border, title);
    titledBorder.setTitleColor(Style.BLUE_MENU_BACKGROUND_COLOR);
    titledBorder.setTitleFont(Style.FONT_BOLD_20);
    return titledBorder;
  }

  public void refreshTable() {
    // Xoá tất cả các dòng cũ
    DefaultTableModel model = (DefaultTableModel) tablePanel.getTable().getModel();
    //    System.out.println(Arrays.deepToString(memberData));
    //     Test thử logic add member
    //    ---------------------------------------------------------
    //        var member11 = Reader.builder()
    //                .id(11L)
    //                .email("duynguyenavg@gmail.com")
    //                .firstName("John")
    //                .lastName("Doe")
    //                .address("123 Main St")
    //                .dateOfBirth(978307200000L) // 2001-01-01
    //                .createdAt(1746988800000L) // 2025-05-11
    //                .avatarKey("/images/John_Doe.png")
    //                .phoneNumber("123456789")
    //                .isSubscribe(true)
    //                .readerType(
    //                        StudentReaderType.builder()
    //                                .faculty("Information Technology")
    //                                .enrollmentYear(2021)
    //                                .studentID("IT2021001")
    //                                .build())
    //                .build();
    //        ReaderData.getInstance().add(member11);
    //    ---------------------------------------------------------

    // Lấy danh sách Reader mới từ Singleton
    List<Reader> readers = ReaderData.getInstance().getReaders();

    memberData = ReaderMapper.mapAllReadersToTableData(readers, false);

    //    System.out.println(Arrays.deepToString(memberData));

    tablePanel.removeAllDataTable();

    tablePanel.addDataToTable(memberData);

    //    System.out.println(Arrays.deepToString(tablePanel.getTableData()));

  }

  public static Object[][] removeLeadingFalseColumnIfPresent(Object[][] data) {
    // Kiểm tra: mọi hàng đều có cột đầu là Boolean.FALSE
    boolean allHaveFalseCheckbox = true;
    for (Object[] row : data) {
      if (row.length == 0 || !(row[0] instanceof Boolean) || !Boolean.FALSE.equals(row[0])) {
        allHaveFalseCheckbox = false;
        break;
      }
    }

    if (!allHaveFalseCheckbox) {
      return data; // Không sửa gì cả nếu không thỏa điều kiện
    }

    // Tạo mảng mới với dữ liệu đã loại bỏ cột đầu tiên
    Object[][] newData = new Object[data.length][];
    for (int i = 0; i < data.length; i++) {
      Object[] oldRow = data[i];
      Object[] newRow = new Object[oldRow.length - 1];
      System.arraycopy(oldRow, 1, newRow, 0, newRow.length);
      newData[i] = newRow;
    }

    return newData;
  }
}
