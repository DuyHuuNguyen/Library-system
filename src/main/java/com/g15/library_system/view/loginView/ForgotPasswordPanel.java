package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatClientProperties;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class ForgotPasswordPanel extends JPanel {
  private JTextField emailField;
  private JButton sendCodeBt, backBt;
  private LoginCardPanel loginCardPanel;

  public ForgotPasswordPanel(LoginCardPanel loginCardPanel) {
    this.setOpaque(false);
    this.setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
    this.loginCardPanel = loginCardPanel;
    RoundedPanel panel = new RoundedPanel(20, Color.WHITE, null);
    panel.setLayout(new MigLayout("wrap,fill,insets 35 45 30 45", "fill,250:280"));
    panel.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "arc:20;"
            + "[light]background:darken(@background,3%);"
            + "[dark]background:lighten(@background,3%)");

    JLabel resetPasswdLb = new JLabel("Reset Password", SwingConstants.CENTER);
    resetPasswdLb.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
    resetPasswdLb.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);

    emailField = new JTextField();
    emailField.setPreferredSize(new Dimension(250, 35));
    emailField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your email");
    emailField.addActionListener(e -> sendCodeBt.doClick());

    sendCodeBt = new JButton("Send Code");
    sendCodeBt.setPreferredSize(new Dimension(250, 35));
    sendCodeBt.setFont(Style.FONT_BOLD_15);
    sendCodeBt.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    sendCodeBt.setForeground(Color.WHITE);
    sendCodeBt.addActionListener(
        e -> {
          loginCardPanel.clearOTPField();
          loginCardPanel.showPanel(LoginCardPanel.VERIFY);
        });

    backBt = new JButton("Back");
    backBt.setPreferredSize(new Dimension(250, 35));
    backBt.setFont(Style.FONT_BOLD_15);
    backBt.setBackground(Color.WHITE);
    backBt.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);

    backBt.addActionListener(
        e -> {
          loginCardPanel.showPanel(LoginCardPanel.LOGIN);
        });

    panel.add(resetPasswdLb);
    panel.add(new JLabel("Email"), "gapy 8");
    panel.add(emailField);
    panel.add(sendCodeBt, "gapy 8");
    panel.add(backBt, "gapy 8");
    this.add(panel);
  }
}
