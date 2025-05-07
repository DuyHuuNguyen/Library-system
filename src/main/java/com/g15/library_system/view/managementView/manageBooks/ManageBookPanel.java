package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.swingComponentGenerators.TableGenerator;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ManageBookPanel extends JPanel {
  public ManageBookPanel() {
    setLayout(new BorderLayout());
    add(new ToolPanel(), BorderLayout.NORTH);

    String[] cols = {"stt", "name", "number"};
    JTable table = TableGenerator.createBasicTable(new DefaultTableModel(), cols);
    DefaultTableModel model = (DefaultTableModel) table.getModel();
    for (int i = 0; i < 3; i++) {
      model.addRow(new String[] {"1", "hello", "123"});
    }

    //    add(new JScrollPane(table));
  }
}
