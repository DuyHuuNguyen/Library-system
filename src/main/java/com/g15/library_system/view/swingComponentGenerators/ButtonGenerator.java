package com.g15.library_system.view.swingComponentGenerators;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.CustomButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ButtonGenerator {
  /**
   * create a normal button
   *
   * @param title
   * @param font
   * @param textColor
   * @param backgroundColor
   * @param hoverColor
   * @param radius button corner rounding
   * @param textPosition
   * @param size
   * @return
   */
  public static CustomButton createCustomButton(
      String title,
      Font font,
      Color textColor,
      Color backgroundColor,
      Color hoverColor,
      int radius,
      int textPosition,
      Dimension size) {
    CustomButton button = new CustomButton(title);
    button.setFont(font);
    button.setTextColor(textColor);
    button.setBackgroundColor(backgroundColor);
    button.setHoverColor(hoverColor);
    button.setHorizontalAlignment(textPosition);
    button.setBorderRadius(radius);
    button.setDrawBorder(false);
    button.setPreferredSize(size);
    return button;
  }

  /**
   * create a normal button with border and thickness added to it
   *
   * @param title
   * @param font
   * @param textColor
   * @param backgroundColor
   * @param hoverColor
   * @param borderColor
   * @param thickness the thickness of the border
   * @param radius
   * @param textPosition
   * @param size
   * @return
   */
  public static CustomButton createCustomButton(
      String title,
      Font font,
      Color textColor,
      Color backgroundColor,
      Color hoverColor,
      Color borderColor,
      int thickness,
      int radius,
      int textPosition,
      Dimension size) {
    CustomButton bt = new CustomButton(title);
    bt.setFont(font);
    bt.setTextColor(textColor);
    bt.setBackgroundColor(backgroundColor);
    bt.setHoverColor(hoverColor);
    bt.setBorderColor(borderColor);
    bt.setBorderThickness(thickness);
    bt.setBorderRadius(radius);
    bt.setHorizontalAlignment(textPosition);
    bt.setPreferredSize(size);
    return bt;
  }

  /**
   * this will set icon image for button
   *
   * @param url
   * @param that
   * @param gap distance between button border and image
   */
  public static void setButtonIcon(String url, JButton that, int gap) {
    ImageIcon iconButton = new ImageIcon(url);
    Image image = iconButton.getImage();
    Dimension buttonSize = that.getPreferredSize();
    Image resizedImage =
        image.getScaledInstance(
            buttonSize.height - gap, buttonSize.height - gap, Image.SCALE_SMOOTH); // Resize
    that.setIcon(new ImageIcon(resizedImage));
  }

  /**
   * create a button but with a border that can switch between 2 colors lol :))
   *
   * @param title
   * @param font
   * @param textColor
   * @param backgroundColor
   * @param startColor
   * @param endColor
   * @param thickness
   * @param radius
   * @param size
   * @return
   */
  public static CustomButton createCustomButtonGradientBorder(
      String title,
      Font font,
      Color textColor,
      Color backgroundColor,
      Color startColor,
      Color endColor,
      int thickness,
      int radius,
      Dimension size) {
    CustomButton gradientButton = new CustomButton(title);
    gradientButton.setFont(font);
    gradientButton.setForeground(textColor);
    gradientButton.setGradientColors(startColor, endColor);
    gradientButton.setBackgroundColor(backgroundColor);
    gradientButton.setPreferredSize(size);
    gradientButton.setBorderRadius(radius);
    gradientButton.setBorderThickness(thickness);
    return gradientButton;
  }

  /**
   * Show/hide password button
   *
   * @param passwordField
   * @return
   */
  public static JButton createShowPasswdButton(JPasswordField passwordField) {
    JButton toggleButton = new JButton();
    toggleButton.setBackground(Style.LIGHT_BLUE);
    toggleButton.setFocusPainted(false);
    toggleButton.setFocusable(false);
    toggleButton.setBorder(
        BorderFactory.createLineBorder(Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE, 2));
    ImageIcon showPasswd =
        new ImageIcon(
            "src/main/java/Icon/showPasswd_Icon.png"); // image representing the password being
                                                       // displayed
    ImageIcon hidePasswd =
        new ImageIcon(
            "src/main/java/Icon/hidePasswd_Icon.png"); // image representing hidden password

    toggleButton.setIcon(showPasswd);
    toggleButton.addMouseListener(
        new MouseAdapter() {
          public void mouseEntered(MouseEvent evt) {
            toggleButton.setBackground(new Color(130, 180, 230));
          }

          public void mouseExited(MouseEvent evt) {
            toggleButton.setBackground(Style.LIGHT_BLUE);
          }
        });

    toggleButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            if (passwordField.getEchoChar() != '\u0000') {
              passwordField.setEchoChar('\u0000');
              toggleButton.setIcon(hidePasswd);
            } else {
              // Ẩn mật khẩu
              passwordField.setEchoChar('*');
              toggleButton.setIcon(showPasswd);
            }
          }
        });
    return toggleButton;
  }

  // create a dividing line
  //  public static JSeparator createVerticalSeparator(int w, int h) {
  //    JSeparator separator = new JSeparator(SwingConstants.VERTICAL);
  //    separator.setPreferredSize(new Dimension(w, h)); // Điều chỉnh chiều cao của separator
  //    return separator;
  //  }

  public static JButton createJButton(
      String title, Font font, Color textColor, Color backgroundColor) {
    JButton bt = new JButton(title);
    bt.setFont(font);
    bt.setForeground(textColor);
    bt.setBackground(backgroundColor);
    bt.setFocusable(false);
    bt.setBorderPainted(false);
    return bt;
  }
}
