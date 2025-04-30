package com.g15.library_system.view.managementView.returnBooks;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class DockerStyleCombo extends JFrame {
  private final JButton mainButton;
  private final Map<String, Runnable> actionMap = new HashMap<>();

  public DockerStyleCombo() {
    setTitle("Docker Style Combo");
    setSize(300, 200);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    mainButton = new JButton("Run");
    mainButton.setBackground(new Color(33, 111, 255));
    mainButton.setForeground(Color.WHITE);
    mainButton.setFocusPainted(false);

    JButton dropdownButton = new JButton("▼");
    dropdownButton.setBackground(new Color(33, 111, 255));
    dropdownButton.setForeground(Color.WHITE);
    dropdownButton.setFocusPainted(false);

    JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    panel.add(mainButton);
    panel.add(dropdownButton);

    JPopupMenu menu = new JPopupMenu();

    String[] items = {"Edit", "Export", "Import", "Refresh"};
    Font menuFont = new Font("Segoe UI", Font.PLAIN, 14);
    int popupWidth = mainButton.getPreferredSize().width + dropdownButton.getPreferredSize().width;
    int popupHeight = 35;

    // Định nghĩa các hành động tương ứng
    actionMap.put("Edit", () -> JOptionPane.showMessageDialog(this, "Editing..."));
    actionMap.put("Export", () -> JOptionPane.showMessageDialog(this, "Exporting..."));
    actionMap.put("Import", () -> JOptionPane.showMessageDialog(this, "Importing..."));
    actionMap.put("Refresh", () -> JOptionPane.showMessageDialog(this, "Refreshing..."));

    for (String itemText : items) {
      JMenuItem item = new JMenuItem(itemText);
      item.setFont(menuFont);
      item.setPreferredSize(new Dimension(popupWidth, popupHeight));

      item.addActionListener(
          e -> {
            mainButton.setText(itemText);
            // Xóa tất cả action cũ
            for (ActionListener al : mainButton.getActionListeners()) {
              mainButton.removeActionListener(al);
            }
            // Gán hành động mới tương ứng với lựa chọn
            mainButton.addActionListener(
                ev -> {
                  Runnable action = actionMap.get(itemText);
                  if (action != null) action.run();
                });
          });

      menu.add(item);
    }

    dropdownButton.addActionListener(
        e -> {
          menu.show(panel, 0, panel.getHeight());
        });

    getContentPane().add(panel, BorderLayout.NORTH);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> new DockerStyleCombo().setVisible(true));
  }
}
