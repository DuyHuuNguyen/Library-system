package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import java.util.regex.Pattern;
import javax.swing.*;
import org.apache.commons.math3.util.Pair;

public class OptionPaneInputFileExcel {
  public static Pair<String, String> buildPane() {

    JTextField fileNameField = new JTextField(15);
    JTextField headerField = new JTextField(15);

    JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
    panel.add(new JLabel("Name file:"));
    panel.add(fileNameField);
    panel.add(new JLabel("Header:"));
    panel.add(headerField);

    int result =
        JOptionPane.showConfirmDialog(
            null,
            panel,
            "Enter File Information",
            JOptionPane.OK_CANCEL_OPTION,
            JOptionPane.PLAIN_MESSAGE);

    if (result == JOptionPane.OK_OPTION) {
      String fileName = fileNameField.getText().trim();
      String header = headerField.getText().trim();

      if (fileName.isEmpty() || header.isEmpty()) {
        JOptionPane.showMessageDialog(null, "File name and header must not be empty.");
        return null;
      }

      Pattern invalidChars = Pattern.compile("[\\\\/:*?\"<>|]");
      if (invalidChars.matcher(fileName).find()) {
        JOptionPane.showMessageDialog(
            null, "Invalid file name. It must not contain: \\ / : * ? \" < > |");
        return null;
      }

      if (!fileName.toLowerCase().endsWith(".xlsx")) {
        fileName += ".xlsx";
      }

      return new Pair<>(fileName, header);
    }

    return null;
  }
}
