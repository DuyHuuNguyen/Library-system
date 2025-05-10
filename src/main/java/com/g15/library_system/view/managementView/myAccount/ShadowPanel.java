package com.g15.library_system.view.managementView.myAccount;

import java.awt.*;
import javax.swing.*;

public class ShadowPanel extends JPanel {

  private int shadowSize = 10;
  private Color shadowColor = new Color(0, 0, 0, 80);

  public ShadowPanel() {
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g.create();

    int x = shadowSize;
    int y = shadowSize;
    int width = getWidth() - shadowSize * 2;
    int height = getHeight() - shadowSize * 2;

    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setColor(shadowColor);
    g2.fillRoundRect(x, y, width, height, 20, 20);

    g2.setColor(getBackground());
    g2.fillRoundRect(x - shadowSize / 2, y - shadowSize / 2, width, height, 20, 20);

    g2.dispose();
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame("Shadow Panel Demo");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 300);
    frame.setLayout(null); // Dùng layout null để dễ thấy bóng

    ShadowPanel panel = new ShadowPanel();
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
