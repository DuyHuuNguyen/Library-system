package com.g15.library_system.view.overrideComponent;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {
    private int cornerRadius;
    private Color backgroundColor;
    private Color borderColor = null;
    private int borderThickness = 2;

    public RoundedPanel(int radius, Color bgColor, Color borderColor) {
        this.cornerRadius = radius;
        this.backgroundColor = bgColor;
        this.borderColor = borderColor;
        setOpaque(false);
    }

    public void setBorderStroke(Color borderColor, int thickness) {
        this.borderColor = borderColor;
        this.borderThickness = thickness;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        if (borderColor != null) {
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(
                borderThickness / 2, borderThickness / 2,
                getWidth() - borderThickness,
                getHeight() - borderThickness,
                cornerRadius, cornerRadius
            );
        }

        g2.dispose();
        super.paintComponent(g);
    }
}
