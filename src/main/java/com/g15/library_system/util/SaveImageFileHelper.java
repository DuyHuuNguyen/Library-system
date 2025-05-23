package com.g15.library_system.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SaveImageFileHelper {
  public static void main(String[] args) throws IOException {
    // Đường dẫn của file nguồn (source path)
    String sourceFilePath = "E:\\backgroud.jpg";
    System.out.println(saveImage(sourceFilePath));
  }

  public static String saveImage(String path) throws IOException {
    File file = new File(path);
    BufferedImage img = ImageIO.read(file);
    String destinationFilePath = "src/main/resources/bookImages";
    File folder = new File(destinationFilePath);
    if (!folder.exists()) folder.mkdirs();

    File outputFile = new File(folder, file.getName());

    boolean ok = ImageIO.write(img, "png", outputFile);

    if (!ok) {
      throw new IOException("Không thể lưu hình ảnh vào đích: " + outputFile.getAbsolutePath());
    }

    return "/bookImages/" + file.getName();
  }
}
