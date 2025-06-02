package com.g15.library_system.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class QuantityComboBoxTable extends JFrame {

    public QuantityComboBoxTable() {
        setTitle("Chọn số lượng trong JTable");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Dữ liệu mẫu: mỗi hàng là [Tên SP, Số lượng còn, Số lượng chọn (ComboBox)]
        Object[][] data = {
            {"Sản phẩm A", 5,5},
            {"Sản phẩm B", 3,3},
            {"Sản phẩm C", 7,7}
        };

        String[] columnNames = {"Tên sản phẩm", "Số lượng còn", "Số lượng chọn"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 1; // chỉ cho phép chỉnh cột "Số lượng chọn"
            }
        };

        JTable table = new JTable(model);

        // Custom editor cho cột "Số lượng chọn"
        TableColumn quantityColumn = table.getColumnModel().getColumn(1);
        quantityColumn.setCellEditor(new QuantityComboBoxEditor(table));

        this.add(new JScrollPane(table));
        this.setVisible(true);
    }

    // Custom CellEditor để hiển thị JComboBox
    class QuantityComboBoxEditor extends DefaultCellEditor {
        private JComboBox<Integer> comboBox;
        private JTable table;

        public QuantityComboBoxEditor(JTable table) {
            super(new JComboBox<>());
            this.table = table;
            this.comboBox = (JComboBox<Integer>) getComponent();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            comboBox.removeAllItems(); // Xoá item cũ

            int max = (Integer) table.getValueAt(row, 1); // Lấy "Số lượng còn" ở cột 1

            for (int i = max; i >= 1; i--) {
                comboBox.addItem(i);
            }

            comboBox.setSelectedItem(max);
            return comboBox;
        }

        @Override
        public Object getCellEditorValue() {
            return comboBox.getSelectedItem();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuantityComboBoxTable());
    }
}
