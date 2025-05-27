package com.g15.library_system.view.loginView;

import com.formdev.flatlaf.FlatClientProperties;
import com.g15.library_system.controller.LibrarianController;
import com.g15.library_system.dto.request.VerifyOTPRequest;
import com.g15.library_system.provider.ApplicationContextProvider;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedPanel;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;
import net.miginfocom.swing.MigLayout;

@Slf4j
public class VerificationCodePanel extends JPanel {
  private JTextField[] otpFields = new JTextField[4];
  private JButton verifyBt, backBt;
  private JButton resendCodeBt;
  private final int RESET_OTP = 0;
  private LoginCardPanel loginCardPanel;

  private LibrarianController librarianController =
      ApplicationContextProvider.getBean(LibrarianController.class);

  public VerificationCodePanel(LoginCardPanel loginCardPanel) {
    setOpaque(false);
    setLayout(new MigLayout("fill,insets 20", "[center]", "[center]"));
    this.loginCardPanel = loginCardPanel;
    RoundedPanel panel = new RoundedPanel(20, new Color(230, 239, 237, 230), null);
    panel.setLayout(new MigLayout("wrap,fill,insets 35 45 30 45", "fill,250:280"));
    panel.putClientProperty(
        FlatClientProperties.STYLE,
        ""
            + "arc:20;"
            + "[light]background:darken(@background,3%);"
            + "[dark]background:lighten(@background,3%)");

    JLabel verifyCodeLb = new JLabel("Enter Verification Code", SwingConstants.CENTER);
    verifyCodeLb.putClientProperty(FlatClientProperties.STYLE, "font:bold +10");
    verifyCodeLb.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);

    JPanel otpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    otpPanel.setOpaque(false);
    for (int i = 0; i < 4; i++) {
      otpFields[i] = new JTextField();
      otpFields[i].setFont(Style.FONT_BOLD_24);
      otpFields[i].setHorizontalAlignment(JTextField.CENTER);
      otpFields[i].setPreferredSize(new Dimension(60, 60));
      otpFields[i].addActionListener(e -> verifyBt.doClick());
      int index = i;
      otpFields[i].addKeyListener(
          new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
              if (!Character.isDigit(e.getKeyChar())) {
                e.consume();
              } else if (otpFields[index].getText().length() >= 1) {
                e.consume();
              }
            }

            @Override
            public void keyReleased(KeyEvent e) {
              if (Character.isDigit(e.getKeyChar()) && otpFields[index].getText().length() == 1) {
                if (index < 3) {
                  SwingUtilities.invokeLater(() -> otpFields[index + 1].requestFocus());
                }
              } else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (index > 0 && otpFields[index].getText().isEmpty()) {
                  otpFields[index - 1].setText("");
                  otpFields[index - 1].requestFocus();
                }
              }
            }
          });
      otpPanel.add(otpFields[i]);
    }

    JPanel resendCodePn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    resendCodePn.setOpaque(false);

    resendCodeBt = new JButton("Re-send Verify Code");
    resendCodeBt.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);
    resendCodeBt.setBackground(Color.WHITE);
    resendCodeBt.setFont(Style.FONT_PLAIN_13);
    resendCodeBt.setFocusable(false);
    resendCodeBt.setBorderPainted(false);
    resendCodeBt.addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            resendCodeBt.setBackground(Style.LIGHT_BLUE);
          }

          public void mouseExited(MouseEvent evt) {
            resendCodeBt.setBackground(Color.WHITE);
          }
        });
    resendCodeBt.addActionListener(
        new ActionListener() {
          private boolean isCooldown = false;
          private Timer timer = new Timer();

          @Override
          public void actionPerformed(ActionEvent e) {
            if (!isCooldown) {
              isCooldown = true;
              resendCodeBt.setEnabled(false);
              startCooldown();
            }

            JOptionPane.showMessageDialog(
                null, "We have sent a new verification code to your email!");
          }

          private void startCooldown() {
            TimerTask task =
                new TimerTask() {
                  int remainingTime = 25;

                  @Override
                  public void run() {
                    if (remainingTime > 0) {
                      resendCodeBt.setText("Wait " + remainingTime + "s");
                      remainingTime--;
                    } else {
                      resendCodeBt.setText("Re-send Verify Code");
                      resendCodeBt.setEnabled(true);
                      isCooldown = false;
                      cancel();
                    }
                  }
                };
            timer.scheduleAtFixedRate(task, 0, 1000);
          }
        });
    resendCodePn.add(resendCodeBt);

    verifyBt = new JButton("Verify");
    verifyBt.setPreferredSize(new Dimension(250, 35));
    verifyBt.setFont(Style.FONT_BOLD_15);
    verifyBt.setBackground(Style.BLUE_MENU_BACKGROUND_COLOR);
    verifyBt.setForeground(Color.WHITE);
    verifyBt.addActionListener(
        e -> {
          var verifyOTPRequest = VerifyOTPRequest.builder().otp(this.getOTPField()).build();
          log.info("ccc {}", verifyOTPRequest);

          var isVerifyOTP = this.librarianController.verifyOTP(verifyOTPRequest);
          log.debug("verify otp {}", isVerifyOTP);

          if (!isVerifyOTP) {
            this.clearOTPField();
            ToastNotification notification =
                new ToastNotification(
                    JOptionPane.getFrameForComponent(this),
                    ToastNotification.Type.WARNING,
                    ToastNotification.Location.CENTER,
                    "OTP don't match");
            notification.showNotification();
          } else loginCardPanel.showPanel(LoginCardPanel.NEW_PASS);
        });

    backBt = new JButton("Back");
    backBt.setPreferredSize(new Dimension(250, 35));
    backBt.setFont(Style.FONT_BOLD_15);
    backBt.setBackground(Color.WHITE);
    backBt.setForeground(Style.BLUE_MENU_BACKGROUND_COLOR);

    backBt.addActionListener(
        e -> {
          loginCardPanel.showPanel(LoginCardPanel.FORGOT);
        });

    panel.add(verifyCodeLb);
    panel.add(otpPanel, "gapy 8");
    panel.add(resendCodePn, "gapy 8");
    panel.add(verifyBt, "gapy 8");
    panel.add(backBt, "gapy 8");

    add(panel);
  }

  public void clearOTPField() {
    for (int i = 0; i < otpFields.length; i++) {
      otpFields[i].setText("");
    }
  }

  public String getOTPField() {
    String otp = "";
    for (int i = 0; i < otpFields.length; i++) {
      otp += otpFields[i].getText();
    }
    return otp;
  }
}
