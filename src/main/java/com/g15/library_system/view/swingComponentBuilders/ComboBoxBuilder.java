package com.g15.library_system.view.swingComponentBuilders;

import java.awt.*;
import javax.swing.*;

public class ComboBoxBuilder<T> {
  private T[] items;
  private Font font;
  private Color backgroundColor;
  private Color textColor;
  private boolean editable;

  public ComboBoxBuilder<T> setItems(T[] items) {
    this.items = items;
    return this;
  }

  public ComboBoxBuilder<T> setFont(Font font) {
    this.font = font;
    return this;
  }

  public ComboBoxBuilder<T> setBackgroundColor(Color backgroundColor) {
    this.backgroundColor = backgroundColor;
    return this;
  }

  public ComboBoxBuilder<T> setTextColor(Color textColor) {
    this.textColor = textColor;
    return this;
  }

  public ComboBoxBuilder<T> setEditable(boolean editable) {
    this.editable = editable;
    return this;
  }

  public JComboBox<T> build() {
    JComboBox<T> comboBox = new JComboBox<>(this.items);
    if (this.font != null) comboBox.setFont(this.font);
    if (this.backgroundColor != null) comboBox.setBackground(this.backgroundColor);
    if (this.textColor != null) comboBox.setForeground(this.textColor);
    comboBox.setEditable(this.editable);
    return comboBox;
  }
}
