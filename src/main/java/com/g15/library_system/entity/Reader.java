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
}
