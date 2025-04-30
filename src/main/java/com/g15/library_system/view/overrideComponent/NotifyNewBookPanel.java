package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;

public class NotifyNewBookPanel extends JPanel {
  private JPanel centerPanel;
  private TablePanel tablePanel;
  private EmailFormPanel emailFormPanel;

  public NotifyNewBookPanel() {
    this.initPanel();
  }

  private void initPanel() {
    setLayout(new BorderLayout());

    JPanel centerPanel = new JPanel(new BorderLayout());

    String[] columns = new String[] {"haha", "hehe"};
    Object[][] data = {
      {"b1", "n2"},
      {"b1", "n2"},
      {"b1", "n2"}
    };
    TablePanel leftPanel = new TablePanel(columns, data, 10, 10);
    leftPanel.setBackground(Color.PINK);
    leftPanel.setPreferredSize(new Dimension(400, 0));

    JPanel emailContentPanel = new EmailFormPanel();
    emailContentPanel.setBackground(Color.GREEN);

    centerPanel.add(leftPanel, BorderLayout.CENTER);
    centerPanel.add(emailContentPanel, BorderLayout.EAST);

    add(centerPanel, BorderLayout.CENTER);
  }
}
