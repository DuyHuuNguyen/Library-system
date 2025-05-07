package com.g15.library_system.view.managementView.myAccount;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class MyAccountPanel extends JPanel {
  private Map<String, String> personalData = new HashMap<>();
  private Map<String, String> addressData = new HashMap<>();

  private String avatarPath = "src/main/resources/images/fit_nlu_logo.jpg"; // temp

  public MyAccountPanel() {

    JLabel title = new JLabel("My Profile");
    title.setFont(new Font("Arial", Font.BOLD, 20));

    JPanel mainPanel = new JPanel();
    mainPanel.setOpaque(false);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // data
    this.personalData.put("First Name", "Jack 5 xu");
    this.personalData.put("Last Name", "Adams");
    this.personalData.put("Email address", "jackadams@gmail.com");
    this.personalData.put("Phone", "(213) 555-1234");
    this.personalData.put("Job", "Dev lỏ chuồng gà");

    this.addressData.put("Country", "United States");
    this.addressData.put("Zip Code", "90001");
    this.addressData.put("City", "Los Angeles");
    this.addressData.put("Address", "1234 Sunset Blvd");

    mainPanel.add(new UserProfilePanel(avatarPath, personalData, addressData));
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(new PersonalInfoPanel(personalData));
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(new AddressPanel(addressData));

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(800, 780));
    scrollPane.setBorder(null);
    this.add(scrollPane);
  }
}
