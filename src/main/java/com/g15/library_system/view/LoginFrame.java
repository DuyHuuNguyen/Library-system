package com.g15.library_system.view;


import com.g15.library_system.view.managementView.MainFrame;
import com.g15.library_system.view.overrideComponent.CustomButton;
import com.g15.library_system.view.swingComponentGenerators.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class LoginFrame extends JFrame{
    private WelcomePanel welcomePanel;
    private SignInMainPn signInMainPn;

    private static final String CUSTOMER_ROLE = "Customer";
    private static final String MANAGER_ROLE = "Manager";

    public LoginFrame(){
//        setLayout(new GridLayout(1,2));
        setLayout(new BorderLayout());
        setTitle("Computer Management");
        setSize(1300, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon("src/main/java/view/LibraryUI/icons/li2ng").getImage());
        try {
            UIManager.put("ComboBox.focus", UIManager.get("ComboBox.background"));
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        signInMainPn = new SignInMainPn(this);
        welcomePanel = new WelcomePanel();
        add(welcomePanel);
        add(signInMainPn,BorderLayout.EAST);
        setVisible(true);
    }

    private class WelcomePanel extends JPanel {
        JLabel welcomeLabel, subTextLabel;

//        private String imagePath ="src/main/java/view/LibraryUI/images/NLULibrary.jpg";
        private String imagePath ="src/main/java/view/LibraryUI/images/hogwarts2.png";

        public WelcomePanel() {
            setPreferredSize(new Dimension(550, 700));
            setBackground(Style.PURPLE_MAIN_THEME);
            setLayout(new BorderLayout());
            setBorder(BorderFactory.createEmptyBorder(0, 60, 50, 50));

//            welcomeLabel = LabelConfig.createLabel("<html>Welcome to the Library Management System!<br>Enjoy browsing and managing your books.<html>",
//                    Style.FONT_BOLD_30,new Color(121, 138, 7),SwingConstants.LEFT);
//            add(welcomeLabel, BorderLayout.SOUTH);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Image backgroundImage = new ImageIcon(imagePath).getImage();
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        public void setImagePath(String imagePath) {
            this.imagePath = imagePath;
        }
    }

    private class SignInMainPn extends JPanel {
        private SignInPanel signInPanel;
        private ForgotPasswdPanel forgotPasswdPanel;
        private CardLayout cardLayoutMain;
        private LoginFrame loginFrame;

        SignInMainPn(LoginFrame loginFrame) {
            this.loginFrame = loginFrame;
            cardLayoutMain = new CardLayout();
            setLayout(cardLayoutMain);
            signInPanel = new SignInPanel(loginFrame);
            forgotPasswdPanel = new ForgotPasswdPanel();

            add(signInPanel, "signIn");
            add(forgotPasswdPanel, "forgotPassword");
            cardLayoutMain.show(this, "signIn");
        }

        public void showPanel(String title) {
            cardLayoutMain.show(this, title);
        }
    }

    private class SignInPanel extends JPanel {
        private LoginFrame loginFrame;
        private JLabel signInLabel;
        private JTextField emailField;
        private JPasswordField passwdFieldSignIn;
        private JComboBox<String> roleComboBox;
        private CustomButton signInButton;
        private JCheckBox showPasswdCB;
        private JButton forgotPasswdBt;

        SignInPanel(LoginFrame loginFrame) {
            this.loginFrame = loginFrame;
            setLayout(new GridBagLayout());
            setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Sign In title
            signInLabel = LabelGenerator.createLabel("Sign In", Style.FONT_TITLE_BOLD_45, Style.PURPLE_MAIN_THEME, SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 2;
            gbc.anchor = GridBagConstraints.CENTER;
            add(signInLabel, gbc);

// Reset lại gridwidth cho các thành phần kế tiếp
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;

// Role ComboBox
            gbc.gridy++;
            gbc.gridx = 0;
            String[] roles = {"Librarian", "Admin"};
            roleComboBox = new JComboBox<>(roles);
            roleComboBox.setPreferredSize(new Dimension(320, 45));
            roleComboBox.setFont(Style.FONT_PLAIN_20);
            roleComboBox.setBackground(Color.WHITE);
            roleComboBox.setBorder(BorderFactory.createLineBorder(Style.PURPLE_MAIN_THEME, 1));
            gbc.gridwidth = 2; // Cho ComboBox chiếm hết dòng
            add(roleComboBox, gbc);

// User Email Label
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            JLabel userEmailLabel = LabelGenerator.createLabel("User Email", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.LEFT);
            add(userEmailLabel, gbc);

// Email TextField
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            emailField = TextFieldGenerator.createTextFieldWithPlaceholder(
                    "User Email", Style.FONT_PLAIN_20, Color.GRAY, Style.PURPLE_MAIN_THEME, new Dimension(320, 45)
            );
            emailField.addActionListener(e -> signInButton.doClick());
            add(emailField, gbc);

// Password Label
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            JLabel passwordLabel = LabelGenerator.createLabel("Password", Style.FONT_BOLD_16, Color.BLACK, SwingConstants.LEFT);
            add(passwordLabel, gbc);

// Password Field
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            passwdFieldSignIn = PasswordFieldGenerator.createPasswordFieldWithPlaceHolder(
                    "Password", Style.FONT_PLAIN_20, Style.PURPLE_MAIN_THEME, new Dimension(320, 45)
            );
            passwdFieldSignIn.addActionListener(e -> signInButton.doClick());
            add(passwdFieldSignIn, gbc);

// Show Password Checkbox
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            showPasswdCB = new JCheckBox("Show Password");
            showPasswdCB.setPreferredSize(new Dimension(300, 15));
            showPasswdCB.setFocusPainted(false);
            showPasswdCB.setFont(Style.FONT_PLAIN_13);
            showPasswdCB.setFocusable(false);
            showPasswdCB.setBackground(Color.WHITE);
            showPasswdCB.setForeground(Style.PURPLE_MAIN_THEME);
            showPasswdCB.addActionListener(
                    e -> {
                        String passwd = new String(passwdFieldSignIn.getPassword());
                        if (passwd.equals("Password")) {
                            showPasswdCB.setSelected(false);
                        } else {
                            if (showPasswdCB.isSelected()) {
                                passwdFieldSignIn.setEchoChar((char) 0); // Hiện mật khẩu
                            } else {
                                passwdFieldSignIn.setEchoChar('*'); // Ẩn mật khẩu
                            }
                        }
                    }
            );
            add(showPasswdCB, gbc);

// Sign In Button
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            signInButton = ButtonGenerator.createCustomButton(
                    "Sign In",
                    Style.FONT_BOLD_24,
                    Color.white,
                    Style.PURPLE_MAIN_THEME,
                    Style.PURPLE_MAIN_THEME_DARKER,
                    Style.PURPLE_MAIN_THEME,
                    1,
                    20,
                    SwingConstants.CENTER,
                    new Dimension(350, 45)
            );
            signInButton.addActionListener(e -> {
                loginFrame.dispose();
                new MainFrame();
            });
            add(signInButton, gbc);


            forgotPasswdBt = ButtonGenerator.createJButton("You forgot your Password?",Style.FONT_PLAIN_13,Style.PURPLE_MAIN_THEME,Color.WHITE);
//            forgotPasswdBt.setOpaque(false);
            forgotPasswdBt.addMouseListener(
                    new MouseAdapter() {
                        public void mouseEntered(MouseEvent evt) {
                            forgotPasswdBt.setBackground(Style.LIGHT_BLUE);
                        }

                        public void mouseExited(MouseEvent evt) {
                            forgotPasswdBt.setBackground(Color.WHITE);
                        }
                    });
            forgotPasswdBt.addActionListener(e ->{
                signInMainPn.showPanel("forgotPassword");
            });
            gbc.gridy++;
            gbc.gridx = 0;
            gbc.gridwidth = 2;
            add(forgotPasswdBt, gbc);
        }

    }

    private class ForgotPasswdPanel extends JPanel {
        private InputEmail inputEmail;
        private VerificationCodePanel verificationCodePanel;
        private SetNewPasswdPanel setNewPasswdPanel;
        private CardLayout cardLayoutForgotPass;
        private int otp = 0;
        private String to = "";
        private String name = "";
        private static String email;


        ForgotPasswdPanel() {
            setBackground(Color.WHITE);
            cardLayoutForgotPass = new CardLayout();
            setLayout(cardLayoutForgotPass);
            inputEmail = new InputEmail();
            verificationCodePanel = new VerificationCodePanel();
            setNewPasswdPanel = new SetNewPasswdPanel();
            add(inputEmail, "inputEmail");
            add(verificationCodePanel, "verificationCode");
            add(setNewPasswdPanel, "setNewPasswd");

            cardLayoutForgotPass.show(this, "inputEmail");
        }

        public void showInnerPanel(String message) {
            cardLayoutForgotPass.show(this, message);
        }

        private boolean sendOTP(String to, String name, int theOtp) {
            return false;
        }

        class InputEmail extends JPanel {
            private JTextField emailField;
            private CustomButton sendCodeBt, backBt;
            private JRadioButton managerBt, customerBt;

            public InputEmail() {
                setBackground(Color.WHITE);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(20, 10, 20, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel resetPasswdLb = new JLabel("Reset Password", SwingConstants.CENTER);
                resetPasswdLb.setFont(Style.FONT_TITLE_BOLD_45);
                resetPasswdLb.setForeground(Style.PURPLE_MAIN_THEME);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(resetPasswdLb, gbc);

                ButtonGroup group = new ButtonGroup();

                managerBt =
                        RadioButtonGenerator.createRadioButton(
                                "Manager",
                                Style.FONT_PLAIN_15,
                                Style.PURPLE_MAIN_THEME,
                                Style.WORD_COLOR_WHITE,
                                new Dimension(120, 40));
                customerBt =
                        RadioButtonGenerator.createRadioButton(
                                "Customer",
                                Style.FONT_PLAIN_15,
                                Style.PURPLE_MAIN_THEME,
                                Style.WORD_COLOR_WHITE,
                                new Dimension(120, 40));
                customerBt.setSelected(true);
                group.add(customerBt);
                group.add(managerBt);

                JPanel checkBoxPanel = new JPanel();

                checkBoxPanel.setLayout(new FlowLayout());
                checkBoxPanel.setBackground(Style.WORD_COLOR_WHITE);
                checkBoxPanel.add(customerBt);
                checkBoxPanel.add(managerBt);
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.gridx = 0;
                gbc.gridy = 1;
                gbc.gridwidth = 2;
                add(checkBoxPanel, gbc);

                sendCodeBt =
                        ButtonGenerator.createCustomButton(
                                "Send Code",
                                Style.FONT_BOLD_24,
                                Color.white,
                                Style.PURPLE_MAIN_THEME,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(350, 50));
                sendCodeBt.addActionListener(
                        e -> {
//                            email = emailField.getText();
//                            if (managerBt.isSelected()) {
//                                forgotPasswordStatus = ForgotPasswordStatus.MANAGER;
//                                var manager = COMPUTER_SHOP.findManagerByEmail(email);
//                                if (manager == null)
//                                    ToastNotification.showToast("Not found user", 3000, 30, -1, -1);
//                                name = manager.getFullName();
//
//                            } else {
//                                forgotPasswordStatus = ForgotPasswordStatus.CUSTOMER;
//                                var customer = COMPUTER_SHOP.findCustomerByEmail(email);
//                                if (customer == null)
//                                    ToastNotification.showToast("Not found user", 3000, 30, -1, -1);
//                                name = customer.getFullName();
//                            }
//                            otp = new EmailConfig().generateOTP();
//                            System.out.println("otp " + otp);
//                            sendOTP(email, name, otp);
                            showInnerPanel("verificationCode");
                        });

                gbc.gridwidth = 1;
                gbc.gridy++;
                gbc.gridx = 0;
                JLabel emailIcon = new JLabel(new ImageIcon("src/main/java/Icon/email_icon.png"));
                emailIcon.setPreferredSize(new Dimension(30, 30));
                add(emailIcon, gbc);
                gbc.gridx = 1;
                emailField =
                        TextFieldGenerator.createTextFieldWithPlaceholder(
                                "User Email", Style.FONT_PLAIN_20, Color.GRAY,Style.PURPLE_MAIN_THEME, new Dimension(320, 45));
                emailField.addActionListener(
                        e -> {
                            sendCodeBt.doClick();
                        });
                add(emailField, gbc);

                gbc.gridy++;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                add(sendCodeBt, gbc);

                backBt =
                        ButtonGenerator.createCustomButton(
                                "Back",
                                Style.FONT_BOLD_24,
                                Style.PURPLE_MAIN_THEME,
                                Color.white,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(350, 50));
                backBt.addActionListener(
                        e ->signInMainPn.showPanel("signIn")
                        );
                gbc.gridy++;
                add(backBt, gbc);
            }


        }

        class VerificationCodePanel extends JPanel {
            JTextField[] otpFields = new JTextField[4];
            CustomButton verifyBt, backBt;
            JButton resendCodeBt;
            final int RESET_OTP = 0;

            VerificationCodePanel() {
                setBackground(Color.WHITE);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;

                JLabel resetPasswdLb = new JLabel("Enter Verification Code", SwingConstants.CENTER);
                resetPasswdLb.setFont(Style.FONT_TITLE_BOLD_45);
                resetPasswdLb.setForeground(Style.PURPLE_MAIN_THEME);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                add(resetPasswdLb, gbc);

                verifyBt =
                        ButtonGenerator.createCustomButton(
                                "Verify",
                                Style.FONT_BOLD_24,
                                Color.white,
                                Style.PURPLE_MAIN_THEME,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(350, 50));

                verifyBt.addActionListener(
                        e -> {

//                                boolean isEmpty = false;
//                                int otpInput = 0;
//                                try {
//                                    for (int i = 0; i < otpFields.length; i++) {
//                                        int num = Integer.parseInt(otpFields[i].getText().trim());
//                                        otpInput += num;
//                                        if (i < otpFields.length - 1) otpInput *= 10;
//                                    }
//                                } catch (NullPointerException exception) {
//                                    System.out.println(exception);
//                                    setColorTextField();
//                                }
//
//                                System.out.println(otpInput + "   " + otp);
//                                if (otp == otpInput) {
//                                    showInnerPanel("setNewPasswd");
//                                    otp = RESET_OTP;
//                                    for (int i = 0; i < otpFields.length; i++) {
//                                        otpFields[i].setText("");
//                                    }
//                                } else {
//                                    setColorTextField();
//                                    ToastNotification.showToast("Wrong OTP", 2500, 50, -1, -1);
//                                }
                            showInnerPanel("setNewPasswd");
                        });

                resendCodeBt = new JButton("Re-send Verify Code");
                resendCodeBt.setForeground(Style.PURPLE_MAIN_THEME);
                resendCodeBt.setBackground(Color.WHITE);
                resendCodeBt.setFont(Style.FONT_PLAIN_16);
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
//                                EmailConfig emailConfig = new EmailConfig();
//                                otp = emailConfig.generateOTP();
                                System.out.println(otp + " new ");
                                boolean sent = sendOTP(to, name, otp);
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

                backBt =
                        ButtonGenerator.createCustomButton(
                                "Back",
                                Style.FONT_BOLD_24,
                                Style.PURPLE_MAIN_THEME,
                                Color.white,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(350, 50));
                backBt.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                showInnerPanel("inputEmail");
                            }
                        });

                gbc.gridy++;
                JPanel otpPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                otpPanel.setBackground(Color.WHITE);
                for (int i = 0; i < 4; i++) {
                    otpFields[i] = new JTextField();
                    otpFields[i].setFont(new Font("Arial", Font.BOLD, 60));
                    otpFields[i].setHorizontalAlignment(JTextField.CENTER);
                    otpFields[i].setPreferredSize(new Dimension(80, 90));
                    otpFields[i].setBorder(
                            BorderFactory.createLineBorder(Style.PURPLE_MAIN_THEME, 1));
                    otpFields[i].addActionListener(e -> verifyBt.doClick());
                    int index1 = i;
                    otpFields[i].addFocusListener(
                            new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    otpFields[index1].setBorder(
                                            BorderFactory.createLineBorder(Style.PURPLE_MAIN_THEME, 4));
                                }

                                @Override
                                public void focusLost(FocusEvent e) {

                                    otpFields[index1].setBorder(
                                            BorderFactory.createLineBorder(Style.PURPLE_MAIN_THEME, 1));
                                }
                            });

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
                                    if (Character.isDigit(e.getKeyChar())
                                            && otpFields[index].getText().length() == 1) {
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

                add(otpPanel, gbc);

                gbc.gridy++;
                JPanel resendCodePn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                resendCodePn.setBackground(Color.WHITE);
                resendCodePn.add(resendCodeBt);
                add(resendCodePn, gbc);

                gbc.insets = new Insets(5, 10, 5, 10);
                gbc.gridy++;
                JPanel verifyPn = new JPanel();
                verifyPn.setBackground(Color.WHITE);
                verifyPn.add(verifyBt);
                add(verifyPn, gbc);

                JPanel backPn = new JPanel();
                backPn.setBackground(Color.WHITE);
                backPn.add(backBt);
                gbc.gridy++;
                add(backPn, gbc);
            }

            private void setColorTextField() {
                for (int i = 0; i < otpFields.length; i++) {
                    otpFields[i].setBorder(BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 4));
                }
            }
        }

        class SetNewPasswdPanel extends JPanel {
            JPasswordField newPasswdField, confirmPasswdField;
            CustomButton resetPasswdBt, backBt;
            JCheckBox showPasswd;

            private void resetTextPassword() {
                newPasswdField.setText("Enter your new password");
                confirmPasswdField.setText("Confirm your new password");
            }

            SetNewPasswdPanel() {
                setBackground(Color.WHITE);
                setLayout(new GridBagLayout());
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(15, 10, 15, 10);
                gbc.fill = GridBagConstraints.HORIZONTAL;
                JLabel setNewPasswdLb = new JLabel("New Password", SwingConstants.CENTER);
                setNewPasswdLb.setFont(Style.FONT_TITLE_BOLD_45);
                setNewPasswdLb.setForeground(Style.PURPLE_MAIN_THEME);
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.gridwidth = 2;
                gbc.anchor = GridBagConstraints.CENTER;
                add(setNewPasswdLb, gbc);

                resetPasswdBt =
                        ButtonGenerator.createCustomButton(
                                "Reset Password",
                                Style.FONT_BOLD_24,
                                Color.white,
                                Style.PURPLE_MAIN_THEME,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(320, 50));
                resetPasswdBt.addActionListener( e-> {
                                String newPassword = new String(newPasswdField.getPassword());
                                String confirmPassword = new String(confirmPasswdField.getPassword());

                                if ((!newPassword.equals(confirmPassword))
                                        || newPassword.equals("Enter your new password")
                                        || confirmPassword.equals("Confirm your new password")) {

                                    if (newPassword.equals("Enter your new password")) {
                                        newPasswdField.setBorder(
                                                BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 4));
                                        newPasswdField.setForeground(Style.DELETE_BUTTON_COLOR_RED);

                                    } else if (confirmPassword.equals("Confirm your new password")) {
                                        confirmPasswdField.setBorder(
                                                BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 4));
                                        confirmPasswdField.setForeground(Style.DELETE_BUTTON_COLOR_RED);

                                    } else {
                                        confirmPasswdField.setBorder(
                                                BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 4));
                                        confirmPasswdField.setForeground(Style.DELETE_BUTTON_COLOR_RED);
                                        newPasswdField.setBorder(
                                                BorderFactory.createLineBorder(Style.DELETE_BUTTON_COLOR_RED, 4));
                                        newPasswdField.setForeground(Style.DELETE_BUTTON_COLOR_RED);
                                    }

                                } else {

                                    JOptionPane.showMessageDialog(null, "Password reset successfully!");
                                    showInnerPanel("inputEmail");
                                    signInMainPn.showPanel("signIn");
                                }
                        });

                gbc.gridwidth = 1;
                gbc.gridy++;
                gbc.gridx = 0;
                ImageIcon passwordIcon = new ImageIcon("src/main/java/Icon/lock_icon.png");
                JLabel passwordIconLb1 = new JLabel(passwordIcon);

                add(passwordIconLb1, gbc);
                gbc.gridx = 1;
                newPasswdField =
                        PasswordFieldGenerator.createPasswordFieldWithPlaceHolder(
                                "Enter new password", Style.FONT_PLAIN_20, Style.PURPLE_MAIN_THEME, new Dimension(320, 45));
                newPasswdField.addActionListener(e -> resetPasswdBt.doClick());
                add(newPasswdField, gbc);

                JLabel passwordIconLb2 = new JLabel(passwordIcon);
                gbc.gridy++;
                gbc.gridx = 0;
                add(passwordIconLb2, gbc);
                gbc.gridx = 1;
                confirmPasswdField =
                        PasswordFieldGenerator.createPasswordFieldWithPlaceHolder(
                                "Confirm new password",
                                Style.FONT_PLAIN_20,
                                Style.PURPLE_MAIN_THEME,
                                new Dimension(320, 45));
                confirmPasswdField.addActionListener(e -> resetPasswdBt.doClick());
                add(confirmPasswdField, gbc);

                gbc.gridy++;
                gbc.gridx = 1;
                showPasswd = new JCheckBox("Show Password");
                showPasswd.setPreferredSize(new Dimension(300, 15));
                showPasswd.setFocusPainted(false);
                showPasswd.setFocusable(false);
                showPasswd.setForeground(Style.PURPLE_MAIN_THEME);
                showPasswd.setBackground(Color.WHITE);
                showPasswd.addActionListener(
                        e ->{
                                String passwdNew = new String(newPasswdField.getPassword());
                                String passwdConfirm = new String(confirmPasswdField.getPassword());

                                if (passwdNew.equals("Enter new password")
                                        && passwdConfirm.equals("Confirm new password")) {
                                    showPasswd.setSelected(false);
                                } else {
                                    if (showPasswd.isSelected()) {
                                        newPasswdField.setEchoChar((char) 0);
                                        confirmPasswdField.setEchoChar((char) 0);
                                    } else {
                                        newPasswdField.setEchoChar('*');
                                        confirmPasswdField.setEchoChar('*');
                                    }
                                }
                        });
                add(showPasswd, gbc);

                backBt =
                        ButtonGenerator.createCustomButton(
                                "Back",
                                Style.FONT_BOLD_24,
                                Style.PURPLE_MAIN_THEME,
                                Color.white,
                                new Color(160, 231, 224),
                                Style.PURPLE_MAIN_THEME,
                                1,
                                20,
                                SwingConstants.CENTER,
                                new Dimension(320, 50));
                backBt.addActionListener(
                        e -> showInnerPanel("verificationCode"));

                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.gridy++;
                gbc.gridx = 0;
                gbc.gridwidth = 2;
                add(resetPasswdBt, gbc);
                gbc.gridy++;
                add(backBt, gbc);
            }
        }
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
