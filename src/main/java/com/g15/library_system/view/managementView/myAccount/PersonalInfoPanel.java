package com.g15.library_system.view.managementView.myAccount;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class PersonalInfoPanel extends RoundedShadowPanel {

  private final JPanel infoPanel;
  private final JButton editButton;
  private boolean isEditMode = false;
  private Map<String, String> personalData;
  private final Map<String, JLabel> labels = new LinkedHashMap<>();
  private final Map<String, JTextField> textFields = new LinkedHashMap<>();

  public PersonalInfoPanel(Map<String, String> personalData, Runnable saveCallback) {
    this.personalData = personalData;
    this.saveCallback = saveCallback;
    //    loadUserData(); //  Tải dữ liệu từ file nếu có

    this.setLayout(new BorderLayout());
    this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 5, 20));
    headerPanel.setOpaque(false);

    JLabel title = new JLabel("Personal information");
    title.setFont(new Font("Arial", Font.BOLD, 16));
    title.setForeground(new Color(30, 30, 60));

    editButton = new JButton("Edit");
    editButton.addActionListener(this::toggleEditMode);

    headerPanel.add(title, BorderLayout.WEST);
    headerPanel.add(editButton, BorderLayout.EAST);

    infoPanel = new JPanel(new MigLayout("wrap 2, insets 20 30 30 10", "[grow,fill][grow,fill]"));
    infoPanel.setOpaque(false);
    buildViewMode();

    this.add(headerPanel, BorderLayout.NORTH);
    this.add(infoPanel, BorderLayout.CENTER);
    this.setBackground(Color.WHITE);
  }

  private void buildViewMode() {
    infoPanel.removeAll();
    labels.clear();
    addRowView("First Name", "Last Name");
    addRowView("Email address", "Phone");
    addRowView("Job", null);
    infoPanel.revalidate();
    infoPanel.repaint();
  }

  private void addRowView(String key1, String key2) {
    if (key1 != null) infoPanel.add(new JLabel(key1));
    if (key2 != null) infoPanel.add(new JLabel(key2), "wrap");

    if (key1 != null) {
      JLabel val1 = new JLabel(personalData.getOrDefault(key1, ""));
      val1.setFont(new Font("Arial", Font.BOLD, 13));
      val1.setForeground(new Color(50, 50, 100));
      labels.put(key1, val1);
      infoPanel.add(val1);
    }
    if (key2 != null) {
      JLabel val2 = new JLabel(personalData.getOrDefault(key2, ""));
      val2.setFont(new Font("Arial", Font.BOLD, 13));
      val2.setForeground(new Color(50, 50, 100));
      labels.put(key2, val2);
      infoPanel.add(val2, "wrap, gapbottom 15");
    } else {
      infoPanel.add(Box.createHorizontalStrut(1), "wrap");
    }
  }

  private void buildEditMode() {
    infoPanel.removeAll();
    textFields.clear();

    addRowEdit("First Name", "Last Name");
    addRowEdit("Email address", "Phone");
    addRowEdit("Job", null);

    JButton saveBtn = new JButton("Save");
    saveBtn.setBackground(Style.GREEN_CONFIRM_BUTTON_COLOR);
    saveBtn.setForeground(Color.WHITE);
    JButton cancelBtn = new JButton("Cancel");

    saveBtn.addActionListener(e -> saveChanges());
    cancelBtn.addActionListener(e -> cancelEdit());

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setOpaque(false);
    buttonPanel.add(saveBtn);
    buttonPanel.add(cancelBtn);

    infoPanel.add(buttonPanel, "span 2, right");

    infoPanel.revalidate();
    infoPanel.repaint();
  }

  private void addRowEdit(String key1, String key2) {
    if (key1 != null) infoPanel.add(new JLabel(key1));
    if (key2 != null) infoPanel.add(new JLabel(key2), "wrap");

    if (key1 != null) {
      JTextField field1 = new JTextField(personalData.getOrDefault(key1, ""));
      textFields.put(key1, field1);
      infoPanel.add(field1);
    }
    if (key2 != null) {
      JTextField field2 = new JTextField(personalData.getOrDefault(key2, ""));
      textFields.put(key2, field2);
      infoPanel.add(field2, "wrap, gapbottom 15");
    } else {
      infoPanel.add(Box.createHorizontalStrut(1), "wrap");
    }
  }

  private void toggleEditMode(ActionEvent e) {
    isEditMode = true;
    editButton.setVisible(false);
    buildEditMode();
  }

  private void saveChanges() {
    for (Map.Entry<String, JTextField> entry : textFields.entrySet()) {
      personalData.put(entry.getKey(), entry.getValue().getText());
    }
    // KHÔNG CÒN GỌI saveUserData() trực tiếp ở đây nữa
    isEditMode = false;
    editButton.setVisible(true);
    // buildViewMode(); // Việc này sẽ được gọi bởi lệnh refresh từ MyAccountPanel
    if (saveCallback != null) {
      saveCallback.run(); // Kích hoạt lệnh lưu trong MyAccountPanel
    }
  }

  private void cancelEdit() {
    isEditMode = false;
    editButton.setVisible(true);
    buildViewMode();
  }

  public void refreshView() {
    buildViewMode();
  }

  // Ghi dữ liệu xuống file
  private void saveUserData() {
    try {
      Properties props = new Properties();
      props.putAll(personalData);
      props.store(new FileOutputStream("userdata.properties"), "User personal information");
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  //  Nạp dữ liệu từ file
  private void loadUserData() {
    File file = new File("userdata.properties");
    if (file.exists()) {
      try (InputStream input = new FileInputStream(file)) {
        Properties props = new Properties();
        props.load(input);
        for (String key : props.stringPropertyNames()) {
          personalData.put(key, props.getProperty(key));
        }
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  private Runnable saveCallback; // Thêm trường này
}
