package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class ComboBoxBuilder<T> {

  private final JComboBox<T> comboBox;

  private ComboBoxBuilder() {
    comboBox = new JComboBox<>();
  }

  public static <T> ComboBoxBuilder<T> builder() {
    return new ComboBoxBuilder<>();
  }

  public ComboBoxBuilder<T> items(T[] items) {
    comboBox.setModel(new DefaultComboBoxModel<>(items));
    return this;
  }

  public ComboBoxBuilder<T> font(Font font) {
    comboBox.setFont(font);
    return this;
  }

  public ComboBoxBuilder<T> backgroundColor(Color backgroundColor) {
    comboBox.setBackground(backgroundColor);
    return this;
  }

  public ComboBoxBuilder<T> textColor(Color textColor) {
    comboBox.setForeground(textColor);
    return this;
  }

  public ComboBoxBuilder<T> editable(boolean editable) {
    comboBox.setEditable(editable);
    return this;
  }

  public JComboBox<T> build() {
    return comboBox;
  }
}
