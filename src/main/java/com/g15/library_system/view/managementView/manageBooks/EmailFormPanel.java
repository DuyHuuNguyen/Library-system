package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.enums.ApiKey;
import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import java.util.Map;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class EmailFormPanel extends RoundedShadowPanel {
  private JTextField toField;
  private JTextField subjectField;
  private JPanel body;
  private JTextArea contentEmail;
  private ImageDropPanel bodyArea;
  private JScrollPane bodyScroll;
  private JButton sendButton,reloadBtn;

  private Map<ApiKey, Runnable> mapApi;

  public EmailFormPanel(Map<ApiKey, Runnable> mapApi) {
    this.mapApi = mapApi;
    setLayout(new MigLayout("insets 20, wrap 2", "[right]10[grow, fill]", "[]10[]10[]10[]"));
    setBackground(Color.WHITE);
    setBorder(BorderFactory.createLineBorder(Style.BLUE_HEADER_TABLE_AND_BUTTON));

    JLabel toLabel = new JLabel("To (email):");
    this.toField = new JTextField(25);

    JLabel subjectLabel = new JLabel("Subject:");
    this.subjectField = new JTextField(25);

    JLabel bodyLabel = new JLabel("Message:");
    this.body = new JPanel(new MigLayout("insets 5, wrap 1", "[grow, fill]", "[]10[]"));

    this.bodyArea = new ImageDropPanel(300, 300);

    this.contentEmail = new JTextArea();
    this.contentEmail.setRows(15);
    this.contentEmail.setLineWrap(true);
    this.contentEmail.setWrapStyleWord(true);

    this.body.add(contentEmail, "growx");
    this.body.add(bodyArea, "center, growx");

    this.bodyScroll = new JScrollPane(body);
    this.bodyScroll.setPreferredSize(new Dimension(400, 800));

    this.sendButton =
        CustomButtonBuilder.builder()
            .text("Send")
            .backgroundColor(Style.BLUE_HEADER_TABLE_AND_BUTTON)
            .preferredSize(new Dimension(120, 35));
    this.sendButton.addActionListener(e -> this.mapApi.get(ApiKey.SEND_EMAIL).run());

    this.reloadBtn =  CustomButtonBuilder.builder()
            .text("reload")
            .backgroundColor(Style.BLUE_HEADER_TABLE_AND_BUTTON)
            .preferredSize(new Dimension(120, 35));
    this.reloadBtn.addActionListener(e->this.mapApi.get(ApiKey.RELOAD).run());

    this.add(toLabel);
    this.add(toField, "growx");

    this.add(subjectLabel);
    this.add(subjectField, "growx");

    this.add(bodyLabel, "top");
    this.add(bodyScroll, "growx, growy, height 250::600");

    // Đặt hai nút vào một JPanel để chúng nằm sát nhau
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setOpaque(false);
    buttonPanel.add(reloadBtn);
    buttonPanel.add(sendButton);

    this.add(new JLabel());
    this.add(buttonPanel, "right, spanx");

    this.setPreferredSize(new Dimension(600, 500));
  }

  public void loadEmail(String[] emails) {
    if (emails == null) {
      this.toField.setText("");
      return;
    };
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < emails.length; i++) {
      sb.append(emails[i]);
      var isLastItem = (i + 1 == emails.length);
      if (!isLastItem) sb.append(", ");
    }
    this.toField.setText(sb.toString());
  }

  public void loadImages(java.util.List<String> images) {
    this.bodyArea.loadImagesFromUrls(images);
  }

  public void loadContent(String content,String subject) {
    this.subjectField.setText(subject);
    this.contentEmail.setText(content);
  }

  public void removeAllImages() {
    this.bodyArea.clearALlImages();
  }
}
