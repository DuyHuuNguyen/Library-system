package com.g15.library_system.view.swingComponentBuilders;

import javax.swing.*;
import java.awt.*;

public class TextFieldBuilder {
    private String text;
    private Font font;
    private Color textColor;
    private Color backgroundColor;
    private Dimension size;
    private boolean editable;

    public TextFieldBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public TextFieldBuilder setFont(Font font) {
        this.font = font;
        return this;
    }

    public TextFieldBuilder setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public TextFieldBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public TextFieldBuilder setSize(Dimension size) {
        this.size = size;
        return this;
    }

    public TextFieldBuilder setEditable(boolean editable) {
        this.editable = editable;
        return this;
    }

    public JTextField build() {
        JTextField textField = new JTextField(this.text);
        if (this.font != null) textField.setFont(this.font);
        if (this.textColor != null) textField.setForeground(this.textColor);
        if (this.backgroundColor != null) textField.setBackground(this.backgroundColor);
        if (this.size != null) textField.setPreferredSize(this.size);
        textField.setEditable(this.editable);
        return textField;
    }
}
