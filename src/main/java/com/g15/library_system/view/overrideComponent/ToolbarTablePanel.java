package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;

@Deprecated
public class ToolbarTablePanel extends JPanel {
  private JPanel searchPanel;
  private JTextField searchField;
  private JButton addBt;
  private JButton removeBt;
  private JButton reloadBt;
  private JCheckBox checkBox;

  public ToolbarTablePanel() {
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Search field with icon
    searchPanel = new JPanel(new BorderLayout());
    searchPanel.setBackground(Color.WHITE);
    searchPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

    searchField = new JTextField(" Search books");
    searchField.setBorder(null);

    searchPanel.add(new JLabel("🔍"), BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);
    searchPanel.setPreferredSize(new Dimension(250, 35));

    // Right side buttons
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setBackground(Color.WHITE);

    addBt = new JButton("+ Add Books");

    // Style button (purple)
    Color purple = new Color(103, 80, 164);
    addBt.setBackground(purple);
    addBt.setForeground(Color.BLACK);
    addBt.setFocusPainted(false);

    removeBt = new JButton("Delete book");
    removeBt.setBackground(purple);
    removeBt.setForeground(Color.BLACK);
    removeBt.setFocusPainted(false);

    //    removeBt = ButtonGenerator.createCustomButton();

    String[] actions = {"Show/Hide Search", "Other Action"};
    JComboBox<String> actionComboBox = new JComboBox<>(actions);
    actionComboBox.setBackground(purple);
    actionComboBox.setForeground(Color.BLACK);
    actionComboBox.setFocusable(false);

    actionComboBox.addActionListener(
        e -> {
          String selected = (String) actionComboBox.getSelectedItem();
          if ("Show/Hide Search".equals(selected)) {
            searchPanel.setVisible(!searchPanel.isVisible());
            revalidate();
            repaint();
          }
        });

    buttonPanel.add(addBt);
    buttonPanel.add(removeBt);
    buttonPanel.add(actionComboBox);

    add(searchPanel, BorderLayout.WEST);
    add(buttonPanel, BorderLayout.EAST);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Top Panel UI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 100);
    frame.add(new ToolbarTablePanel());
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
