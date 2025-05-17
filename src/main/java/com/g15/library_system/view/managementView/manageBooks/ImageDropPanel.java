package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ImageDropPanel extends JPanel {
  private final JPanel imagesContainer;
  private final List<String> imageUrls = new ArrayList<>();
  private int widthOfImage;
  private int heightOfImage;

  public ImageDropPanel(int widthOfImage, int heightOfImage) {
    this.heightOfImage = heightOfImage;
    this.widthOfImage = widthOfImage;

    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(400, 300));
    setBorder(BorderFactory.createTitledBorder("Drop Images Here"));
    this.setBackground(Style.BLUE_HEADER_TABLE_AND_BUTTON);

    // Use WrapLayout for auto-wrapping
    imagesContainer = new JPanel();
    imagesContainer.setLayout(new WrapLayout(FlowLayout.LEFT, 10, 10));
    imagesContainer.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    JScrollPane scrollPane = new JScrollPane(imagesContainer);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    add(scrollPane, BorderLayout.CENTER);

    new DropTarget(
        this,
        new DropTargetAdapter() {
          @Override
          public void drop(DropTargetDropEvent event) {
            try {
              event.acceptDrop(DnDConstants.ACTION_COPY);
              List<File> droppedFiles =
                  (List<File>)
                      event.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

              for (File file : droppedFiles) {
                if (isImageFile(file)) {
                  displayAndSaveImage(file);
                }
              }

              revalidate();
              repaint();
            } catch (Exception ex) {
              ex.printStackTrace();
            }
          }
        });
  }

  // Set image size dynamically and reload images
  public void setImageSize(int width, int height) {
    this.widthOfImage = width;
    this.heightOfImage = height;
    reloadImages();
  }

  private void reloadImages() {
    List<String> urls = new ArrayList<>(imageUrls);
    imagesContainer.removeAll();
    loadImagesFromUrls(urls);
    revalidate();
    repaint();
  }

  private void displayAndSaveImage(File file) throws IOException {
    BufferedImage img = ImageIO.read(file);
    Image scaled = img.getScaledInstance(widthOfImage, heightOfImage, Image.SCALE_SMOOTH);
    JLabel imgLabel = new JLabel(new ImageIcon(scaled));
    imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    imgLabel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int option =
                JOptionPane.showConfirmDialog(
                    ImageDropPanel.this,
                    "Are you sure you want to delete this image?",
                    "Confirm deletion",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
              imagesContainer.remove(imgLabel);
              imagesContainer.revalidate();
              imagesContainer.repaint();

              File outputFile = new File("src/main/resources/images", file.getName());
              if (outputFile.exists()) {
                outputFile.delete();
                System.out.println("Đã xóa ảnh: " + outputFile.getAbsolutePath());
              }
              String url = "/images/" + file.getName();
              boolean isRemove = imageUrls.remove(url);
              log.info("Remove is {} , url {} ", isRemove, url);
            }
          }
        });

    imagesContainer.add(imgLabel);

    File folder = new File("src/main/resources/bookImages");
    if (!folder.exists()) folder.mkdirs();

    log.info("name -> {}", file.getName());

    File outputFile = new File(folder, file.getName());
    ImageIO.write(img, "png", outputFile);

    String resourcePath = "src/main/resources/bookImages" + file.getName();
    imageUrls.add(resourcePath);
    log.info("Đã lưu ảnh: {} ", outputFile.getAbsolutePath());
  }

  private boolean isImageFile(File file) {
    String name = file.getName().toLowerCase();
    return name.endsWith(".png")
        || name.endsWith(".jpg")
        || name.endsWith(".jpeg")
        || name.endsWith(".bmp");
  }

  public List<String> getImageUrls() {
    return imageUrls;
  }

  /** Load images from classpath resource. Expects url in format "/images/abc.png" */
  public void loadImagesFromUrls(List<String> urls) {
    imagesContainer.removeAll();

    if (urls == null || urls.isEmpty()) {
      log.info("Image not found");
      return;
    }

    for (String path : urls) {
      if (!path.startsWith("/")) path = "/" + path;
      try (InputStream is = getClass().getResourceAsStream(path)) {
        if (is != null) {
          BufferedImage img = ImageIO.read(is);
          Image scaled = img.getScaledInstance(widthOfImage, heightOfImage, Image.SCALE_SMOOTH);
          JLabel imgLabel = new JLabel(new ImageIcon(scaled));
          imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

          String finalPath = path;
          imgLabel.addMouseListener(
              new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                  int option =
                      JOptionPane.showConfirmDialog(
                          ImageDropPanel.this,
                          "Are you sure you want to delete this image?",
                          "Confirm deletion",
                          JOptionPane.YES_NO_OPTION);
                  if (option == JOptionPane.YES_OPTION) {
                    imagesContainer.remove(imgLabel);
                    imagesContainer.revalidate();
                    imagesContainer.repaint();

                    System.out.println("Đã xóa ảnh khỏi giao diện: " + finalPath);
                    imageUrls.remove(finalPath);
                  }
                }
              });

          imagesContainer.add(imgLabel);
        } else {
          System.err.println("Không tìm thấy file resource: " + path);
        }
      } catch (IOException e) {
        System.err.println("Không thể đọc ảnh từ: " + path);
        e.printStackTrace();
      }
    }
    revalidate();
    repaint();
  }

  public void clearALlImages() {
    imagesContainer.removeAll();
    revalidate();
    repaint();
  }
}

/**
 * FlowLayout subclass that fully supports wrapping of components. Source:
 * https://tips4java.wordpress.com/2008/11/06/wrap-layout/
 */
class WrapLayout extends FlowLayout {
  public WrapLayout() {
    super();
  }

  public WrapLayout(int align) {
    super(align);
  }

  public WrapLayout(int align, int hgap, int vgap) {
    super(align, hgap, vgap);
  }

  @Override
  public Dimension preferredLayoutSize(Container target) {
    return layoutSize(target, true);
  }

  @Override
  public Dimension minimumLayoutSize(Container target) {
    Dimension minimum = layoutSize(target, false);
    minimum.width -= (getHgap() + 1);
    return minimum;
  }

  private Dimension layoutSize(Container target, boolean preferred) {
    synchronized (target.getTreeLock()) {
      int targetWidth = target.getWidth();

      if (targetWidth == 0) {
        targetWidth = Integer.MAX_VALUE;
      }

      int hgap = getHgap();
      int vgap = getVgap();
      Insets insets = target.getInsets();
      int maxWidth = targetWidth - (insets.left + insets.right + hgap * 2);

      Dimension dim = new Dimension(0, 0);
      int rowWidth = 0;
      int rowHeight = 0;

      int nmembers = target.getComponentCount();

      for (int i = 0; i < nmembers; i++) {
        Component m = target.getComponent(i);

        if (m.isVisible()) {
          Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();

          if (rowWidth + d.width > maxWidth) {
            addRow(dim, rowWidth, rowHeight);
            rowWidth = 0;
            rowHeight = 0;
          }

          if (rowWidth != 0) {
            rowWidth += hgap;
          }

          rowWidth += d.width;
          rowHeight = Math.max(rowHeight, d.height);
        }
      }
      addRow(dim, rowWidth, rowHeight);
      dim.width += insets.left + insets.right + hgap * 2;
      dim.height += insets.top + insets.bottom + vgap * 2;

      return dim;
    }
  }

  private void addRow(Dimension dim, int rowWidth, int rowHeight) {
    dim.width = Math.max(dim.width, rowWidth);

    if (dim.height > 0) {
      dim.height += getVgap();
    }

    dim.height += rowHeight;
  }
}
