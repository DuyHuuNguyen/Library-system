package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
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

    JPanel centerPanel = new JPanel(new BorderLayout(10, 10));

    String[] columns = new String[] {"", "haha", "hehe", "kshdf"};
    Object[][] data = {
      {"b1", "n2", "df"},
      {"b1", "n2", "df"},
      {"b1", "n2", "df"},
      {"b1", "n2", "df"}
    };

    RoundedShadowPanel roundedShadowPanel = new RoundedShadowPanel();
    this.checkboxTablePanel = new CheckboxTablePanel(columns, data);
    this.checkboxTablePanel.setPreferredSize(new Dimension(400, 650));
    roundedShadowPanel.add(this.checkboxTablePanel);

    RoundedShadowPanel roundedShadowPanelEmailForm = new RoundedShadowPanel();
    JPanel emailContentPanel = new EmailFormPanel();
    emailContentPanel.setPreferredSize(new Dimension(750, 650));
    emailContentPanel.setBackground(Style.LIGHT_WHITE_BACKGROUND);
    roundedShadowPanelEmailForm.add(emailContentPanel);

    centerPanel.add(roundedShadowPanel, BorderLayout.CENTER);
    centerPanel.add(roundedShadowPanelEmailForm, BorderLayout.EAST);

    add(centerPanel, BorderLayout.CENTER);
  }
}
