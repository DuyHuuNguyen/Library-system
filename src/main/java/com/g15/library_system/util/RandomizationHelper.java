package com.g15.library_system.util;

import java.util.Random;

public class RandomizationHelper {
  public static String generaRandomCode() {
    Random random = new Random();
    int code = random.nextInt(9999);
    return String.format("%04d", code);
  }
}
