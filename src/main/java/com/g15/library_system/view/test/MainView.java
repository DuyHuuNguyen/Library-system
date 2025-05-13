package com.g15.library_system.view.test;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MainView extends JFrame implements Observer {
  private final DefaultTableModel tableModel;
  private final JTable table;
  private final JTextField nameField;
  private final JTextField ageField;
  private final JTabbedPane tabbedPane;

  public MainView() {
    setTitle("MVC + Observer (Modern)");
    setSize(500, 400);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    ReaderData.getInstance().registerObserver(this);

    tableModel = new DefaultTableModel(new String[] {"Name", "Age"}, 0);
    table = new JTable(tableModel);
    JButton addButton = new JButton("Add");

    JPanel tablePanel = new JPanel(new BorderLayout());
    tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
    tablePanel.add(addButton, BorderLayout.SOUTH);

    nameField = new JTextField();
    ageField = new JTextField();
    JButton confirmButton = new JButton("Xác nhận");

    JPanel formPanel = new JPanel(new GridLayout(3, 2));
    formPanel.add(new JLabel("Tên:"));
    formPanel.add(nameField);
    formPanel.add(new JLabel("Tuổi:"));
    formPanel.add(ageField);
    formPanel.add(new JLabel());
    formPanel.add(confirmButton);

    tabbedPane = new JTabbedPane();
    tabbedPane.add("Danh sách", tablePanel);
    tabbedPane.add("Thêm", formPanel);

    add(tabbedPane);

    // Controller
    DataController controller = new DataController(this);
    addButton.addActionListener(e -> controller.switchToAddTab());
    confirmButton.addActionListener(e -> controller.confirmAdd());
  }

  public String getNameInput() {
    return nameField.getText();
  }

  public int getAgeInput() {
    return Integer.parseInt(ageField.getText());
  }

  public void switchToTableTab() {
    tabbedPane.setSelectedIndex(0);
  }

  public void switchToAddTab() {
    tabbedPane.setSelectedIndex(1);
  }

  @Override
  public void update() {
    tableModel.setRowCount(0);
    for (Reader d : ReaderData.getInstance().getDataList()) {
      tableModel.addRow(new Object[] {d.getName(), d.getAge()});
    }
  }
}
