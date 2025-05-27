package com.g15.library_system.view.managementView.myAccount;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.overrideComponent.labels.CircularImageLabel;
import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class UserProfilePanel extends RoundedShadowPanel {
  private CircularImageLabel avatarLabel;
  private JLabel nameLabel, jobLabel, addressLabel;
  private JButton editButton, saveButton, uploadImageButton, cancelButton;
  private JPanel infoPanel, buttonPanel;
  private boolean isEditing = false;
  private String avatarPath;
  private Map<String, String> personalData;
  private Map<String, String> addressData;
  private JTextField nameField, jobField, addressField;
  private PersonalInfoPanel personalInfoPanel;
  private AddressPanel addressPanel;
  private Runnable saveCallback;

  public UserProfilePanel(
      String avatarPath,
      Map<String, String> personalData,
      Map<String, String> addressData,
      PersonalInfoPanel personalInfoPanel,
      AddressPanel addressPanel,
      Runnable saveCallback) { // Thêm tham số Runnable
    this.avatarPath = avatarPath;
    this.addressData = addressData;
    this.personalData = personalData;
    this.personalInfoPanel = personalInfoPanel;
    this.addressPanel = addressPanel;
    this.saveCallback = saveCallback;
    this.setLayout(new BorderLayout(20, 20));
    this.setBorder(BorderFactory.createEmptyBorder(20, 40, 30, 20));
    this.setBackground(Color.WHITE);

    avatarLabel = new CircularImageLabel(avatarPath, 100, 100);
    this.add(avatarLabel, BorderLayout.WEST);

    infoPanel = new JPanel(new GridBagLayout());
    infoPanel.setOpaque(false);
    this.add(infoPanel, BorderLayout.CENTER);

    JPanel topPn = new JPanel(new GridLayout(1, 2));
    topPn.setOpaque(false);
    JPanel myProfilePn = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    myProfilePn.setOpaque(false);
    JLabel titleLb = new JLabel("My Profile");
    titleLb.setFont(new Font("Arial", Font.BOLD, 16));
    titleLb.setForeground(new Color(30, 30, 60));
    myProfilePn.add(titleLb);
    topPn.add(myProfilePn);

    buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
    buttonPanel.setOpaque(false);
    topPn.add(buttonPanel);
    this.add(topPn, BorderLayout.NORTH);

    editButton = new JButton("Edit");
    editButton.addActionListener(e -> toggleEditMode());
    buttonPanel.add(editButton);

    buildViewMode();
  }

  private void buildViewMode() {
    infoPanel.removeAll();
    buttonPanel.removeAll();
    buttonPanel.add(editButton);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(0, 0, 5, 0);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 0;

    nameLabel = new JLabel(personalData.get("First Name") + " " + personalData.get("Last Name"));
    nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
    nameLabel.setForeground(new Color(30, 30, 60));
    jobLabel = new JLabel(personalData.get("Job"));
    addressLabel =
        new JLabel(
            addressData.get("Address")
                + ", "
                + addressData.get("City")
                + ", "
                + addressData.get("Country"));

    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    infoPanel.add(nameLabel, gbc);
    gbc.gridy++;
    infoPanel.add(jobLabel, gbc);
    gbc.gridy++;
    infoPanel.add(addressLabel, gbc);

    revalidate();
    repaint();
  }

  private void buildEditMode() {
    infoPanel.removeAll();
    buttonPanel.removeAll();

    uploadImageButton = new JButton("Upload Avatar");
    uploadImageButton.addActionListener(e -> uploadAvatar());

    saveButton = new JButton("Save");
    saveButton.setBackground(Style.GREEN_CONFIRM_BUTTON_COLOR);
    saveButton.setForeground(Color.WHITE);
    saveButton.addActionListener(e -> saveChanges());

    cancelButton = new JButton("Cancel");
    cancelButton.setBackground(new Color(255, 255, 255)); // Đỏ nhạt
    cancelButton.setForeground(Color.BLACK);
    cancelButton.addActionListener(e -> cancelEdit());

    buttonPanel.add(uploadImageButton);
    buttonPanel.add(cancelButton);
    buttonPanel.add(saveButton);

    String fullName = nameLabel.getText();
    String[] parts = fullName.split(" ", 2);
    String firstName = parts.length > 0 ? parts[0] : "";
    String lastName = parts.length > 1 ? parts[1] : "";

    nameField = new JTextField(firstName + " " + lastName);
    jobField = new JTextField(jobLabel.getText());
    addressField = new JTextField(addressLabel.getText());

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 0, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.fill = GridBagConstraints.HORIZONTAL;

    infoPanel.add(nameField, gbc);
    gbc.gridy++;
    infoPanel.add(jobField, gbc);
    gbc.gridy++;
    infoPanel.add(addressField, gbc);

    revalidate();
    repaint();
  }

  private void toggleEditMode() {
    isEditing = !isEditing;
    if (isEditing) {
      buildEditMode();
      editButton.setVisible(false);
    } else {
      buildViewMode();
      editButton.setVisible(true);
    }
  }

  private void cancelEdit() {
    isEditing = false;
    buildViewMode();
    editButton.setVisible(true);
  }

  private void saveChanges() {
    // Cập nhật thông tin cá nhân
    String[] names = nameField.getText().trim().split(" ", 2);
    String firstName = names.length > 0 ? names[0] : "";
    String lastName = names.length > 1 ? names[1] : "";

    personalData.put("First Name", firstName);
    personalData.put("Last Name", lastName);
    personalData.put("Job", jobField.getText().trim());

    // Cập nhật thông tin địa chỉ
    String[] addressParts = addressField.getText().trim().split(",", 3);
    String address = addressParts.length > 0 ? addressParts[0].trim() : "";
    String city = addressParts.length > 1 ? addressParts[1].trim() : "";
    String country = addressParts.length > 2 ? addressParts[2].trim() : "";

    addressData.put("Address", address);
    addressData.put("City", city);
    addressData.put("Country", country);

    // Cập nhật lại label hiển thị
    nameLabel.setText(personalData.get("First Name") + " " + personalData.get("Last Name"));
    jobLabel.setText(personalData.get("Job"));
    addressLabel.setText(
        addressData.get("Address")
            + ", "
            + addressData.get("City")
            + ", "
            + addressData.get("Country"));

    // Quay lại chế độ xem
    toggleEditMode();
    // Cập nhật lại các panel liên quan
    if (personalInfoPanel != null) {
      personalInfoPanel.refreshView();
    }
    if (addressPanel != null) {
      addressPanel.refreshView();
    }
    // Kích hoạt callback để MyAccountPanel lưu và làm mới toàn bộ
    if (saveCallback != null) {
      saveCallback.run();
    }
  }

  private void uploadAvatar() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      avatarLabel.setImage(imagePath);
    }
  }

  // Cập nhật phương thức này để nhận cả addressData
  public void updateProfileData(Map<String, String> personalData, Map<String, String> addressData) {
    this.personalData = personalData; // Cập nhật tham chiếu
    this.addressData = addressData; // Cập nhật tham chiếu
    nameLabel.setText(personalData.get("First Name") + " " + personalData.get("Last Name"));
    jobLabel.setText(personalData.get("Job"));
    addressLabel.setText(
        addressData.get("Address")
            + ", "
            + addressData.get("City")
            + ", "
            + addressData.get("Country"));
    revalidate();
    repaint();
  }
}
