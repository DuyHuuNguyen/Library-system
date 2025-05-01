package com.g15.library_system.view.managementView.returnBooks;

import com.g15.library_system.view.overrideComponent.tables.CheckboxTablePanel;

import java.awt.*;
import javax.swing.*;

public class ContentPanel extends JPanel {
  public ContentPanel() {
    this.setLayout(new BorderLayout());

    this.add(new CheckboxTablePanel());
  }
}
