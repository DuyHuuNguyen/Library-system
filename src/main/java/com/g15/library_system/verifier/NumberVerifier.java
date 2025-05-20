package com.g15.library_system.verifier;

import java.awt.*;
import javax.swing.*;

public class NumberVerifier extends InputVerifier {
  @Override
  public boolean verify(JComponent input) {
    JTextField textField = (JTextField) input;
    String text = textField.getText().trim();
    try {
      Double.parseDouble(text); // hoặc Integer.parseInt(text)
      input.setBackground(Color.WHITE); // Hợp lệ thì để màu trắng
      return true;
    } catch (NumberFormatException e) {
      input.setBackground(Color.PINK); // Sai thì tô hồng
      return false;
    }
  }
}
