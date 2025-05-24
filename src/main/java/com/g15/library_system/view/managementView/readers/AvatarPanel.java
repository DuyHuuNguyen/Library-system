package com.g15.library_system.view.managementView.readers;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.File;
import java.util.List;
import javax.swing.*;

public class AvatarPanel extends JPanel {
  private JPanel uploadPanel;
  private JLabel uploadStatusLabel;
  private File selectedFile;

  public AvatarPanel() {
    setLayout(new BorderLayout());
    setPreferredSize(new Dimension(200, 120));
    setBackground(Color.WHITE);

    //        // Create a circular border with dashed line
    //        JPanel circlePanel =
    //            new JPanel() {
    //              @Override
    //              protected void paintComponent(Graphics g) {
    //                super.paintComponent(g);
    //                Graphics2D g2d = (Graphics2D) g.create();
    //                g2d.setRenderingHint(
    //                    RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //
    //                // Draw dashed circle
    //                g2d.setColor(new Color(180, 180, 180));
    //                float[] dash = {5.0f};
    //                g2d.setStroke(
    //                    new BasicStroke(
    //                        1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
    // 0.0f));
    //
    //                int diameter = Math.min(getWidth(), getHeight());
    //                int x = (getWidth() - diameter) / 2;
    //                int y = (getHeight() - diameter) / 2;
    //
    //                g2d.drawOval(x, y, diameter, diameter);
    //                g2d.dispose();
    //              }
    //
    //              @Override
    //              public Dimension getPreferredSize() {
    //                return new Dimension(100, 100);
    //              }
    //            };
    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.add(new CircularImage("src/main/resources/images/fit_nlu_logo.jpg"));
    add(panel, BorderLayout.CENTER);

    //        // Upload icon
    //        JPanel iconPanel = new JPanel(new GridLayout(2,1));
    //        iconPanel.setOpaque(false);
    //
    //        // Arrow up icon
    //        JLabel iconLabel = new JLabel("\u2191");
    //        iconLabel.setFont(new Font("Arial", Font.PLAIN, 24));
    //        iconLabel.setForeground(new Color(100, 100, 100));
    //        iconPanel.add(iconLabel);
    //
    //        // Text
    //        JLabel textLabel =
    //            new JLabel(
    //                "<html><center>Drop files to upload<br>or <font
    // color='blue'>browse</font></center></html>");
    //        textLabel.setHorizontalAlignment(JLabel.CENTER);
    //        textLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    //        iconPanel.add(textLabel);
    //
    //        circlePanel.add(iconPanel, BorderLayout.CENTER);
    //
    //        // Add circle panel to the center
    //        add(circlePanel, BorderLayout.CENTER);
    //
    //        // Status label
    //        uploadStatusLabel = new JLabel("No file selected");
    //        uploadStatusLabel.setHorizontalAlignment(JLabel.CENTER);
    //        uploadStatusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
    //        add(uploadStatusLabel, BorderLayout.SOUTH);
    //
    //          setPreferredSize(new Dimension(200, 200));
    //          setOpaque(false); // để nền trong suốt nếu cần
    //
    //          JLabel label = new JLabel("<html><center><span style='font-size:12px'>⬆<br>Drop
    // files to upload<br><a href='#'>or browse</a></span></center></html>", SwingConstants.CENTER);
    //          label.setForeground(new Color(70, 70, 70));
    //          setLayout(new BorderLayout());
    //          add(label, BorderLayout.CENTER);
    //
    //          // Drag and drop handler
    //          new DropTarget(this, new FileDropHandler());
    //
    //        // Add mouse listener for the browse functionality
    //        circlePanel.addMouseListener(
    //            new MouseAdapter() {
    //              @Override
    //              public void mouseClicked(MouseEvent e) {
    //                JFileChooser fileChooser = new JFileChooser();
    //                fileChooser.setDialogTitle("Select a file to upload");
    //                fileChooser.setFileFilter(
    //                    new FileNameExtensionFilter("Image Files", "jpg", "png", "gif", "jpeg"));
    //
    //                int result = fileChooser.showOpenDialog(AvatarPanel.this);
    //                if (result == JFileChooser.APPROVE_OPTION) {
    //                  selectedFile = fileChooser.getSelectedFile();
    //                  uploadStatusLabel.setText("Selected: " + selectedFile.getName());
    //                }
    //              }
    //
    //              @Override
    //              public void mouseEntered(MouseEvent e) {
    //                setCursor(new Cursor(Cursor.HAND_CURSOR));
    //              }
    //
    //              @Override
    //              public void mouseExited(MouseEvent e) {
    //                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    //              }
    //            });
  }

  public void resetAvatar() {
    uploadStatusLabel.setText("No file selected");
    selectedFile = null;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // Vẽ đường tròn nét đứt
    Graphics2D g2 = (Graphics2D) g;
    int padding = 10;
    int diameter = Math.min(getWidth(), getHeight()) - padding * 2;

    Stroke dashed =
        new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0);
    g2.setStroke(dashed);
    g2.setColor(Color.LIGHT_GRAY);
    g2.drawOval((getWidth() - diameter) / 2, (getHeight() - diameter) / 2, diameter, diameter);
  }

  // Lớp xử lý drag-and-drop file
  private static class FileDropHandler extends DropTargetAdapter {
    @Override
    public void drop(DropTargetDropEvent dtde) {
      try {
        dtde.acceptDrop(DnDConstants.ACTION_COPY);
        Transferable t = dtde.getTransferable();
        List<File> droppedFiles = (List<File>) t.getTransferData(DataFlavor.javaFileListFlavor);

        for (File file : droppedFiles) {
          System.out.println("File dropped: " + file.getAbsolutePath());
          // xử lý file ở đây
        }
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }
}
