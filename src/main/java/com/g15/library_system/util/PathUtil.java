package com.g15.library_system.util;

public class PathUtil {
  public static String convertFullPathToRelativePath(String fullPath) {
    fullPath = fullPath.replace("\\", "/");
    int index = fullPath.indexOf("/images");
    String relativePath = index != -1 ? fullPath.substring(index) : fullPath;

    return relativePath; // Kết quả: /images/abc.png
  }
}
