package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import lombok.Getter;

public class AvatarPanel extends JPanel {
  private int width = 150, height = 150;
  private Image image;
  @Getter private String imageUrl;

  public AvatarPanel(String imageUrl, boolean isAbsolute) {
    // Load ảnh từ resource hoặc đường dẫn hệ thống
    //    this.image = new ImageIcon(getClass().getResource(this.imageUrl)).getImage();
    setPreferredSize(new Dimension(120, 120));
    setOpaque(false);
    if (isAbsolute) {
      this.setImageUrlAbsolute(imageUrl);
    } else {
      this.setImageUrlRelative(imageUrl);
    }

    // Thêm sự kiện click chuột
    addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            chooseImageFromFileSystem();
          }
        });
  }

  private void chooseImageFromFileSystem() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Chọn ảnh đại diện");
    fileChooser.setFileFilter(new FileNameExtensionFilter("Ảnh", "jpg", "jpeg", "png", "gif"));

    int result = fileChooser.showOpenDialog(this);
    if (result == JFileChooser.APPROVE_OPTION) {
      File selectedFile = fileChooser.getSelectedFile();
      setImageFromFile(selectedFile);
    }
  }

  public void setImageFromFile(File file) {
    if (file != null && file.exists()) {
      imageUrl = file.getAbsolutePath();
      ImageIcon icon = new ImageIcon(file.getAbsolutePath());
      this.image = icon.getImage();
      setSize(150, 150);
      revalidate();
      repaint();

      // Đảm bảo container cha cũng được vẽ lại
      Window window = SwingUtilities.getWindowAncestor(this);
      if (window != null) {
        window.repaint();
      }
    }
  }

  @Override
  public void setSize(int width, int height) {
    super.setSize(width, height);
    this.width = width;
    this.height = height;

    repaint(); // vẽ lại ảnh
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Tạo đồ họa 2D
    Graphics2D g2 = (Graphics2D) g.create();

    // Làm mượt
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Cắt hình ảnh thành hình tròn
    Shape clip = new Ellipse2D.Float(0, 0, width, height);
    g2.setClip(clip);

    // Vẽ ảnh vừa với kích thước panel
    g2.drawImage(image, 0, 0, width, height, this);

    g2.dispose();
  }

  public void setImageUrlAbsolute(String imageUrlAbsolute) {
    try {
      this.imageUrl = imageUrlAbsolute;
      URL url = new URL(imageUrlAbsolute);
      ImageIcon icon = new ImageIcon(url);
      this.image = icon.getImage();
      repaint();
    } catch (MalformedURLException e) {
      e.printStackTrace(); // Hoặc xử lý khác
    }

    // Đảm bảo container cha cũng được vẽ lại
    Window window = SwingUtilities.getWindowAncestor(this);
    if (window != null) {
      window.repaint();
    }
  }

  public void setImageUrlRelative(String imageUrlRelative) {
    this.imageUrl = imageUrlRelative;
    ImageIcon icon = new ImageIcon(getClass().getResource(imageUrlRelative));
    this.image = icon.getImage();
    repaint();

    // Đảm bảo container cha cũng được vẽ lại
    Window window = SwingUtilities.getWindowAncestor(this);
    if (window != null) {
      window.repaint();
    }
  }
}
