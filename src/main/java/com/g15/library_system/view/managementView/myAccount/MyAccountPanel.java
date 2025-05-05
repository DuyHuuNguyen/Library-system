package com.g15.library_system.view.managementView.myAccount;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MyAccountPanel extends JPanel {
  private CircleImagePanel imagePanel;
  private JButton btnSelectImage;

  private JTextField txtFullName;
  private JTextField txtEmail;
  private JTextField txtPhone;
  private JTextField txtBirthday;

  private JButton btnUpdate;
  private JButton btnCancel;

  public MyAccountPanel() {
    setLayout(new FlowLayout(FlowLayout.LEFT, 15, 20));
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    BufferedImage defaultImage = null;
    try {
      defaultImage = ImageIO.read(new File("src/main/resources/images/fit_nlu_logo.jpg"));
    } catch (Exception e) {
      System.err.println("Không tìm thấy ảnh mặc định.");
    }

    imagePanel = new CircleImagePanel(defaultImage);
    btnSelectImage = new JButton("Chọn ảnh");
    btnSelectImage.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnSelectImage.addActionListener(this::chooseImage);

    JPanel leftPanel = new JPanel();
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
    leftPanel.add(Box.createVerticalGlue());
    leftPanel.add(imagePanel);
    leftPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    leftPanel.add(btnSelectImage);
    leftPanel.add(Box.createVerticalGlue());

    JPanel infoPanel = new JPanel(new GridBagLayout());
    infoPanel.setBorder(BorderFactory.createTitledBorder("Thông tin cá nhân"));
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 8, 5, 8);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    txtFullName = createField("Họ tên:", infoPanel, gbc, 0);
    txtEmail = createField("Email:", infoPanel, gbc, 1);
    txtPhone = createField("Số điện thoại:", infoPanel, gbc, 2);
    txtBirthday = createField("Ngày sinh:", infoPanel, gbc, 3);

    txtFullName.setText("Nguyễn Hữu Duy");
    txtEmail.setText("duy@example.com");
    txtPhone.setText("0123456789");
    txtBirthday.setText("01/01/2005");

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    btnUpdate = new JButton("Cập nhật");
    btnCancel = new JButton("Hủy");

    btnUpdate.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã cập nhật!"));
    btnCancel.addActionListener(e -> JOptionPane.showMessageDialog(this, "Hủy thay đổi."));

    buttonPanel.add(btnCancel);
    buttonPanel.add(btnUpdate);

    JPanel rightPanel = new JPanel(new BorderLayout(10, 10));
    rightPanel.add(infoPanel, BorderLayout.CENTER);
    rightPanel.add(buttonPanel, BorderLayout.SOUTH);

    add(leftPanel);
    add(rightPanel);
  }

  private JTextField createField(String label, JPanel container, GridBagConstraints gbc, int row) {
    JLabel jLabel = new JLabel(label);
    JTextField textField = new JTextField(15); // nhỏ gọn
    textField.setEditable(false);

    gbc.gridx = 0;
    gbc.gridy = row;
    container.add(jLabel, gbc);

    gbc.gridx = 1;
    container.add(textField, gbc);

    return textField;
  }

  private void chooseImage(ActionEvent e) {
    JFileChooser fileChooser = new JFileChooser();
    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      try {
        File file = fileChooser.getSelectedFile();
        BufferedImage newImg = ImageIO.read(file);
        imagePanel.setImage(newImg);
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Không thể đọc ảnh!");
      }
    }
  }
}
