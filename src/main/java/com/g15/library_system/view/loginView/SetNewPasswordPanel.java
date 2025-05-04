package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatClientProperties;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class SetNewPasswordPanel extends JPanel {
  private JPasswordField newPasswdField, confirmPasswdField;
  private JButton resetPasswdBt, backToLoginBt;
  private LoginCardPanel loginCardPanel;

  public SetNewPasswordPanel(LoginCardPanel loginCardPanel) {
    this.loginCardPanel = loginCardPanel;
    this.setOpaque(false);
    this.setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));

    RoundedPanel panel = new RoundedPanel(20, Color.WHITE, null);
    panel.setLayout(new MigLayout("wrap,fill,insets 35 45 30 45", "fill,250:280"));
    panel.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "arc:20;"
            + "[light]background:darken(@background,3%);"
            + "[dark]background:lighten(@background,3%)");
    JLabel titleLabel = new JLabel("Set new password", SwingConstants.CENTER);
    titleLabel.putClientProperty(FlatClientProperties.STYLE, "font:bold +13");
    titleLabel.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);
    panel.add(titleLabel, "gapy 5");

    JLabel newPasswdLabel = new JLabel("New Password:");
    newPasswdLabel.setFont(Style.FONT_PLAIN_13);
    newPasswdLabel.setForeground(Color.BLACK);
    panel.add(newPasswdLabel, "gapy 5");

    newPasswdField = new JPasswordField();
    newPasswdField.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
    newPasswdField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter new password");
    newPasswdField.setPreferredSize(new Dimension(250, 35));
    newPasswdField.addActionListener(e -> resetPasswdBt.doClick());
    panel.add(newPasswdField, "gapy 5");

    JLabel confirmPasswdLabel = new JLabel("Confirm Password:");
    confirmPasswdLabel.setFont(Style.FONT_PLAIN_13);
    confirmPasswdLabel.setForeground(Color.BLACK);
    panel.add(confirmPasswdLabel, "gapy 5");

    confirmPasswdField = new JPasswordField();
    confirmPasswdField.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
    confirmPasswdField.putClientProperty(
        FlatClientProperties.PLACEHOLDER_TEXT, "Confirm new password");
    confirmPasswdField.setPreferredSize(new Dimension(250, 35));
    confirmPasswdField.addActionListener(e -> resetPasswdBt.doClick());
    panel.add(confirmPasswdField, "gapy 5");

    resetPasswdBt = new JButton("Reset Password");
    resetPasswdBt.setPreferredSize(new Dimension(250, 35));
    resetPasswdBt.setFont(Style.FONT_BOLD_15);
    resetPasswdBt.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    resetPasswdBt.setForeground(Color.WHITE);

    resetPasswdBt.addActionListener(
        e -> {
          String newPassword = new String(newPasswdField.getPassword());
          String confirmPassword = new String(confirmPasswdField.getPassword());

          boolean invalidNew = newPassword.isBlank();
          boolean invalidConfirm = confirmPassword.isBlank();

          if (invalidNew || invalidConfirm) {
            highlightInvalidField(newPasswdField, invalidNew);
            highlightInvalidField(confirmPasswdField, invalidConfirm);
          } else if (!newPassword.equals(confirmPassword)) {
            highlightInvalidField(newPasswdField, true);
            highlightInvalidField(confirmPasswdField, true);
          } else {
            JOptionPane.showMessageDialog(null, "Password reset successfully!");
            loginCardPanel.showPanel(LoginCardPanel.LOGIN);
          }
        });

    panel.add(resetPasswdBt, "gapy 8");

    this.add(panel);
  }

  private void highlightInvalidField(JPasswordField field, boolean isInvalid) {
    if (isInvalid) {
      field.setBorder(BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 3));
      field.setForeground(Style.DELETE_BUTTON_COLOR_RED);
    } else {
      field.setBorder(BorderFactory.createLineBorder(Style.PURPLE_MAIN_THEME, 1));
      field.setForeground(Style.PURPLE_MAIN_THEME);
    }
  }
}
