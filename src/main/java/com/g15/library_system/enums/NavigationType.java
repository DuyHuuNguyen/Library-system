package com.g15.library_system.enums;

public enum NavigationType {
  DASHBOARD("Dashboard"),
  MANAGE_BOOKS("ManageBooks"),
  LENDED_BOOKS("LendedBooks"),
  RETURN_BOOKS("ReturnBooks"),
  OVERDUE_BOOKS("overdueBooks"),
  READERS("Readers"),
  LIBRARIANS("Librarians"),
  SETTINGS("Settings"),
  MY_ACCOUNT("MyAccount"),
  NOTIFICATION("Notification"),
  LOGOUT("logout");

  private final String cardName;

  NavigationType(String cardName) {
    this.cardName = cardName;
  }

  public String getCardName() {
    return cardName;
  }
}
