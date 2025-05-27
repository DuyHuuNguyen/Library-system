package com.g15.library_system.view.managementView.myAccount;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UserDataManager {
  private static final String FILE_NAME = "userdata.properties";

  // Lưu cả hai loại dữ liệu
  public static void saveUserData(
      Map<String, String> personalData, Map<String, String> addressData) {
    try (FileOutputStream out = new FileOutputStream(FILE_NAME)) {
      Properties props = new Properties();

      // Ghi personalData với prefix "personal."
      for (Map.Entry<String, String> entry : personalData.entrySet()) {
        props.setProperty("personal." + entry.getKey(), entry.getValue());
      }

      // Ghi addressData với prefix "address."
      for (Map.Entry<String, String> entry : addressData.entrySet()) {
        props.setProperty("address." + entry.getKey(), entry.getValue());
      }

      props.store(out, "User Personal and Address Data");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Đọc dữ liệu đã lưu và tách thành hai map
  public static Map<String, Map<String, String>> loadUserData() {
    Map<String, String> personalData = new HashMap<>();
    Map<String, String> addressData = new HashMap<>();

    File file = new File(FILE_NAME);
    if (file.exists()) {
      try (FileInputStream in = new FileInputStream(file)) {
        Properties props = new Properties();
        props.load(in);

        for (String key : props.stringPropertyNames()) {
          if (key.startsWith("personal.")) {
            personalData.put(key.substring("personal.".length()), props.getProperty(key));
          } else if (key.startsWith("address.")) {
            addressData.put(key.substring("address.".length()), props.getProperty(key));
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    Map<String, Map<String, String>> result = new HashMap<>();
    result.put("personal", personalData);
    result.put("address", addressData);
    return result;
  }
}
