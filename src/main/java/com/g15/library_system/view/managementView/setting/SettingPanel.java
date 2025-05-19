package com.g15.library_system.view.managementView.setting;

import com.g15.library_system.view.managementView.dashboard.BookCoverPanel;
import com.g15.library_system.view.overrideComponent.labels.RoundedImageLabel;
import java.awt.*;
import javax.swing.*;

public class SettingPanel extends JPanel {
  public SettingPanel() {
    setLayout(new FlowLayout(FlowLayout.LEFT));

    //    add(new JLabel("setting"));

    this.add(
        new BookCoverPanel(
            "/bookImages/theHobbit/The Hobbit.png", "The Hobbit", "J.R.R. Tolkien", 150, 220));
    this.add(new RoundedImageLabel("/bookImages/theHobbit/The Hobbit.png", 150, 220));
  }
}
