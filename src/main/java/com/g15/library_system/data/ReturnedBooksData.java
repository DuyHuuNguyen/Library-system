package com.g15.library_system.data;

@Deprecated
public class ReturnedBooksData {
  private static final ReturnedBooksData instance = new ReturnedBooksData();

  private ReturnedBooksData() {}

  public static ReturnedBooksData getInstance() {
    return instance;
  }

  private void initializeData() {}
}
