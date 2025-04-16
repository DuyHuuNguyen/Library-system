package com.g15.library_system.view;

import com.g15.library_system.request.LoginRequest;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import lombok.SneakyThrows;

public class LoginFrame extends JFrame {

  public LoginFrame() {
    setTitle("Login System");
    setSize(400, 300);
    setLocationRelativeTo(null); // Center frame
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLayout(null);

    JLabel lblTitle = new JLabel("LOGIN SYSTEM");
    lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
    lblTitle.setBounds(120, 20, 200, 30);
    add(lblTitle);

    JLabel lblUsername = new JLabel("Username:");
    lblUsername.setBounds(50, 80, 100, 30);
    add(lblUsername);

    JTextField txtUsername = new JTextField();
    txtUsername.setBounds(150, 80, 200, 30);
    add(txtUsername);

    JLabel lblPassword = new JLabel("Password:");
    lblPassword.setBounds(50, 130, 100, 30);
    add(lblPassword);

    JPasswordField txtPassword = new JPasswordField();
    txtPassword.setBounds(150, 130, 200, 30);
    add(txtPassword);

    JButton btnLogin = new JButton("Login");
    btnLogin.setBounds(150, 190, 100, 40);
    btnLogin.setBackground(new Color(46, 204, 113));
    btnLogin.setForeground(Color.WHITE);
    btnLogin.setFocusPainted(false);
    add(btnLogin);

    JButton btnPrint = new JButton("Get Profile");
    btnPrint.setBounds(270, 190, 100, 40); // Set vị trí và kích thước
    btnPrint.setBackground(new Color(52, 152, 219)); // Màu xanh dương
    btnPrint.setForeground(Color.WHITE);
    btnPrint.setFocusPainted(false);
    add(btnPrint);

    btnPrint.addActionListener(
        e -> {
          //          try {
          ////            JOptionPane.showMessageDialog(
          ////                null, userAdapter.getProfile(TokenStore.getAccessToken()).toString());
          //          } catch (IOException ex) {
          //            throw new RuntimeException(ex);
          //          }
        });

    btnLogin.addActionListener(
        new ActionListener() {
          @SneakyThrows
          @Override
          public void actionPerformed(ActionEvent e) {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            var request = LoginRequest.builder().email(username).password(password).build();
            //            TokenStore.saveToken(userAdapter.login(request));
          }
        });
  }
}
