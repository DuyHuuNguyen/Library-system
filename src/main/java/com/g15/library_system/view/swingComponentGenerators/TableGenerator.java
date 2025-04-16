package com.g15.library_system.view.swingComponentGenerators;


import com.g15.library_system.view.Style;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class TableGenerator {
    private Color themeColor = Style.MENU_BUTTON_COLOR;

    public static JTable createBasicTable(DefaultTableModel model, String[] columnNames) {

        model =
                new DefaultTableModel(columnNames, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

        JTable table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setRowHeight(40);
        table.setFont(Style.FONT_PLAIN_16);
        resizeColumnWidth(table, 200);
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                           boolean hasFocus, int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                cell.setBackground(isSelected ? table.getSelectionBackground() :
                        (row % 2 == 0 ? Style.MENU_BACKGROUND_COLOR: Color.WHITE));
//                        (row % 2 == 0 ? new Color(156, 135, 246): Color.WHITE));
                return cell;
            }
        });

        JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(
                new TableCellRenderer() {
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {
                        JLabel label = new JLabel(value.toString());
                        label.setBackground(Style.MENU_BUTTON_COLOR);
                        label.setForeground(Color.BLACK);
                        label.setFont(Style.FONT_BOLD_16);
//                        label.setBorder(BorderFactory.createLineBorder(Color.GRAY));
                        label.setBorder(BorderFactory.createLineBorder(new Color(105, 115, 122, 55)));
                        label.setOpaque(true);
                        return label;
                    }
                });
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 50));
        header.setReorderingAllowed(false);
        header.setResizingAllowed(true);

        return table;
    }

    public static void resizeColumnWidth(JTable table, int width) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(width);
            table.getColumnModel().getColumn(0).setPreferredWidth(120);
            table.getColumnModel().getColumn(1).setPreferredWidth(120);
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
        }
    }

    public static void setColorScrollPane(
            JScrollPane scrollPane, Color thumbColor, Color trackColor) {
        setColorScrollBar(scrollPane.getVerticalScrollBar(), thumbColor, trackColor);
        setColorScrollBar(scrollPane.getHorizontalScrollBar(), thumbColor, trackColor);
    }

    public static void setColorScrollBar(
            JScrollBar scrollBar, Color scrollBarColor, Color trackBackGroundColor) {
        scrollBar.setUI(
                new BasicScrollBarUI() {
                    @Override
                    protected void configureScrollBarColors() {
                        this.thumbColor = scrollBarColor;
                        this.trackColor = trackBackGroundColor;
                    }
                });
    }
}
