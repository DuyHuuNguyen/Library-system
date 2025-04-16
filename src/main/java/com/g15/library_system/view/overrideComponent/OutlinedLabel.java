package com.g15.library_system.view.overrideComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.font.GlyphVector;

public class OutlinedLabel extends JLabel {
    private Color outlineColor = Color.BLACK;
    private float strokeWidth = 2f;
    private boolean centered = true;

    public OutlinedLabel(String text, Font font, Color textColor, Color outlineColor) {
        super(text);
        this.outlineColor = outlineColor;
        setForeground(textColor);
        setFont(font);
        setOpaque(false);
    }

    public void setOutlineColor(Color color) {
        this.outlineColor = color;
    }

    public void setTextPosition(boolean isCentered) {
        this.centered = isCentered;
    }

    public void setStrokeWidth(float width) {
        this.strokeWidth = width;
    }

    @Override
    protected void paintComponent(Graphics g) {
        String text = getText();
        if (text == null || text.isEmpty()) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setFont(getFont());
        FontMetrics fm = g2.getFontMetrics();

        int textWidth = fm.stringWidth(text);
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        int x = centered ? (panelWidth - textWidth) / 2 : 0;
        int y = (panelHeight + fm.getAscent() - fm.getDescent()) / 2;

        GlyphVector gv = getFont().createGlyphVector(g2.getFontRenderContext(), text);
        Shape textShape = gv.getOutline(x, y);

        g2.setColor(outlineColor);
        g2.setStroke(new BasicStroke(strokeWidth));
        g2.draw(textShape);

        g2.setColor(getForeground());
        g2.fill(textShape);
        g2.dispose();
    }
}

