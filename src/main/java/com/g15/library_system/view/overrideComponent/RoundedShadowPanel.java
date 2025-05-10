package com.g15.library_system.view.overrideComponent;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedShadowPanel extends JPanel {

  private int radius;
  private Color backgroundColor;
  private Color shadowColor;
  private int shadowSize;
  private int shadowOffset;

  public RoundedShadowPanel() {
    this(20, Color.WHITE, new Color(0, 0, 0, 30), 5, 4);
  }

  public RoundedShadowPanel(
      int radius, Color backgroundColor, Color shadowColor, int shadowSize, int shadowOffset) {
    this.radius = radius;
    this.backgroundColor = backgroundColor;
    this.shadowColor = shadowColor;
    this.shadowSize = shadowSize;
    this.shadowOffset = shadowOffset;
    setOpaque(false);
    setBorder(new EmptyBorder(15, 15, 15, 15));
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    int width = getWidth();
    int height = getHeight();

    g2.setColor(shadowColor);
    g2.fillRoundRect(
        shadowOffset, shadowOffset, width - shadowSize, height - shadowSize, radius, radius);

    g2.setColor(backgroundColor);
    g2.fillRoundRect(0, 0, width - shadowSize, height - shadowSize, radius, radius);

    g2.dispose();
    super.paintComponent(g);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Shadow Panel Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setLayout(null); // Dùng layout null để dễ thấy bóng

    RoundedShadowPanel panel = new RoundedShadowPanel();
    panel.setBackground(new Color(70, 130, 180)); // SteelBlue
    panel.setBounds(50, 50, 300, 150); // Vị trí cố định để bóng hiển thị rõ

    JLabel label = new JLabel("Panel có đổ bóng", SwingConstants.CENTER);
    label.setForeground(Color.WHITE);
    label.setFont(new Font("Arial", Font.BOLD, 18));
    panel.setLayout(new BorderLayout());
    panel.add(label, BorderLayout.CENTER);

    frame.add(panel);
    frame.setVisible(true);
  }
}
