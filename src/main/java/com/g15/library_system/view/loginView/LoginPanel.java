package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatClientProperties;
import com.g15.library_system.controller.LibrarianController;
import com.g15.library_system.data.CacheData;
import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.managementView.MainFrame;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;
import net.miginfocom.swing.MigLayout;

@Slf4j
public class LoginPanel extends JPanel {
  private JTextField txtUsername;
  private JPasswordField txtPassword;
  private JButton cmdLogin;
  private LoginCardPanel loginCardPanel;

  private LibrarianController librarianController =
      ApplicationContextProvider.getBean(LibrarianController.class);

  public LoginPanel(LoginCardPanel loginCardPanel) {
    this.loginCardPanel = loginCardPanel;
    init();
  }

  private void init() {
    this.setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
    this.setOpaque(false);
    txtUsername = new JTextField();
    txtPassword = new JPasswordField();
    cmdLogin = new JButton("Login");
    cmdLogin.setPreferredSize(new Dimension(250, 35));
    cmdLogin.setFont(Style.FONT_BOLD_15);
    cmdLogin.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    cmdLogin.setForeground(Color.WHITE);
    cmdLogin.addActionListener(
        e -> {
          var request =
              LoginRequest.builder()
                  .password(String.valueOf(txtPassword.getPassword()))
                  .email(txtUsername.getText())
                  .build();
          var isLogin = this.librarianController.login(request);
          if (isLogin) {
            Window window = SwingUtilities.getWindowAncestor(cmdLogin);
            if (window != null) {
              window.dispose();
            }

            System.out.println(CacheData.getCURRENT_LIBRARIAN());
            new MainFrame();
          } else {
            ToastNotification panel =
                new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.WARNING,
                    ToastNotification.Location.TOP_CENTER,
                    "Email or password don't match");
            panel.showNotification();
          }
        });

    RoundedPanel panel = new RoundedPanel(20, new Color(230, 239, 237, 230), null);
    //    RoundedPanel panel = new RoundedPanel(20, new Color(113, 117, 115,150), null);
    panel.setLayout(new MigLayout("wrap,fill,insets 35 45 30 45", "fill,250:280"));
    panel.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "arc:20;"
            + "[light]background:darken(@background,3%);"
            + "[dark]background:lighten(@background,3%)");

    txtUsername.putClientProperty(
        FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username or email");
    txtUsername.setPreferredSize(new Dimension(250, 35));
    txtUsername.addActionListener(e -> cmdLogin.doClick());

    txtPassword.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true");
    txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
    txtPassword.setPreferredSize(new Dimension(250, 35));
    txtPassword.addActionListener(e -> cmdLogin.doClick());

    JLabel lbTitle = new JLabel("Welcome back!");
    lbTitle.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);
    JLabel description = new JLabel("Please sign in to access your account");
    description.setForeground(Color.BLACK);
    lbTitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
    //    description.putClientProperty(
    //        FlatClientProperties.STYLE,
    //        ""
    //            + "[light]foreground:lighten(@foreground,30%);"
    //            + "[dark]foreground:darken(@foreground,100%)");

    panel.add(lbTitle);
    panel.add(description);
    panel.add(new JLabel("Username"), "gapy 8");
    panel.add(txtUsername);
    panel.add(new JLabel("Password"), "gapy 8");
    panel.add(txtPassword);
    panel.add(cmdLogin, "gapy 10");
    panel.add(createForgotPasswdLabel(), "gapy 10");
    add(panel);
  }

  private Component createForgotPasswdLabel() {
    JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
    panel.setOpaque(false);
    JButton cmdForgotPassword = new JButton("<html><a href=\"#\">Forgot your password?</a></html>");
    cmdForgotPassword.putClientProperty(FlatClientProperties.STYLE, "border:3,3,3,3");
    cmdForgotPassword.setContentAreaFilled(false);
    cmdForgotPassword.setOpaque(false);
    cmdForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
    cmdForgotPassword.addActionListener(
        e -> {
          loginCardPanel.showPanel(LoginCardPanel.FORGOT);
        });

    panel.add(cmdForgotPassword);
    return panel;
  }
}
