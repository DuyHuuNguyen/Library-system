package com.g15.library_system.view.managementView.returnBooks;

import javax.swing.*;
import java.awt.*;

public class ReturnBookPanel extends JPanel {
  public ReturnBookPanel() {
    setLayout(new BorderLayout());

    add(new ToolPanel(), BorderLayout.NORTH);
    add(new ContentPanel(), BorderLayout.CENTER);

    add(new JLabel("return book"));


  }


}
