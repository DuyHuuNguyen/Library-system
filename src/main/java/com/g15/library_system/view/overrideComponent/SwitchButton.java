package com.g15.library_system.view.overrideComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwitchButton extends JToggleButton {
    private int circleX;
    private final int padding = 4;
    private final Timer timer;
    private boolean movingRight = false;

    public SwitchButton() {
        setPreferredSize(new Dimension(60, 30));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);

        circleX = padding;

        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int targetX = isSelected() ? getWidth() - getHeight() + padding : padding;
                if (movingRight) {
                    if (circleX < targetX) {
                        circleX += 2;
                        repaint();
                    } else {
                        circleX = targetX;
                        timer.stop();
                    }
                } else {
                    if (circleX > targetX) {
                        circleX -= 2;
                        repaint();
                    } else {
                        circleX = targetX;
                        timer.stop();
                    }
                }
            }
        });

        addActionListener(e -> {
            movingRight = isSelected();
            timer.start();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (isSelected()) {
            g2.setColor(new Color(0, 200, 83));
        } else {
            g2.setColor(Color.GRAY);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight());

        g2.setColor(Color.WHITE);
        int diameter = getHeight() - padding * 2;
        g2.fillOval(circleX, padding, diameter, diameter);

        g2.dispose();
    }
}
