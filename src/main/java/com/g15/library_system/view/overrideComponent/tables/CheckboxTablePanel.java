package com.g15.library_system.view.overrideComponent.tables;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import com.formdev.flatlaf.FlatLightLaf;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentGenerators.TableGenerator;

public class CheckboxTablePanel extends JPanel {
    private String[] columnNames = {"", "Return ID", "Borrowing ID", "Reader Name","Return Date","Returned Books","Status","Fine","Processed By","Notes"};
    private JTable table;
    private DefaultTableModel tableModel;
    private boolean isSelectAll = false;

    public CheckboxTablePanel() {
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        tableModel = new DefaultTableModel(null, columnNames) {
            public Class<?> getColumnClass(int column) {
                return column == 0 ? Boolean.class : String.class;
            }

            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
        };

        // Sample data
        Object[][] data = {
                {false, "ubuntu", "latest", "a1b2c3d4", "5 days ago", "72 MB"},
                {false, "nginx", "1.21", "d4e5f6g7", "2 weeks ago", "133 MB"},
                {false, "ubuntu", "latest", "a1b2c3d4", "5 days ago", "72 MB"},
                {false, "nginx", "1.21", "d4e5f6g7", "2 weeks ago", "133 MB"},
                {false, "ubuntu", "latest", "a1b2c3d4", "5 days ago", "72 MB"},
                {false, "nginx", "1.21", "d4e5f6g7", "2 weeks ago", "133 MB"},
            {false, "ubuntu", "latest", "a1b2c3d4", "5 days ago", "72 MB"},
            {false, "nginx", "1.21", "d4e5f6g7", "2 weeks ago", "133 MB"},
            {false, "mysql", "8.0", "z9x8y7w6", "1 month ago", "456 MB"}
        };
        for (Object[] row : data) {
            tableModel.addRow(row);
        }

        table = new JTable(tableModel);
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);

        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        table.setDefaultRenderer(
                Object.class,
                new DefaultTableCellRenderer() {
                    @Override
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
                            c.setBackground(row % 2 == 1 ? new Color(230, 247, 255) : Color.WHITE);
                        } else {
                            c.setBackground(new Color(184, 207, 229));
                        }

                        return c;
                    }
                });

        // Custom cell renderer & editor for checkbox
        TableColumn checkboxCol = table.getColumnModel().getColumn(0);
        checkboxCol.setCellRenderer(new CustomCheckBoxRenderer());
        checkboxCol.setCellEditor(new CustomCheckBoxEditor());

        // Cố định kích thước checkbox column
        int checkboxWidth = 40;
        checkboxCol.setPreferredWidth(checkboxWidth);
        checkboxCol.setMaxWidth(checkboxWidth);
        checkboxCol.setMinWidth(checkboxWidth);
        checkboxCol.setResizable(false);

        // Header checkbox
        checkboxCol.setHeaderRenderer(new HeaderCheckboxRenderer());

        // Header click listener
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.addMouseListener(new MouseAdapter() {
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


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        TableGenerator.setColorScrollPane(
                scrollPane, Style.BLUE_MENU_BACKGROUND_COLOR, Style.CHART_BACKGROUND_COLOR);
        scrollPane.getVerticalScrollBar().setUnitIncrement(20);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public DefaultTableModel getModel(){
        return tableModel;
    }

    // Renderer cho checkbox nhỏ gọn
    private class CustomCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        public CustomCheckBoxRenderer() {
            setHorizontalAlignment(CENTER);
            setOpaque(true);
            setBackground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            setSelected(value != null && (Boolean) value);
            return this;
        }
    }

    // Editor cho checkbox
    private class CustomCheckBoxEditor extends DefaultCellEditor {
        private JCheckBox checkBox;

        public CustomCheckBoxEditor() {
            super(new JCheckBox());
            checkBox = (JCheckBox) getComponent();
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setBackground(Color.WHITE);
        }
    }

    // Header renderer với checkbox nhỏ
    private class HeaderCheckboxRenderer implements TableCellRenderer {
        private JCheckBox checkBox = new JCheckBox();

        public HeaderCheckboxRenderer() {
            checkBox.setHorizontalAlignment(SwingConstants.CENTER);
            checkBox.setOpaque(false);
        }

        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            checkBox.setSelected(isSelectAll);
            return checkBox;
        }
    }

    public static CheckboxTablePanel createCheckboxTablePanel() {
        return new CheckboxTablePanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example of how to add to a JFrame
            JFrame frame = new JFrame("Checkbox Table Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(850, 400);
            frame.setLocationRelativeTo(null);
            frame.add(createCheckboxTablePanel());
            frame.setVisible(true);
        });
    }
}
