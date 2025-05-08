package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageDropPanel extends JPanel {
  private static final Logger log = LoggerFactory.getLogger(ImageDropPanel.class);
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

    imagesContainer = new JPanel();
    imagesContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
    imagesContainer.setBackground(Style.LIGHT_WHITE_BACKGROUND);

    JScrollPane scrollPane = new JScrollPane(imagesContainer);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
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

  private void displayAndSaveImage(File file) throws IOException {
    BufferedImage img = ImageIO.read(file);
    Image scaled = img.getScaledInstance(widthOfImage, heightOfImage, Image.SCALE_SMOOTH);
    JLabel imgLabel = new JLabel(new ImageIcon(scaled));
    imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

    // Sự kiện click vào ảnh để xóa
    imgLabel.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int option =
                JOptionPane.showConfirmDialog(
                    ImageDropPanel.this,
                    "Bạn có chắc muốn xóa hình này?",
                    "Xác nhận xóa",
                    JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
              imagesContainer.remove(imgLabel);
              imagesContainer.revalidate();
              imagesContainer.repaint();

              // Xóa ảnh khỏi ổ đĩa
              File outputFile = new File("images", file.getName());
              if (outputFile.exists()) {
                outputFile.delete();
                System.out.println("Đã xóa ảnh: " + outputFile.getAbsolutePath());
              }

              imageUrls.remove(new File("images", file.getName()).getAbsolutePath());
            }
          }
        });

    imagesContainer.add(imgLabel);

    File folder = new File("src/main/resources/bookImages");
    if (!folder.exists()) folder.mkdir();

    File outputFile = new File(folder, file.getName());
    ImageIO.write(img, "png", outputFile);

    imageUrls.add(outputFile.getAbsolutePath());
    System.out.println("Đã lưu ảnh: " + outputFile.getAbsolutePath());
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

  public void loadImagesFromUrls(List<String> urls) {
    imagesContainer.removeAll();

    if (urls == null || urls.isEmpty()) {
      log.info("Image not found");
      return;
    }

    for (String path : urls) {
      File file = new File(path);
      if (file.exists() && isImageFile(file)) {
        try {
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
                          "Bạn có chắc muốn xóa hình này?",
                          "Xác nhận xóa",
                          JOptionPane.YES_NO_OPTION);
                  if (option == JOptionPane.YES_OPTION) {
                    imagesContainer.remove(imgLabel);
                    imagesContainer.revalidate();
                    imagesContainer.repaint();

                    if (file.exists()) {
                      file.delete();
                      System.out.println("Đã xóa ảnh: " + file.getAbsolutePath());
                    }

                    imageUrls.remove(file.getAbsolutePath());
                  }
                }
              });

          imagesContainer.add(imgLabel);
        } catch (IOException e) {
          System.err.println("Không thể đọc ảnh từ: " + path);
          e.printStackTrace();
        }
      }
    }

    revalidate();
    repaint();
  }
}
