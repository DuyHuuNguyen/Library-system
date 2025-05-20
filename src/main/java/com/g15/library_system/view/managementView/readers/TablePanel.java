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

public class TablePanel extends JPanel {
  private JTable table;
  private DefaultTableModel tableModel;
  public CheckboxTablePanel tablePanel;
  // Book data
  public Object[][] memberData;
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
          "Birth Date",
          "Phone",
          "Email",
          "Address",
          "Membership date",
          "Total Fine"
        };
    memberData =
        new Object[][] {
          {

            "U001",
            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
            "John Doe",
            "Student",
            "1/1/2000",
            "0123456",
            "john.doe@example.com",
            "Linh Trung, Thu Duc, Tp.HCM",
            "20/5/2024",
            5
          },
          {

            "U002",
            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
            "John Wick",
            "Student",
            "1/2/2000",
            "012345678",
            "john.wick@example.com",
            "Quan Go Vap, Tp.HCM",
            "21/5/2024",
            10
          },
          {

            "U003",
            new ImageIcon(getClass().getResource("/images/John_Doe.png")),
            "John Cena",
            "Student",
            "1/3/2000",
            "09876543",
            "john.cena@example.com",
            "Tan Binh, Tp.HCM",
            "22/5/2024",
            15
          }
        };

    tablePanel = new CheckboxTablePanel(Arrays.copyOfRange(columnNames, 0, 5), memberData);
    tablePanel.setEditableColumns(Set.of(5));
//    tablePanel.setStatusEditable(true);

    // Column checkbox giả định là cột 0
    TableColumn checkboxColumn = tablePanel.getTable().getColumnModel().getColumn(0);
    CustomCheckBoxEditor editor = new CustomCheckBoxEditor();

    // Lắng nghe thay đổi trong dữ liệu model
    tablePanel.getTable().getModel().addTableModelListener(e -> {
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
}
