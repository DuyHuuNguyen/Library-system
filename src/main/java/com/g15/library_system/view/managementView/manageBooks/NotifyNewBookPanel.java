package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;
import java.awt.*;
import javax.swing.*;

public class NotifyNewBookPanel extends JPanel {
  private JPanel centerPanel;
  private CheckboxTablePanel checkboxTablePanel;
  private EmailFormPanel emailFormPanel;

  public NotifyNewBookPanel() {
    this.initPanel();
  }

  private void initPanel() {
    setLayout(new BorderLayout());

    JPanel centerPanel = new JPanel(new BorderLayout());

    String[] columns = new String[] {"", "haha", "hehe", "kshdf"};
    Object[][] data = {
      {false, "b1", "n2", "df"},
      {false, "b1", "n2", "df"},
      {false, "b1", "n2", "df"},
      {false, "b1", "n2", "df"}
    };
    CheckboxTablePanel leftPanel = new CheckboxTablePanel(columns, data);
    leftPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    leftPanel.setPreferredSize(new Dimension(400, 0));

    JPanel emailContentPanel = new EmailFormPanel();
    emailContentPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    centerPanel.add(new CheckboxTablePanel(columns, data), BorderLayout.CENTER);
    centerPanel.add(emailContentPanel, BorderLayout.EAST);

    add(centerPanel, BorderLayout.CENTER);
  }
}
