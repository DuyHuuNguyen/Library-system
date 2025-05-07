package com.g15.library_system.view.managementView.myAccount;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CircularImage;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import java.awt.*;
import java.util.Map;
import javax.swing.*;

public class UserProfilePanel extends RoundedPanel {
  private CircularImage avatarLabel;
  private JLabel nameLabel, jobLabel, addressLabel;
  private JButton editButton, saveButton, uploadImageButton;
  private JPanel infoPanel, buttonPanel;
  private boolean isEditing = false;
  private String avatarPath;
  private Map<String, String> personalData;
  private Map<String, String> addressData;

  public UserProfilePanel(
      String avatarPath, Map<String, String> personalData, Map<String, String> addressData) {
    super(20, Color.WHITE, null);
    this.avatarPath = avatarPath;
    this.addressData = addressData;
    this.personalData = personalData;
    this.setLayout(new BorderLayout(20, 20));
    this.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 20));
    this.setBackground(Color.WHITE);

    avatarLabel = new CircularImage(avatarPath, 100, 100, false);
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

    saveButton = new JButton("✔ Save");
    saveButton.setBackground(Style.GREEN_CONFIRM_BUTTON_COLOR);
    saveButton.setForeground(Color.WHITE);
    saveButton.addActionListener(e -> saveChanges());

    buttonPanel.add(uploadImageButton);
    buttonPanel.add(saveButton);

    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 0, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;
    gbc.gridx = 1;
    gbc.gridy = 0;

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

  private void saveChanges() {
    toggleEditMode();
  }

  private void uploadAvatar() {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      String imagePath = fileChooser.getSelectedFile().getAbsolutePath();
      avatarLabel.setImage(imagePath);
    }
  }

  public void updateProfileData(Map<String, String> personalData) {
    // TODO
    nameLabel.setText(personalData.get("First Name") + " " + personalData.get("Last Name"));
    jobLabel.setText(personalData.get("Job"));
    addressLabel.setText(
        addressData.get("Address")
            + ", "
            + addressData.get("City")
            + ", "
            + addressData.get("Country"));
  }
}
