package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentPanel extends JPanel {
  private JTextField txtFullName, txtEmail, txtDob, txtPhone, txtStudentId;
  private JTextArea txtAddress;
  private JComboBox<String> cbStatus, cbEnrollmentYear, cbFaculty;
  private JTable tblBorrowedBooks, tblHistory;

  public StudentPanel() {
    setLayout(new BorderLayout(10, 10));

    // === Thông tin sinh viên (bên trái) ===
    JPanel infoPanel = new JPanel(new GridBagLayout());
    infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;
    int row = 0;

    // Dòng 1
    gbc.gridx = 0;
    gbc.gridy = row;
    infoPanel.add(new JLabel("Full Name:"), gbc);
    gbc.gridx = 1;
    txtFullName = new JTextField();
    infoPanel.add(txtFullName, gbc);

    gbc.gridx = 2;
    infoPanel.add(new JLabel("Email:"), gbc);
    gbc.gridx = 3;
    txtEmail = new JTextField();
    infoPanel.add(txtEmail, gbc);
    row++;

    // Dòng 2
    gbc.gridx = 0;
    gbc.gridy = row;
    infoPanel.add(new JLabel("Date of Birth:"), gbc);
    gbc.gridx = 1;
    txtDob = new JTextField();
    infoPanel.add(txtDob, gbc);

    gbc.gridx = 2;
    infoPanel.add(new JLabel("Phone:"), gbc);
    gbc.gridx = 3;
    txtPhone = new JTextField();
    infoPanel.add(txtPhone, gbc);
    row++;

    // Dòng 3
    gbc.gridx = 0;
    gbc.gridy = row;
    infoPanel.add(new JLabel("Address:"), gbc);
    gbc.gridx = 1;
    gbc.gridwidth = 3;
    txtAddress = new JTextArea(2, 20);
    infoPanel.add(new JScrollPane(txtAddress), gbc);
    gbc.gridwidth = 1;
    row++;

    // Dòng 4
    gbc.gridx = 0;
    gbc.gridy = row;
    infoPanel.add(new JLabel("Status:"), gbc);
    gbc.gridx = 1;
    cbStatus = new JComboBox<>(new String[] {"Active", "Inactive"});
    infoPanel.add(cbStatus, gbc);

    gbc.gridx = 2;
    infoPanel.add(new JLabel("Student ID:"), gbc);
    gbc.gridx = 3;
    txtStudentId = new JTextField();
    infoPanel.add(txtStudentId, gbc);
    row++;

    // Dòng 5
    gbc.gridx = 0;
    gbc.gridy = row;
    infoPanel.add(new JLabel("Enrollment Year:"), gbc);
    gbc.gridx = 1;
    cbEnrollmentYear = new JComboBox<>();
    for (int year = 2015; year <= 2025; year++) cbEnrollmentYear.addItem(String.valueOf(year));
    infoPanel.add(cbEnrollmentYear, gbc);

    gbc.gridx = 2;
    infoPanel.add(new JLabel("Faculty:"), gbc);
    gbc.gridx = 3;
    cbFaculty = new JComboBox<>(new String[] {"IT", "Business", "Law", "Engineering"});
    infoPanel.add(cbFaculty, gbc);

    // === Bảng và nút ===
    tblBorrowedBooks =
        new JTable(
            new DefaultTableModel(
                new Object[][] {
                  {"B001", "Java Basics", "James Gosling", "2025-04-10", "2025-05-10"},
                  {"B002", "Clean Code", "Robert C. Martin", "2025-04-15", "2025-05-15"}
                },
                new String[] {"Book ID", "Title", "Author", "Borrow Date", "Due Date"}));

    tblHistory =
        new JTable(
            new DefaultTableModel(
                new Object[][] {
                  {"B010", "Spring Boot", "2025-02-01", "2025-03-01", "Returned"},
                  {"B011", "Effective Java", "2025-01-05", "2025-02-05", "Overdue"}
                },
                new String[] {"Book ID", "Title", "Borrow Date", "Return Date", "Status"}));

    // === Thanh nút ===
    JPanel topButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topButtonPanel.add(new JButton("🔍 Tìm kiếm"));
    topButtonPanel.add(new JButton("A"));
    topButtonPanel.add(new JButton("B"));
    topButtonPanel.add(new JButton("C"));

    // === Panel bảng ===
    JPanel tablePanel = new JPanel(new BorderLayout(5, 5));
    JPanel tableContent = new JPanel(new GridLayout(2, 1, 10, 10));
    tableContent.add(new JScrollPane(tblBorrowedBooks));
    tableContent.add(new JScrollPane(tblHistory));
    tablePanel.add(topButtonPanel, BorderLayout.NORTH);
    tablePanel.add(tableContent, BorderLayout.CENTER);

    // === Chia trái/phải ===
    JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, infoPanel, tablePanel);
    splitPane.setDividerLocation(420);

    add(splitPane, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Student Management");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(1000, 600);
    frame.setLocationRelativeTo(null);
    frame.setContentPane(new StudentPanel());
    frame.setVisible(true);
  }
}
