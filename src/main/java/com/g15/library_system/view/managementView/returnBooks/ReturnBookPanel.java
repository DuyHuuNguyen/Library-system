package com.g15.library_system.view.managementView.returnBooks;

import java.awt.*;
import javax.swing.*;

public class ReturnBookPanel extends JPanel {
  public ReturnBookPanel() {
    setLayout(new BorderLayout());

    add(new ToolPanel(), BorderLayout.NORTH);
    add(new ContentPanel(), BorderLayout.CENTER);

  }
}
