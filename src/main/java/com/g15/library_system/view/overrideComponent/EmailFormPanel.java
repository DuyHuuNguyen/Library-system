package com.g15.library_system.view.overrideComponent;

import com.g15.library_system.view.Style;
import java.awt.*;
import javax.swing.*;

public class EmailFormPanel extends JPanel {

  public EmailFormPanel() {
    setLayout(new BorderLayout(10, 10));

    JPanel formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel toLabel = new JLabel("To (email):");
    JTextField toField = new JTextField(25);

    JLabel subjectLabel = new JLabel("Subject:");
    JTextField subjectField = new JTextField(25);

    JLabel bodyLabel = new JLabel("Message:");
    JTextArea bodyArea = new JTextArea(30, 50);
    JScrollPane bodyScroll = new JScrollPane(bodyArea);

    JButton sendButton = new JButton("Send");

    gbc.gridx = 0;
    gbc.gridy = 0;
    formPanel.add(toLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(toField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 1;
    formPanel.add(subjectLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(subjectField, gbc);

    gbc.gridx = 0;
    gbc.gridy = 2;
    formPanel.add(bodyLabel, gbc);
    gbc.gridx = 1;
    formPanel.add(bodyScroll, gbc);

    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.anchor = GridBagConstraints.EAST;
    formPanel.add(sendButton, gbc);

    formPanel.setBackground(Style.DELETE_BUTTON_COLOR_RED);
    add(formPanel, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(
        () -> {
          JFrame frame = new JFrame("Send Mail");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.setSize(500, 400);
          frame.setLocationRelativeTo(null);
          frame.setContentPane(new EmailFormPanel());
          frame.setVisible(true);
        });
  }
}
