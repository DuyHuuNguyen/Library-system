package com.g15.library_system.view.managementView.manageBooks;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.swingComponentBuilders.CustomButtonBuilder;
import java.awt.*;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class EmailFormPanel extends JPanel {
  private JTextField toField;
  private JTextField subjectField;
  private JPanel body;
  private JTextArea contentEmail;
  private ImageDropPanel bodyArea;
  private JScrollPane bodyScroll;
  private JButton sendButton;

  public EmailFormPanel() {
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

    add(toLabel);
    add(toField, "growx");

    add(subjectLabel);
    add(subjectField, "growx");

    add(bodyLabel, "top");
    add(bodyScroll, "growx, growy, height 250::600");

    add(new JLabel());
    add(sendButton, "right, width 120!");

    this.setPreferredSize(new Dimension(600, 500));
  }
}
