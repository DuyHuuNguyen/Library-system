package com.g15.library_system.data;

import com.g15.library_system.entity.Librarian;

public class CacheData {
  private static Librarian CURRENT_LIBRARIAN;
  private static String OTP;

  public static void addOTP(String otp) {
    OTP = otp;
  }

  public static void addCurrentLibrarian(Librarian librarian) {
    CURRENT_LIBRARIAN = librarian;
  }
}
