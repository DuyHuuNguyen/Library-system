package com.g15.library_system.view.overrideComponent;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import javax.swing.*;

public class EmailFormPanel extends JPanel {
  private JTextField toField;
  private JTextField subjectField;
  private JTextArea bodyArea;
  private JScrollPane bodyScroll;
  private JButton sendButton;

  private JPanel formPanel;

  public EmailFormPanel() {
    setLayout(new BorderLayout(10, 10));

    this.formPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(10, 10, 10, 10);
    gbc.anchor = GridBagConstraints.WEST;

    JLabel toLabel = new JLabel("To (email):");
    this.toField = new JTextField(25);

    JLabel subjectLabel = new JLabel("Subject:");
    this.subjectField = new JTextField(25);

    JLabel bodyLabel = new JLabel("Message:");
    this.bodyArea = new JTextArea(30, 50);
    this.bodyScroll = new JScrollPane(bodyArea);

    this.sendButton =
        CustomButtonBuilder.builder()
            .text("Send")
            .backgroundColor(Style.BLUE_HEADER_TABLE_AND_BUTTON)
            .preferredSize(new Dimension(120, 35));

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

    formPanel.setBackground(Color.WHITE);
    formPanel.setBorder(BorderFactory.createLineBorder(Style.BLUE_HEADER_TABLE_AND_BUTTON));
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
