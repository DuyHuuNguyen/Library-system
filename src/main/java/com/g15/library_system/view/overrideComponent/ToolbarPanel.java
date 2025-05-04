package com.g15.library_system.view.overrideComponent;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ToolbarPanel extends JPanel {
  private JPanel searchPanel;

  public ToolbarPanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Search field with icon
    searchPanel = new JPanel(new BorderLayout());
    searchPanel.setBackground(Color.WHITE);
    searchPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    JTextField searchField = new JTextField(" Search books");
    searchField.setBorder(null);

    searchPanel.add(new JLabel("🔍"), BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.setPreferredSize(new Dimension(250, 35));

    // Right side buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setBackground(Color.WHITE);
    Color purple = new Color(103, 80, 164);
    JButton addButton =
        CustomButtonBuilder.builder()
            .text("+ Add")
            .font(Style.FONT_PLAIN_13)
            .textColor(purple)
            .backgroundColor(purple)
            .hoverColor(Color.cyan)
            .radius(10)
            .alignment(SwingConstants.CENTER)
            .preferredSize(new Dimension(100, 100))
            .drawBorder(false)
            .opaque(true)
            .contentAreaFilled(true);

    JButton actionButton = new JButton("Actions ⌄");

    // Style buttons (purple)

    //        addButton.setForeground(Color.WHITE);
    addButton.setFocusPainted(false);

    actionButton.setBackground(purple);
    actionButton.setForeground(Color.WHITE);
    actionButton.setFocusPainted(false);

    // Toggle search field visibility
    actionButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            searchPanel.setVisible(!searchPanel.isVisible());
            revalidate();
            repaint();
          }
        });

    buttonPanel.add(addButton);
    buttonPanel.add(actionButton);

    // Add to main panel
    add(searchPanel, BorderLayout.WEST);
    add(buttonPanel, BorderLayout.EAST);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Top Panel UI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 100);
    frame.add(new ToolbarPanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
