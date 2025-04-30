package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.swingComponentGenerators.ButtonGenerator;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DashboardCard extends RoundedPanel {
  private final String title;
  private final String amount;
  private Font font;
  private final CustomButton viewButton;
  private final String iconPath;
  private final Color backgroundColor;
  private final Color borderColor;
  private final Color iconBackgroundColor = Color.white;
  private int radius;

  public DashboardCard(
      String iconPath,
      String title,
      String amount,
      Color backgroundColor,
      Color iconBackgroundColor,
      Color borderColor) {
    super(20, backgroundColor, borderColor);
    this.title = title;
    this.amount = amount;
    this.iconPath = iconPath;
    this.backgroundColor = backgroundColor;
    this.borderColor = borderColor;

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    RoundedPanel iconPanel = new RoundedPanel(12, iconBackgroundColor, borderColor);
    iconPanel.setPreferredSize(new Dimension(50, 50));
    iconPanel.setMaximumSize(new Dimension(50, 50));
    iconPanel.setLayout(new BorderLayout());

    // Card's Icon
    JLabel bookIcon = LabelGenerator.createImageLabel(iconPath, 35, 35);
    bookIcon.setForeground(Color.WHITE);
    iconPanel.add(bookIcon, BorderLayout.CENTER);
    iconPanel.setAlignmentX(CENTER_ALIGNMENT);

    // Card's title
    JLabel titleLabel = new JLabel(title);
    titleLabel.setFont(Style.FONT_SANSERIF_PLAIN_16);
    titleLabel.setAlignmentX(CENTER_ALIGNMENT);

    // amount
    JLabel numberLabel = new JLabel(amount);
    numberLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    numberLabel.setAlignmentX(CENTER_ALIGNMENT);

    viewButton =
        ButtonGenerator.createCustomButton(
            "View Details",
            Style.FONT_SANSERIF_BOLD_16,
            Color.BLACK,
            Color.white,
            Color.white.darker(),
            Color.GRAY,
            1,
            10,
            SwingConstants.CENTER,
            new Dimension(150, 40));

    viewButton.setAlignmentX(CENTER_ALIGNMENT);
    viewButton.setFocusPainted(false);

    add(iconPanel);
    add(Box.createVerticalStrut(10));
    add(titleLabel);
    add(Box.createVerticalStrut(5));
    add(numberLabel);
    add(Box.createVerticalStrut(10));
    add(viewButton);
  }

  public void setDashBoardCardButtonListener(ActionListener listener) {
    this.viewButton.addActionListener(listener);
  }
}
