package com.g15.library_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Reader extends User {
  private ReaderType readerType;
  private LibraryCard libraryCard;

  public void addLibraryCard(LibraryCard libraryCard) {
    this.libraryCard = libraryCard;
    //    this.libraryCard.addOwner(this);
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
}
