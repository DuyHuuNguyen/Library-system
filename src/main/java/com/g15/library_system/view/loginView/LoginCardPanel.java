package com.g15.library_system.view.loginView;

import java.awt.*;
import javax.swing.*;

public class LoginCardPanel extends JPanel {
  public static final String LOGIN = "login";
  public static final String FORGOT = "forgot";
  public static final String VERIFY = "verify";
  public static final String NEW_PASS = "newPass";
  private CardLayout cardLayout;
  private String backgroundImagePath;
  private LoginPanel loginPanel;
  private ForgotPasswordPanel forgotPanel;
  private VerificationCodePanel verifyPanel;
  private SetNewPasswordPanel newPassPanel;

  public LoginCardPanel(String backgroundImagePath) {
    this.backgroundImagePath = backgroundImagePath;
    cardLayout = new CardLayout();
    this.setLayout(cardLayout);

    loginPanel = new LoginPanel(this);
    forgotPanel = new ForgotPasswordPanel(this);
    verifyPanel = new VerificationCodePanel(this);
    newPassPanel = new SetNewPasswordPanel(this);

    this.add(loginPanel, LOGIN);
    this.add(forgotPanel, FORGOT);
    this.add(verifyPanel, VERIFY);
    this.add(newPassPanel, NEW_PASS);
  }

  public void showPanel(String cardName) {
    cardLayout.show(this, cardName);
  }

  public void clearOTPField() {
    this.verifyPanel.clearOTPField();
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Image backgroundImage = new ImageIcon(getClass().getResource(backgroundImagePath)).getImage();
    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
  }
}
