package com.g15.library_system.verifier;

import java.awt.*;
import javax.swing.*;

public class EmailVerifier extends InputVerifier {
  @Override
  public boolean verify(JComponent input) {
    JTextField textField = (JTextField) input;
    String email = textField.getText().trim();
    String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    boolean isValid = email.matches(regex);
    textField.setBackground(isValid ? Color.WHITE : Color.PINK);
    return isValid;
  }
}
