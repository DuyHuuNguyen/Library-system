package com.g15.library_system.data;

import com.g15.library_system.entity.Librarian;
import lombok.Getter;

public class CacheData {
  @Getter private static Librarian CURRENT_LIBRARIAN;
  private static String OTP;
  private static String EMAIL;

  public static void addOTP(String otp) {
    OTP = otp;
  }

  public static void addCurrentLibrarian(Librarian librarian) {
    CURRENT_LIBRARIAN = librarian;
  }

  public static void addEmail(String email) {
    EMAIL = email;
  }

  public static boolean isEmailEmpty() {
    return EMAIL == "" || EMAIL == null;
  }

  public static String getEMAIL() {
    return EMAIL;
  }

  public static String getOTP() {
    return OTP;
  }
}
