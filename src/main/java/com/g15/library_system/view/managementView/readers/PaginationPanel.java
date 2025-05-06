package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import javax.swing.*;

public class PaginationPanel extends JPanel {
  public PaginationPanel() {
    setLayout(new FlowLayout(FlowLayout.CENTER));

    JButton firstPageButton = createPaginationButton("«");
    JButton prevPageButton = createPaginationButton("‹");

    // Page buttons
    for (int i = 1; i <= 5; i++) {
      JButton pageButton;
      if (i == 1) {
        pageButton = createPaginationButton(String.valueOf(i), true);
      } else {
        pageButton = createPaginationButton(String.valueOf(i));
      }
      add(pageButton);
    }

    JButton nextPageButton = createPaginationButton("›");
    JButton lastPageButton = createPaginationButton("»");

    add(firstPageButton, 0);
    add(prevPageButton, 1);
    add(nextPageButton);
    add(lastPageButton);
  }

  private JButton createPaginationButton(String text) {
    return createPaginationButton(text, false);
  }

  private JButton createPaginationButton(String text, boolean active) {
    JButton button = new JButton(text);
    button.setFocusPainted(false);
    button.setFont(new Font("SansSerif", Font.BOLD, 12));
    button.setPreferredSize(new Dimension(30, 30));
    button.setCursor(new Cursor(Cursor.HAND_CURSOR));

    if (active) {
      button.setBackground(new Color(98, 52, 183));
      button.setForeground(Color.WHITE);
      button.setBorderPainted(false);
    } else {
      button.setBackground(Color.WHITE);
      button.setForeground(Color.DARK_GRAY);
      button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    return button;
  }
}
