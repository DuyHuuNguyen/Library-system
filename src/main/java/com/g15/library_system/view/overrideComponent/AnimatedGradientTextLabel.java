package com.g15.library_system.view.overrideComponent;

import javax.swing.*;
import java.awt.*;

public class AnimatedGradientTextLabel extends JLabel {
    private float hue = 0f;
    private int timeChange = 150;    // Timer change color (ms)
    private Font font = new Font("Arial", Font.BOLD, 20);
    private Dimension size = new Dimension(500, 100);

    public AnimatedGradientTextLabel(String text, Font font, Dimension size, int position) {
        super(text);
        this.font = font;
        this.size = size;
        setFont(font);
        setPreferredSize(size);
        setHorizontalAlignment(position);

        Timer timer = new Timer(timeChange, e -> {
            hue += 0.02f;
            if (hue > 1f) hue = 0f;
            repaint();
        });
        timer.start();
    }

//    @Override
//    protected void paintComponent(Graphics g) {
//        Graphics2D g2d = (Graphics2D) g.create();
//        int width = getWidth();
//        int height = getHeight();
//
//        // Tạo 4 màu theo hue thay đổi
//        Color[] colors = new Color[] {
//                Color.getHSBColor((hue + 0.75f) % 1f, 1f, 1f),
//                Color.getHSBColor((hue + 0.0f) % 1f, 1f, 1f),
//                Color.getHSBColor((hue + 0.25f) % 1f, 1f, 1f),
//                Color.getHSBColor((hue + 0.45f) % 1f, 1f, 1f)
//
//        };
//
//        float[] fractions = {0.0f, 0.33f, 0.66f, 1.0f};
//
//        LinearGradientPaint gradient = new LinearGradientPaint(
//                0, 0, width, 0, fractions, colors
//        );
//
//        g2d.setPaint(gradient);
//        g2d.setFont(getFont());
//
//        FontMetrics fm = g2d.getFontMetrics();
//        int x = (width - fm.stringWidth(getText())) / 2;
//        int y = (height - fm.getHeight()) / 2 + fm.getAscent();
//
//        g2d.drawString(getText(), x, y);
//        g2d.dispose();
//    }

//    GradientPaint  only support 2 colors
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int width = getWidth();
        int height = getHeight();

        // Tạo hai màu theo hue thay đổi
        Color color1 = Color.getHSBColor(hue, 1f, 1f);
        Color color2 = Color.getHSBColor((hue + 0.5f) % 1f, 1f, 1f);

        GradientPaint gradient = new GradientPaint(0, 0, color1, width, height, color2, true);

        g2d.setPaint(gradient);
        g2d.setFont(getFont());

        FontMetrics fm = g2d.getFontMetrics();
        int x = (width - fm.stringWidth(getText())) / 2;
        int y = (height - fm.getHeight()) / 2 + fm.getAscent();

        g2d.drawString(getText(), x, y);
        g2d.dispose();
    }
}
