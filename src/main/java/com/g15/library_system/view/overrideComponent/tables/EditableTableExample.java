package com.g15.library_system.view.overrideComponent.tables;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class EditableTableExample extends JFrame {
    private JTable table;
    private CustomTableModel model;
    private JButton editButton;
    private JButton saveButton;

    public EditableTableExample() {
        setTitle("Bảng có thể chỉnh sửa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        String[] columnNames = {"ID", "Tên", "Giá"};
        Object[][] data = {
            {1, "Sản phẩm A", 10000},
            {2, "Sản phẩm B", 20000},
            {3, "Sản phẩm C", 15000}
        };

        model = new CustomTableModel(data, columnNames);
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);

        editButton = new JButton("Sửa");
        saveButton = new JButton("Lưu");

        editButton.addActionListener(e -> {
            model.setEditable(true);
            table.repaint();
        });

        saveButton.addActionListener(e -> {
            model.setEditable(false);
            table.repaint();
            // In ra dữ liệu thử
            for (int i = 0; i < model.getRowCount(); i++) {
                System.out.println("Tên: " + model.getValueAt(i, 1) + ", Giá: " + model.getValueAt(i, 2));
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);


        String[] columnNames1 = {"ID", "Tên sản phẩm", "Giá"};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);


        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // Lớp model tùy chỉnh
    class CustomTableModel extends DefaultTableModel {
        private boolean editable = false;

        public CustomTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        public void setEditable(boolean editable) {
            this.editable = editable;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            // Không cho sửa cột ID
            return editable && column != 0;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EditableTableExample().setVisible(true);
        });
    }
}
