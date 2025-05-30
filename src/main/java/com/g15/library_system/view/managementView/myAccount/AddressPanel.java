package com.g15.library_system.view.managementView.myAccount;

import com.g15.library_system.view.Style;
import com.g15.library_system.view.overrideComponent.RoundedShadowPanel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import net.miginfocom.swing.MigLayout;

public class AddressPanel extends RoundedShadowPanel {

  private Map<String, String> addressData = new HashMap<>();
  private Map<String, JLabel> labels = new HashMap<>();
  private Map<String, JTextField> textFields = new HashMap<>();

  private final JPanel infoPanel;
  private final JButton editButton;
  private boolean isEditing = false;
  private Runnable saveCallback;

  public AddressPanel(Map<String, String> addressData, Runnable saveCallback) {
    this.addressData = addressData;
    this.saveCallback = saveCallback;
    this.setLayout(new BorderLayout());
    this.setBackground(UIManager.getColor("Panel.background"));

    JPanel headerPanel = new JPanel(new BorderLayout());
    headerPanel.setBorder(BorderFactory.createEmptyBorder(20, 28, 5, 25));
    headerPanel.setOpaque(false);

    JLabel title = new JLabel("Address");
    title.setFont(new Font("Arial", Font.BOLD, 16));
    title.setForeground(new Color(30, 30, 60));

    editButton = new JButton("Edit");
    editButton.addActionListener(e -> toggleEditMode());

    headerPanel.add(title, BorderLayout.WEST);
    headerPanel.add(editButton, BorderLayout.EAST);

    infoPanel =
        new JPanel(new MigLayout("wrap 2, insets 20 30 30 10, gapy 5", "[grow,fill][grow,fill]"));
    infoPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
    infoPanel.setOpaque(false);
    buildViewMode();

    this.add(headerPanel, BorderLayout.NORTH);
    this.add(infoPanel, BorderLayout.CENTER);
    this.setBackground(Color.WHITE);
  }

  private void buildViewMode() {
    infoPanel.removeAll();
    labels.clear();
    addRowView("Country", "Zip Code");
    addRowView("City", "Address");
    infoPanel.revalidate();
    infoPanel.repaint();
  }

  private void addRowView(String key1, String key2) {

    if (key1 != null) infoPanel.add(new JLabel(key1));
    if (key2 != null) infoPanel.add(new JLabel(key2), "wrap");

    if (key1 != null) {
      JLabel val1 = new JLabel(addressData.get(key1));
      val1.setPreferredSize(new Dimension(150, 25));
      val1.setFont(new Font("Arial", Font.BOLD, 13));
      val1.setForeground(new Color(50, 50, 100));
      labels.put(key1, val1);
      infoPanel.add(val1);
    }
    if (key2 != null) {
      JLabel val2 = new JLabel(addressData.get(key2));
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

    addRowEdit("Country", "Zip Code");
    addRowEdit("City", "Address");

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
      JTextField field1 = new JTextField(addressData.get(key1));
      field1.setPreferredSize(new Dimension(150, 25));
      textFields.put(key1, field1);
      infoPanel.add(field1);
    }
    if (key2 != null) {
      JTextField field2 = new JTextField(addressData.get(key2));
      textFields.put(key2, field2);
      infoPanel.add(field2, "wrap, gapbottom 15");
    } else {
      infoPanel.add(Box.createHorizontalStrut(1), "wrap");
    }
  }

  private void toggleEditMode() {
    isEditing = !isEditing;
    if (isEditing) {
      editButton.setVisible(false);
      buildEditMode();
    } else {
      editButton.setVisible(true);
      buildViewMode();
    }
  }

  private void saveChanges() {
    for (Map.Entry<String, JTextField> entry : textFields.entrySet()) {
      addressData.put(entry.getKey(), entry.getValue().getText());
    }
    toggleEditMode();
    if (saveCallback != null) { // Gọi callback sau khi lưu
      saveCallback.run();
    }
  }

  private void cancelEdit() {
    toggleEditMode();
  }

  public void refreshView() {
    buildViewMode();
  }
}
