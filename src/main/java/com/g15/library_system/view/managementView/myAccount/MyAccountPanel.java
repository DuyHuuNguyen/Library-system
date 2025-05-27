package com.g15.library_system.view.managementView.myAccount;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class MyAccountPanel extends JPanel {
  Map<String, Map<String, String>> loadedData = UserDataManager.loadUserData();
  Map<String, String> personalData = loadedData.getOrDefault("personal", new HashMap<>());
  Map<String, String> addressData = loadedData.getOrDefault("address", new HashMap<>());

  private String avatarPath = "src/main/resources/images/fit_nlu_logo.jpg"; // temp
  private PersonalInfoPanel personalInfoPanel;
  private AddressPanel addressPanel;
  private UserProfilePanel userProfilePanel;

  public MyAccountPanel() {

    JLabel title = new JLabel("My Profile");
    title.setFont(new Font("Arial", Font.BOLD, 20));

    JPanel mainPanel = new JPanel();
    mainPanel.setOpaque(false);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
    this.setLayout(new BorderLayout());

    // data
    //    this.personalData.put("First Name", "Jack 5 xu");
    //    this.personalData.put("Last Name", "Adams");
    //    this.personalData.put("Email address", "jackadams@gmail.com");
    //    this.personalData.put("Phone", "(213) 555-1234");
    //    this.personalData.put("Job", "Dev lỏ chuồng gà");
    //
    //    this.addressData.put("Country", "United States");
    //    this.addressData.put("Zip Code", "90001");
    //    this.addressData.put("City", "Los Angeles");
    //    this.addressData.put("Address", "1234 Sunset Blvd");

    personalInfoPanel = new PersonalInfoPanel(personalData, this::saveAndRefreshAllData);
    addressPanel = new AddressPanel(addressData, this::saveAndRefreshAllData);
    userProfilePanel =
        new UserProfilePanel(
            avatarPath,
            personalData,
            addressData,
            personalInfoPanel,
            addressPanel,
            this::saveAndRefreshAllData);

    mainPanel.add(userProfilePanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(personalInfoPanel);
    mainPanel.add(Box.createVerticalStrut(10));
    mainPanel.add(addressPanel);

    JScrollPane scrollPane = new JScrollPane(mainPanel);
    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setPreferredSize(new Dimension(800, 780));
    scrollPane.setBorder(null);
    this.add(scrollPane);
    Map<String, String> libraryActivity = new HashMap<>();
    libraryActivity.put("Books Currently Borrowed", "3");
    libraryActivity.put("Due Soon", "1");
    libraryActivity.put("Overdue Books", "1");
    libraryActivity.put("Total Returned", "15");

    mainPanel.add(Box.createVerticalStrut(10));
    UserDataManager.saveUserData(personalData, addressData);
  }

  public void saveAndRefreshAllData() {
    UserDataManager.saveUserData(this.personalData, this.addressData);
    // Sau khi lưu, làm mới các view để đảm bảo chúng hiển thị dữ liệu mới nhất
    personalInfoPanel.refreshView();
    addressPanel.refreshView();
    userProfilePanel.updateProfileData(
        this.personalData,
        this.addressData); // Đảm bảo phương thức này tồn tại và cập nhật các label
  }
}
