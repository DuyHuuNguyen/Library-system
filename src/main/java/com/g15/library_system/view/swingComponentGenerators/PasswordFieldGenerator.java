package com.g15.library_system.view.swingComponentGenerators;


import com.g15.library_system.view.Style;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class PasswordFieldGenerator {
    /**
     * create a normal PasswordField
     * @param text
     * @param font
     * @param size
     * @return
     */
  public static JPasswordField createPasswordField(
      String text, Font font, Dimension size) {
    JPasswordField pwField = new JPasswordField(text);
    pwField.setForeground(Color.BLACK);
    pwField.setPreferredSize(size);
    pwField.setFont(font);
    pwField.setBorder(BorderFactory.createLineBorder(Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE, 1));
    pwField.setEchoChar('*');
    addFocusListenerPasswdField(pwField);
    return pwField;
  }
    /**
     * create a PasswordField With PlaceHolder
     * @param placeHolderText
     * @param font
     * @param borderColor
     * @param size
     * @return
     */
  public static JPasswordField createPasswordFieldWithPlaceHolder(
      String placeHolderText, Font font, Color borderColor, Dimension size) {
    JPasswordField pwField = new JPasswordField(placeHolderText);
    pwField.setForeground(Color.GRAY);
    pwField.setPreferredSize(size);
    pwField.setFont(font);
    pwField.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    pwField.setEchoChar((char) 0);
    addFocusListenerPasswdField(pwField, placeHolderText,borderColor);
    return pwField;
  }



  public static void addFocusListenerPasswdField(JPasswordField that, String originText, Color borderColor) {
    that.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            String passwd = new String(that.getPassword());
            that.setForeground(Color.BLACK);
            if (passwd.equals(originText)) {
              that.setText("");
              that.setEchoChar('*');
            }
            that.setBorder(
                BorderFactory.createLineBorder(borderColor, 3));
          }

          @Override
          public void focusLost(FocusEvent e) {
            String passwd = new String(that.getPassword());
            if (passwd.isEmpty()) {
              that.setText(originText);
              that.setEchoChar((char) 0);
              that.setForeground(Color.GRAY);
            }
            that.setBorder(
                BorderFactory.createLineBorder(borderColor, 1));
          }
        });
  }

  public static void addFocusListenerPasswdField(JPasswordField that) {
        that.addFocusListener(
                new FocusListener() {
                    @Override
                    public void focusGained(FocusEvent e) {
                        that.setBorder(
                                BorderFactory.createLineBorder(Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE, 3));
                    }

                    @Override
                    public void focusLost(FocusEvent e) {
                        that.setBorder(
                                BorderFactory.createLineBorder(Style.LOGIN_FRAME_BACKGROUND_COLOR_BLUE, 1));
                    }
                });
    }
}
