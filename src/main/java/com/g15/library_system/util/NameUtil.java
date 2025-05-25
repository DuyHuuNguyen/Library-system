package com.g15.library_system.util;

import java.util.Arrays;

public class NameUtil {
  public static String getFirstName(String fullName) {
    if (fullName == null || fullName.trim().isEmpty()) return "";
    String[] parts = fullName.trim().split("\\s+");
    return parts[parts.length - 1]; // Tên ở cuối
  }

  public static String getLastName(String fullName) {
    if (fullName == null || fullName.trim().isEmpty()) return "";
    String[] parts = fullName.trim().split("\\s+");
    if (parts.length <= 1) return ""; // Không có họ
    return String.join(" ", Arrays.copyOf(parts, parts.length - 1));
  }
}
