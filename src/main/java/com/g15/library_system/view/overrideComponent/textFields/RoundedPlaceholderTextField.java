package com.g15.library_system.view.overrideComponent.textFields;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import lombok.Setter;

@Setter
public class RoundedPlaceholderTextField extends JTextField {
  private String placeholder;
  private Color borderColor = Color.PINK;
  private Font font = new Font("Arial", Font.PLAIN, 12);
  private int radius = 10;

  public RoundedPlaceholderTextField() {
    setOpaque(false);
    setBorder(new RoundedBorder(radius, Color.GRAY));
    setFont(font);
    setMargin(new Insets(5, 10, 5, 10));

    addFocusListener(
        new FocusAdapter() {
          @Override
          public void focusGained(FocusEvent e) {
            setBorder(new RoundedBorder(10, borderColor));
            repaint();
          }

          @Override
          public void focusLost(FocusEvent e) {
            setBorder(new RoundedBorder(10, Color.GRAY));
            repaint();
          }
        });
  }

  @Override
  public Insets getInsets() {
    return new Insets(8, 14, 8, 14);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    if (getText().isEmpty() && !isFocusOwner()) {
      Graphics2D g2 = (Graphics2D) g.create();
      g2.setColor(Color.LIGHT_GRAY);
      g2.setFont(getFont().deriveFont(Font.ITALIC));
      g2.drawString(placeholder, getInsets().left, getHeight() / 2 + getFont().getSize() / 2 - 4);
      g2.dispose();
    }
  }

  private static class RoundedBorder extends AbstractBorder {
    private int radius;
    private Color color;

    public RoundedBorder(int radius, Color color) {
      this.radius = radius;
      this.color = color;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
      Graphics2D g2 = (Graphics2D) g.create();

      // Bật anti-aliasing để đường vẽ mượt mà
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      // Viền bo tròn
      g2.setColor(color);
      g2.setStroke(new BasicStroke(2));
      g2.drawRoundRect(x + 1, y + 1, width - 3, height - 3, radius, radius);

      g2.dispose();
    }
  }
}
