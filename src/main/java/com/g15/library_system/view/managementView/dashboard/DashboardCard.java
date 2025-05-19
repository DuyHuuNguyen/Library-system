package com.g15.library_system.view.managementView.dashboard;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import com.g15.library_system.view.swingComponentGenerators.LabelGenerator;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import lombok.Setter;

public class DashboardCard extends RoundedPanel {
    @Setter private JLabel titleLabel;
    @Setter private  JLabel numberLabel;
  private String title;
  private String amount;
  private Font font;
  private CustomButton viewButton;
  private String iconPath;
  private Color backgroundColor;
  private Color borderColor;
  private Color iconBackgroundColor = Color.white;
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

    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

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
     titleLabel = new JLabel(title);
    titleLabel.setFont(Style.FONT_SANSERIF_PLAIN_16);
    titleLabel.setAlignmentX(CENTER_ALIGNMENT);

    // amount
     numberLabel = new JLabel(amount);
    numberLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
    numberLabel.setAlignmentX(CENTER_ALIGNMENT);

    viewButton =
        CustomButtonBuilder.builder()
            .text("View Details")
            .font(Style.FONT_SANSERIF_BOLD_16)
            .textColor(Color.BLACK)
            .backgroundColor(Color.WHITE)
            .hoverColor(Color.WHITE.darker())
            .borderColor(Color.GRAY)
            .thickness(2)
            .radius(12)
            .alignment(SwingConstants.CENTER)
            .contentAreaFilled(false)
            .preferredSize(new Dimension(150, 40));

    //    viewButton.setFocusPainted(false);
    viewButton.setAlignmentX(CENTER_ALIGNMENT);
    this.add(iconPanel);
    this.add(Box.createVerticalStrut(10));
    this.add(titleLabel);
    this.add(Box.createVerticalStrut(5));
    this.add(numberLabel);
    this.add(Box.createVerticalStrut(10));
    this.add(viewButton);
  }

  public void setAmount(String amount) {
    this.amount = amount;
    this.numberLabel.setText(amount);
  }
    public void setTitle(String title) {
        this.title = title;
        this.titleLabel.setText(title);
    }

  public void setDashBoardCardButtonListener(ActionListener listener) {
    this.viewButton.addActionListener(listener);
  }
}
