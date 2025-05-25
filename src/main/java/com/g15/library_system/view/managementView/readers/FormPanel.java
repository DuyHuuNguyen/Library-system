package com.g15.library_system.view.managementView.readers;

import com.g15.library_system.verifier.EmailVerifier;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.dateChoosers.DateChooser;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAction;
import com.g15.library_system.view.overrideComponent.dateChoosers.listener.DateChooserAdapter;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import com.g15.library_system.view.swingComponentBuilders.TextFieldBuilder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import javax.swing.*;
import lombok.Getter;

@Getter
public class FormPanel extends JPanel {
  private JLabel memberIdLabel;
  private JTextField memberIdField;
  private JTextField fullNameField;
  //  private JTextField lastNameField;
  //  private JTextField firstNameField;
  private JTextField birthDateField;
  private JTextField phoneField;
  private JTextField addressField;
  private JTextField emailField;
  private JTextField membershipDateField;
  private JTextField readerTypeField;
  private JComboBox<String> readerTypeJcb;
  private JTextField totalFineField;
  private DateChooser dateOfBirthChooser;
  private DateChooser memberShipDateChooser;

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
    memberIdLabel = new JLabel("Member ID");
    memberIdLabel.setVisible(false);
    memberIdLabel.setFont(labelFont);
    memberIdLabel.setForeground(labelColor);
    labelConstraints.gridy = 0;
    add(memberIdLabel, labelConstraints);

    memberIdField = createTF();
    memberIdField.setVisible(false);
    fieldConstraints.gridy = 0;
    add(memberIdField, fieldConstraints);

    // Full Name
    JLabel nameLabel = new JLabel("Full Name");
    nameLabel.setFont(labelFont);
    nameLabel.setForeground(labelColor);
    labelConstraints.gridy = 1;
    add(nameLabel, labelConstraints);

    fullNameField = createTF();
    fieldConstraints.gridy = 1;
    add(fullNameField, fieldConstraints);

    //    // Last Name
    //    JLabel lastNameLabel = new JLabel("Last Name");
    //    lastNameLabel.setFont(labelFont);
    //    lastNameLabel.setForeground(labelColor);
    //    labelConstraints.gridy = 1;
    //    add(lastNameLabel, labelConstraints);
    //
    //    lastNameField = createTF();
    //    fieldConstraints.gridy = 1;
    //    add(lastNameField, fieldConstraints);
    //
    //    // First Name
    //    JLabel firstNameLabel = new JLabel("First Name");
    //    firstNameLabel.setFont(labelFont);
    //    firstNameLabel.setForeground(labelColor);
    //    labelConstraints.gridy = 2;
    //    add(firstNameLabel, labelConstraints);
    //
    //    firstNameField = createTF();
    //    fieldConstraints.gridy = 2;
    //    add(firstNameField, fieldConstraints);

    // BirthDate
    JLabel birthDateLabel = new JLabel("Date of birth");
    birthDateLabel.setFont(labelFont);
    birthDateLabel.setForeground(labelColor);
    labelConstraints.gridy = 3;
    add(birthDateLabel, labelConstraints);

    birthDateField = createTF();
    dateOfBirthChooser = new DateChooser();
    dateOfBirthChooser.setTextField(birthDateField);
    dateOfBirthChooser.addActionDateChooserListener(
        new DateChooserAdapter() {
          @Override
          public void dateChanged(java.util.Date date, DateChooserAction action) {
            super.dateChanged(date, action);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 7);
            //                dueDateChooser.setSelectedDate(calendar.getTime());
          }
        });
    fieldConstraints.gridy = 3;

    add(birthDateField, fieldConstraints);

    // Phone
    JLabel phoneLabel = new JLabel("Phone");
    phoneLabel.setFont(labelFont);
    phoneLabel.setForeground(labelColor);
    labelConstraints.gridy = 4;
    add(phoneLabel, labelConstraints);

    phoneField = createTF();
    fieldConstraints.gridy = 4;
    add(phoneField, fieldConstraints);

    // Email
    JLabel emailLabel = new JLabel("Email");
    emailLabel.setFont(labelFont);
    emailLabel.setForeground(labelColor);
    labelConstraints.gridy = 5;
    add(emailLabel, labelConstraints);

    emailField = createTF();
    fieldConstraints.gridy = 5;
    add(emailField, fieldConstraints);

    // Address
    JLabel addressLabel = new JLabel("Address");
    addressLabel.setFont(labelFont);
    addressLabel.setForeground(labelColor);
    labelConstraints.gridy = 6;
    add(addressLabel, labelConstraints);

    addressField = createTF();
    fieldConstraints.gridy = 6;
    add(addressField, fieldConstraints);

    // Membership date
    JLabel membershipDateLabel = new JLabel("Membership date");
    membershipDateLabel.setFont(labelFont);
    membershipDateLabel.setForeground(labelColor);
    labelConstraints.gridy = 7;
    add(membershipDateLabel, labelConstraints);

    membershipDateField = createTF();
    membershipDateField.setEnabled(false);
    memberShipDateChooser = new DateChooser();
    memberShipDateChooser.setTextField(membershipDateField);
    memberShipDateChooser.addActionDateChooserListener(
        new DateChooserAdapter() {
          @Override
          public void dateChanged(java.util.Date date, DateChooserAction action) {
            super.dateChanged(date, action);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, 7);

            //                dueDateChooser.setSelectedDate(calendar.getTime());
          }
        });
    // Set current date as default
    //    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    //    membershipDateField.setText(dateFormat.format(new Date()));
    fieldConstraints.gridy = 7;
    add(membershipDateField, fieldConstraints);

    // Reader Type
    JLabel readerTypeLabel = new JLabel("Reader Type");
    readerTypeLabel.setFont(labelFont);
    readerTypeLabel.setForeground(labelColor);
    labelConstraints.gridy = 8;
    add(readerTypeLabel, labelConstraints);

    readerTypeField = createTF();
    readerTypeField.setVisible(false);
    fieldConstraints.gridy = 8;
    add(readerTypeField, fieldConstraints);

    String[] readerTypeList = {"Student", "Teacher", "Other"};
    readerTypeJcb = new JComboBox<>(readerTypeList);
    fieldConstraints.gridy = 8;
    add(readerTypeJcb, fieldConstraints);

    readerTypeJcb.addActionListener(
        e -> {
          String selected = (String) readerTypeJcb.getSelectedItem();
          readerTypeField.setText(selected);
        });

    // Total Fine
    JLabel totalFineLabel = new JLabel("Total Fine");
    totalFineLabel.setFont(labelFont);
    totalFineLabel.setForeground(labelColor);
    labelConstraints.gridy = 9;
    add(totalFineLabel, labelConstraints);

    totalFineField = createTF();
    totalFineField.setEditable(false);
    fieldConstraints.gridy = 9;
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

  //  public void setLastNameField(JTextField lastName) {
  //    this.lastNameField = lastName;
  //  }
  //
  //  public void setFistNameField(JTextField firstName) {
  //    this.firstNameField = firstName;
  //  }

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
    fullNameField.setText("");
    //    lastNameField.setText("");
    //    firstNameField.setText("");
    birthDateField.setText("");
    phoneField.setText("");
    emailField.setText("");
    addressField.setText("");
    //    readerTypeField.setText("");

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
      memberIdLabel.setVisible(true);
      memberIdField.setVisible(true);
      memberIdField.setEditable(false);
      fullNameField.setEditable(false);
      //      lastNameField.setEditable(false);
      //      firstNameField.setEditable(false);
      birthDateField.setEnabled(false);
      phoneField.setEditable(false);
      emailField.setEditable(false);
      addressField.setEditable(false);
      membershipDateField.setEnabled(false);
      readerTypeJcb.setVisible(false);
      readerTypeField.setVisible(true);
      readerTypeField.setEditable(false);
      totalFineField.setEditable(false);

      memberIdField.setText(rowData.get("ID") + "");
      fullNameField.setText(rowData.get("Name") + "");
      //      lastNameField.setText(rowData.get("Last Name") + "");
      //      firstNameField.setText(rowData.get("First Name") + "");
      birthDateField.setText(String.valueOf(rowData.getOrDefault("Birth Date", "")));
      phoneField.setText(String.valueOf(rowData.getOrDefault("Phone", "")));
      emailField.setText(String.valueOf(rowData.getOrDefault("Email", "")));
      addressField.setText(String.valueOf(rowData.getOrDefault("Address", "")));
      membershipDateField.setText(String.valueOf(rowData.getOrDefault("Membership date", "")));
      readerTypeField.setText(String.valueOf(rowData.getOrDefault("Reader Type", "")));
      totalFineField.setText(String.valueOf(rowData.getOrDefault("Total Fine", "")));
    }
  }

  public void isValidate() {
    //    if (memberIdField.getText().trim().isEmpty()) {
    //      membershipDateField.requestFocusInWindow();
    //      throw new IllegalArgumentException("MemberID is empty!");
    //    }
    //    if (lastNameField.getText().trim().isEmpty()) {
    //      lastNameField.requestFocusInWindow();
    //      throw new IllegalArgumentException("Last Name is empty!");
    //    }
    //    if (firstNameField.getText().trim().isEmpty()) {
    //      firstNameField.requestFocusInWindow();
    //      throw new IllegalArgumentException("First Name is empty!");
    //    }

    if (fullNameField.getText().trim().isEmpty()) {
      fullNameField.requestFocusInWindow();
      throw new IllegalArgumentException("Full Name is empty!");
    }
    if (birthDateField.getText().isEmpty()) {
      birthDateField.requestFocusInWindow();
      throw new IllegalArgumentException("Date of birth is empty!");
    }
    if (phoneField.getText().trim().isEmpty()) {
      phoneField.requestFocusInWindow();
      throw new IllegalArgumentException("Invalid numberPhone!");
    }
    if (!new EmailVerifier().verify(emailField)) {
      emailField.requestFocusInWindow();
      throw new IllegalArgumentException("Invalid email!");
    }
    if (addressField.getText().trim().isEmpty()) {
      addressField.requestFocusInWindow();
      throw new IllegalArgumentException("Address is empty!");
    }
    if (readerTypeField.getText().isEmpty()) {
      readerTypeField.requestFocusInWindow();
      throw new IllegalArgumentException("readerType is empty!");
    }
  }

  public boolean validateForm() {
    try {
      this.isValidate();
      return true;
    } catch (IllegalArgumentException e) {
      new ToastNotification(
              JOptionPane.getFrameForComponent(this),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.TOP_CENTER,
              e.getMessage())
          .showNotification();
      return false;
    }
  }
}
