package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Map;
import javax.swing.*;

public class FormPanel extends JPanel {
  private JTextField memberIdField;
  private JTextField nameField;
  private JTextField birthDateField;
  private JTextField phoneField;
  private JTextField addressField;
  private JTextField emailField;
  private JTextField membershipDateField;
  private JTextField readerTypeField;
  private JTextField totalFineField;

  public FormPanel() {
    setLayout(new GridBagLayout());
    setBackground(Color.WHITE);

    GridBagConstraints labelConstraints = new GridBagConstraints();
    labelConstraints.gridx = 0;
    labelConstraints.anchor = GridBagConstraints.WEST;
    labelConstraints.fill = GridBagConstraints.NONE;
    labelConstraints.insets = new Insets(5, 50, 5, 10);
    labelConstraints.weightx = 0.2;

    GridBagConstraints fieldConstraints = new GridBagConstraints();
    fieldConstraints.gridx = 1;
    fieldConstraints.fill = GridBagConstraints.HORIZONTAL;
    fieldConstraints.insets = new Insets(5, 10, 5, 50);
    fieldConstraints.weightx = 0.8;

    // Common styles for labels
    Font labelFont = new Font("Arial", Font.BOLD, 12);
    Color labelColor = new Color(0, 51, 102);

    // Member ID
    JLabel memberIdLabel = new JLabel("Member ID");
    memberIdLabel.setFont(labelFont);
    memberIdLabel.setForeground(labelColor);
    labelConstraints.gridy = 0;
    add(memberIdLabel, labelConstraints);

    memberIdField = createTF();
    fieldConstraints.gridy = 0;
    add(memberIdField, fieldConstraints);

    // Name
    JLabel nameLabel = new JLabel("Name");
    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);
    labelConstraints.gridy = 1;
    add(nameLabel, labelConstraints);

    nameField = createTF();
    fieldConstraints.gridy = 1;
    add(nameField, fieldConstraints);

    // BirthDate
    JLabel birthDateLabel = new JLabel("Birth Date");
    birthDateLabel.setFont(labelFont);
    birthDateLabel.setForeground(labelColor);
    labelConstraints.gridy = 2;
    add(birthDateLabel, labelConstraints);

    birthDateField = createTF();
    fieldConstraints.gridy = 2;
    add(birthDateField, fieldConstraints);

    // Phone
    JLabel phoneLabel = new JLabel("Phone");
    phoneLabel.setFont(labelFont);
    phoneLabel.setForeground(labelColor);
    labelConstraints.gridy = 3;
    add(phoneLabel, labelConstraints);

    phoneField = createTF();
    fieldConstraints.gridy = 3;
    add(phoneField, fieldConstraints);

    // Email
    JLabel emailLabel = new JLabel("Email");
    emailLabel.setFont(labelFont);
    emailLabel.setForeground(labelColor);
    labelConstraints.gridy = 4;
    add(emailLabel, labelConstraints);

    emailField = createTF();
    fieldConstraints.gridy = 4;
    add(emailField, fieldConstraints);

    // Address
    JLabel addressLabel = new JLabel("Address");
    addressLabel.setFont(labelFont);
    addressLabel.setForeground(labelColor);
    labelConstraints.gridy = 5;
    add(addressLabel, labelConstraints);

    addressField = createTF();
    fieldConstraints.gridy = 5;
    add(addressField, fieldConstraints);

    // Membership date
    JLabel membershipDateLabel = new JLabel("Membership date");
    membershipDateLabel.setFont(labelFont);
    membershipDateLabel.setForeground(labelColor);
    labelConstraints.gridy = 6;
    add(membershipDateLabel, labelConstraints);

    membershipDateField = createTF();
    // Set current date as default
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //    membershipDateField.setText(dateFormat.format(new Date()));
    fieldConstraints.gridy = 6;
    add(membershipDateField, fieldConstraints);

    // Reader Type
    JLabel readerTypeLabel = new JLabel("Reader Type");
    readerTypeLabel.setFont(labelFont);
    readerTypeLabel.setForeground(labelColor);
    labelConstraints.gridy = 7;
    add(readerTypeLabel, labelConstraints);

    readerTypeField = createTF();
    fieldConstraints.gridy = 7;
    add(readerTypeField, fieldConstraints);

    // Total Fine
    JLabel totalFineLabel = new JLabel("Total Fine");
    totalFineLabel.setFont(labelFont);
    totalFineLabel.setForeground(labelColor);
    labelConstraints.gridy = 8;
    add(totalFineLabel, labelConstraints);

    totalFineField = createTF();
    fieldConstraints.gridy = 8;
    add(totalFineField, fieldConstraints);

    //    memberIdField.setText("U001");
    //    nameField.setText("John Doe");
    //    birthDateField.setText("1/1/2000");
    //    phoneField.setText("0123456");
    //    emailField.setText("john.doe@example.com");
    //    addressField.setText("Linh Trung, Thu Duc, Tp.HCM");
    //    readerTypeField.setText("Student");

    // Reset date to current
    //    membershipDateField.setText("1/1/2025");
    //    totalFineField.setText(5 + "");
  }

  public void setMemberIdField(JTextField memberIdField) {
    this.memberIdField = memberIdField;
  }

  public void setNameField(JTextField nameField) {
    this.nameField = nameField;
  }

  public void setPhoneField(JTextField phoneField) {
    this.phoneField = phoneField;
  }

  public void setAddressField(JTextField addressField) {
    this.addressField = addressField;
  }

  public void setEmailField(JTextField emailField) {
    this.emailField = emailField;
  }

  public void setMembershipDateField(JTextField membershipDateField) {
    this.membershipDateField = membershipDateField;
  }

  // Method to reset all fields
  public void resetFields() {
    memberIdField.setText("");
    nameField.setText("");
    birthDateField.setText("");
    phoneField.setText("");
    emailField.setText("");
    addressField.setText("");
    readerTypeField.setText("");

    // Reset date to current
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    membershipDateField.setText("");
    totalFineField.setText(5 + "");

    //            "U001",
    //            "John Doe",
    //            "1/1/2000",
    //            "0123456",
    //            "john.doe@example.com",
    //            "Linh Trung, Thu Duc, Tp.HCM",
    //            "Student",
    //            5
  }

  public JTextField createTF() {
    return TextFieldBuilder.builder()
        .font(Style.FONT_PLAIN_13)
        .preferredSize(new Dimension(100, 30))
        .withFocusBorderEffect(Style.PURPLE_MAIN_THEME);
  }

  /** Cập nhật thông tin hiển thị dựa trên dữ liệu từ dòng được chọn */
  public void updateInfo(Map<String, Object> rowData) {
    if (rowData != null) {
      memberIdField.setEditable(false);
      nameField.setEditable(false);
      birthDateField.setEditable(false);
      phoneField.setEditable(false);
      emailField.setEditable(false);
      addressField.setEditable(false);
      membershipDateField.setEditable(false);
      readerTypeField.setEditable(false);
      totalFineField.setEditable(false);

      memberIdField.setText(rowData.get("ID") + "");
      nameField.setText(rowData.get("Name") + "");
      birthDateField.setText(String.valueOf(rowData.getOrDefault("Birth Date", "")));
      phoneField.setText(String.valueOf(rowData.getOrDefault("Phone", "")));
      emailField.setText(String.valueOf(rowData.getOrDefault("Email", "")));
      addressField.setText(String.valueOf(rowData.getOrDefault("Address", "")));
      membershipDateField.setText(String.valueOf(rowData.getOrDefault("Membership date", "")));
      readerTypeField.setText(String.valueOf(rowData.getOrDefault("Reader Type", "")));
      totalFineField.setText(String.valueOf(rowData.getOrDefault("Total Fine", "")));
    }
  }
}
