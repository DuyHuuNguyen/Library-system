package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatClientProperties;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.loginView.component.PasswordStrengthStatus;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class RegisterPanel extends JPanel {
  private JTextField txtFirstName;
  private JTextField txtLastName;
  private JRadioButton jrMale;
  private JRadioButton jrFemale;
  private JTextField txtEmail;
  private JPasswordField txtPassword;
  private JPasswordField txtConfirmPassword;
  private ButtonGroup groupGender;
  private JButton cmdRegister;
  private PasswordStrengthStatus passwordStrengthStatus;

  public RegisterPanel() {
    init();
  }

  private void init() {
    setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
    txtFirstName = new JTextField();
    txtLastName = new JTextField();
    txtEmail = new JTextField();
    txtPassword = new JPasswordField();
    txtConfirmPassword = new JPasswordField();
    cmdRegister = new JButton("Sign Up");
    cmdRegister.setPreferredSize(new Dimension(250, 35));
    cmdRegister.setFont(Style.FONT_BOLD_15);
    cmdRegister.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    cmdRegister.setForeground(Color.WHITE);

    cmdRegister.addActionListener(
        e -> {
          if (isMatchPassword()) {
            // TODO
          } else {
            //            JOptionPane.showMessageDialog(null,"");
          }
        });

    passwordStrengthStatus = new PasswordStrengthStatus();

    JPanel panel = new JPanel(new MigLayout("wrap,fillx,insets 35 45 30 45", "[fill,360]"));
    panel.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "arc:20;"
            + "[light]background:darken(@background,3%);"
            + "[dark]background:lighten(@background,3%)");

    txtFirstName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "First name");
    txtLastName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Last name");
    txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "example@gmail.com");
    txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter password");
    txtConfirmPassword.putClientProperty(
        FlatClientProperties.PLACEHOLDER_TEXT, "Re-enter password");
    txtPassword.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
    txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");

    JLabel lbTitle = new JLabel("Welcome to our Chat Application");
    JLabel description =
        new JLabel(
            "Join us to chat, connect, and make new friends. Sign up now and start chatting!");
    lbTitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
    description.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "[light]foreground:lighten(@foreground,30%);"
            + "[dark]foreground:darken(@foreground,30%)");

    passwordStrengthStatus.initPasswordField(txtPassword);

    panel.add(lbTitle);
    panel.add(description);
    panel.add(new JLabel("Full Name"), "gapy 10");
    panel.add(txtFirstName, "split 2");
    panel.add(txtLastName);
    panel.add(new JLabel("Gender"), "gapy 8");
    panel.add(createGenderPanel());
    panel.add(new JSeparator(), "gapy 5 5");
    panel.add(new JLabel("Email"));
    panel.add(txtEmail);
    panel.add(new JLabel("Password"), "gapy 8");
    panel.add(txtPassword);
    panel.add(passwordStrengthStatus, "gapy 0");
    panel.add(new JLabel("Confirm Password"), "gapy 0");
    panel.add(txtConfirmPassword);
    panel.add(cmdRegister, "gapy 20");
    panel.add(createLoginLabel(), "gapy 10");
    add(panel);
  }

  private Component createGenderPanel() {
    JPanel panel = new JPanel(new MigLayout("insets 0"));
    panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    jrMale = new JRadioButton("Male");
    jrFemale = new JRadioButton("Female");
    groupGender = new ButtonGroup();
    groupGender.add(jrMale);
    groupGender.add(jrFemale);
    jrMale.setSelected(true);
    panel.add(jrMale);
    panel.add(jrFemale);
    return panel;
  }

  private Component createLoginLabel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel.putClientProperty(FlatClientProperties.STYLE, "" + "background:null");
    JButton cmdLogin = new JButton("<html><a href=\"#\">Sign in here</a></html>");
    cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" + "border:3,3,3,3");
    cmdLogin.setContentAreaFilled(false);
    cmdLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
    cmdLogin.addActionListener(
        e -> {
          //          FormsManager.getInstance().showForm(new LoginPanel());
        });
    JLabel label = new JLabel("Already have an account ?");
    label.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "[light]foreground:lighten(@foreground,30%);"
            + "[dark]foreground:darken(@foreground,30%)");
    panel.add(label);
    panel.add(cmdLogin);
    return panel;
  }

  public boolean isMatchPassword() {
    String password = String.valueOf(txtPassword.getPassword());
    String confirmPassword = String.valueOf(txtConfirmPassword.getPassword());
    return password.equals(confirmPassword);
  }
}
