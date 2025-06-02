package com.g15.library_system.view.overrideComponent.tables.tableRenderers;

import javax.swing.*;
import java.awt.*;

public class QuantityComboBoxEditor extends DefaultCellEditor {
        private JComboBox<Integer> comboBox;
        private JTable table;
        private int borrowQuantityIndex;

        public QuantityComboBoxEditor(JTable table, int borrowQuantityIndex) {
            super(new JComboBox<>());
            this.table = table;
            this.borrowQuantityIndex = borrowQuantityIndex;
            this.comboBox = (JComboBox<Integer>) getComponent();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            comboBox.removeAllItems();

            int max = (Integer) this.table.getValueAt(row, borrowQuantityIndex);

            for (int i = max; i >= 1; i--) {
                comboBox.addItem(i);
            }

            comboBox.setSelectedItem(value);
            return comboBox;
        }

        @Override
        public Object getCellEditorValue() {
            return comboBox.getSelectedItem();
        }
    }