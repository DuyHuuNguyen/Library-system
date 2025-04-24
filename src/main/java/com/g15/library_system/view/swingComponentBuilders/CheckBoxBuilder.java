package com.g15.library_system.view.swingComponentBuilders;

import javax.swing.*;
import java.awt.*;

public class CheckBoxBuilder {
    private String text;
    private boolean selected;
    private Font font;
    private Color textColor;
    private Color backgroundColor;

    public CheckBoxBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public CheckBoxBuilder setSelected(boolean selected) {
        this.selected = selected;
        return this;
    }

    public CheckBoxBuilder setFont(Font font) {
        this.font = font;
        return this;
    }

    public CheckBoxBuilder setTextColor(Color textColor) {
        this.textColor = textColor;
        return this;
    }

    public CheckBoxBuilder setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }

    public JCheckBox build() {
        JCheckBox checkBox = new JCheckBox(this.text);
        checkBox.setSelected(this.selected);
        if (this.font != null) checkBox.setFont(this.font);
        if (this.textColor != null) checkBox.setForeground(this.textColor);
        if (this.backgroundColor != null) checkBox.setBackground(this.backgroundColor);
        return checkBox;
    }
}
