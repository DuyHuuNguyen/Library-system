package com.g15.library_system.view.overrideComponent;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

@Deprecated
public class ImageDropPanel extends JPanel {
  private final JPanel imagesContainer;
  private final List<String> imageUrls = new ArrayList<>();

  public ImageDropPanel() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(400, 300));
    setBorder(BorderFactory.createTitledBorder("Drop Images Here"));

    imagesContainer = new JPanel();
    imagesContainer.setLayout(new FlowLayout(FlowLayout.LEFT));
    JScrollPane scrollPane = new JScrollPane(imagesContainer);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    add(scrollPane, BorderLayout.CENTER);

    // Thiết lập drag & drop
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
    Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    JLabel imgLabel = new JLabel(new ImageIcon(scaled));
    imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    imagesContainer.add(imgLabel);

    // Lưu vào thư mục images
    File folder = new File("images");
    if (!folder.exists()) folder.mkdir();

    File outputFile = new File(folder, file.getName());
    ImageIO.write(img, "png", outputFile);

    imageUrls.add(outputFile.getAbsolutePath());
    System.out.println("Saved image: " + outputFile.getAbsolutePath());
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
}
