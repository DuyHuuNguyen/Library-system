package com.g15.library_system.view.overrideComponent.passwdFields;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PasswordFieldWithEye extends JPasswordField {
  private boolean showPassword = false;
  private Image eyeOpen;
  private Image eyeClosed;
  private final int iconSize = 16;
  private final int iconPadding = 8;
  private final int arcSize = 12;

  public PasswordFieldWithEye() {
    setOpaque(false);
    setBorder(new EmptyBorder(9, 12, 9, iconSize + iconPadding * 2 + 5));
    setEchoChar('•');
    setFont(new Font("SansSerif", Font.PLAIN, 16));
    setForeground(Color.BLACK);
    //        setCaretColor(Color.BLACK);
    //        setBackground(Color.GRAY);

    eyeOpen = new ImageIcon("src/swing/visibility.png").getImage();
    eyeClosed = new ImageIcon("src/swing/visibility_off.png").getImage();

    addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int width = getWidth();
            if (x >= width - iconSize - iconPadding && x <= width - iconPadding) {
              togglePasswordVisibility();
            }
          }
        });

    addFocusListener(
        new java.awt.event.FocusAdapter() {
          public void focusGained(java.awt.event.FocusEvent evt) {
            repaint();
          }

          public void focusLost(java.awt.event.FocusEvent evt) {
            repaint();
          }
        });
  }

  private void togglePasswordVisibility() {
    showPassword = !showPassword;
    setEchoChar(showPassword ? (char) 0 : '•');
    repaint();
  }

  @Override
  protected void paintComponent(Graphics g) {

    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(Color.WHITE);
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcSize, arcSize);

    int y = (getHeight() - iconSize) / 2;
    int x = getWidth() - iconSize - iconPadding;
    g2.drawImage(showPassword ? eyeOpen : eyeClosed, x, y, iconSize, iconSize, this);

    g2.dispose();
    super.paintComponent(g);
  }

  @Override
  protected void paintBorder(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    if (isFocusOwner()) {
      g2.setColor(new Color(0, 120, 215));
    } else {
      g2.setColor(Color.GRAY);
    }
    g2.setStroke(new BasicStroke(1.8f));
    g2.drawRoundRect(1, 0, getWidth() - 2, getHeight() - 2, arcSize, arcSize);

    g2.dispose();
  }
}
