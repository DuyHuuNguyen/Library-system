package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import javax.swing.*;

public class FooterPanel extends JPanel {
  public FooterPanel() {
    setLayout(new BorderLayout());
    setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));

    // Total books info
    JLabel totalBooksLabel = new JLabel("Total Books : 1000");
    totalBooksLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
    add(totalBooksLabel, BorderLayout.WEST);

    // Show Entries Panel
    //    add(new ShowEntriesPanel(), BorderLayout.EAST);
  }
}
