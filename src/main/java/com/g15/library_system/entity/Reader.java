package com.g15.library_system.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends User {
  private ReaderType readerType;
  private LibraryCard libraryCard;
  @Builder.Default private Boolean isSubscribe = false;

  public void addLibraryCard(LibraryCard libraryCard) {
    if (this.libraryCard != null) {
      throw new IllegalStateException("This Reader already has a LibraryCard assigned.");
    }
    this.libraryCard = libraryCard;
    this.libraryCard.addOwner(this);
  }

  public boolean nameContains(String name) {
    return this.firstName.toLowerCase().contains(name.toLowerCase())
        || this.lastName.toLowerCase().contains(name.toLowerCase());
  }

  public boolean findByName(String name) {
    String[] strings = name.toLowerCase().split(" ");
    return this.firstName.toLowerCase().contains(strings[0])
        && this.lastName.toLowerCase().contains(strings[1]);
  }

  public boolean findById(String id) {
    try {
      long parsedId = Long.parseLong(id);
      return this.isSameId(parsedId);
    } catch (NumberFormatException e) {
      return false;
    }
  }

  public boolean idContains(Long readerId) {
    return super.idContains(readerId);
  }

  public void setIsSubscribe(boolean isSub) {
    this.isSubscribe = isSub;
  }
}
