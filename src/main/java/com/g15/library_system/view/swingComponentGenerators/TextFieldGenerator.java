package com.g15.library_system.view.swingComponentGenerators;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TextFieldGenerator {

  /**
   * Create a normal textField
   * @param text
   * @param font
   * @param textColor
   * @param borderColor
   * @param size
   * @return textField
   */
  public static JTextField createTextField(
          String text, Font font, Color textColor, Color borderColor, Dimension size) {
    JTextField field = new JTextField(text);
    field.setForeground(textColor);
    field.setPreferredSize(size);
    field.setFont(font);
    field.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    addFocusListenerForTextField(field, borderColor);
    return field;
  }


  /**
   * Create a textField with placeholder and border
   * @param placeholderText
   * @param font
   * @param textColor
   * @param borderColor
   * @param size
   * @return textField
   */
  public static JTextField createTextFieldWithPlaceholder(
      String placeholderText, Font font, Color textColor, Color borderColor, Dimension size) {
    JTextField field = new JTextField(placeholderText);
    field.setForeground(textColor);
    field.setPreferredSize(size);
    field.setFont(font);
    field.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    addFocusListenerForTextField(field, placeholderText, borderColor);
    return field;
  }
  /**
   * Create a normal textField. It cannot be written or edited, it is only used to display data.
   * @param text
   * @param font
   * @param textColor
   * @param borderColor
   * @param size
   * @return textField
   */
  public static JTextField createNonEditableTextField(
      String text,
      Font font,
      Color textColor,
      Color borderColor,
      Dimension size
      ) {
    JTextField field = new JTextField(text);
    field.setForeground(textColor);
    field.setPreferredSize(size);
    field.setFont(font);
    field.setBackground(Color.WHITE);
    field.setEditable(false);
    field.setBorder(BorderFactory.createLineBorder(borderColor, 1));
    addFocusListenerForTextField(field, borderColor);
    return field;
  }

  public static void addFocusListenerForTextField(
      JTextField field, String originText, Color borderColor) {
    field.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            field.setForeground(Color.BLACK);
            if (field.getText().equals(originText)) {
              field.setText("");
            }
            field.setBorder(BorderFactory.createLineBorder(borderColor, 3));
          }

          @Override
          public void focusLost(FocusEvent e) {
            if (field.getText().isEmpty()) {
              field.setForeground(Color.GRAY);
              field.setText(originText);
            }
            field.setBorder(BorderFactory.createLineBorder(borderColor, 1));
          }
        });
  }

  public static void addFocusListenerForTextField(JTextField field, Color borderColor) {
    field.addFocusListener(
        new FocusListener() {
          @Override
          public void focusGained(FocusEvent e) {
            field.setBorder(BorderFactory.createLineBorder(borderColor, 3));
          }

          @Override
          public void focusLost(FocusEvent e) {
            field.setBorder(BorderFactory.createLineBorder(borderColor, 1));
          }
        });
  }

}
