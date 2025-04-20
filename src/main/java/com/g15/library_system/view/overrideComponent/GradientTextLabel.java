package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import javax.swing.*;

public class GradientTextLabel extends JLabel {
  public GradientTextLabel(String text) {
    super(text);
    setFont(new Font("Arial", Font.BOLD, 40)); // Cỡ chữ lớn cho dễ thấy
    setPreferredSize(new Dimension(400, 100));
    setHorizontalAlignment(SwingConstants.CENTER);
  }

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    // Kích thước vùng vẽ chữ
    int width = getWidth();
    int height = getHeight();

    // Gradient từ màu này sang màu kia (tùy chỉnh tùy thích)
    GradientPaint gradient = new GradientPaint(0, 0, Color.RED, width, height, Color.MAGENTA, true);

    g2d.setPaint(gradient);
    g2d.setFont(getFont());

    // Lấy FontMetrics để căn giữa chữ
    FontMetrics fm = g2d.getFontMetrics();
    int x = (width - fm.stringWidth(getText())) / 2;
    int y = (height - fm.getHeight()) / 2 + fm.getAscent();

    g2d.drawString(getText(), x, y);
    g2d.dispose();
  }

  // Không cần setForeground() vì paintComponent đã tự xử lý
}
