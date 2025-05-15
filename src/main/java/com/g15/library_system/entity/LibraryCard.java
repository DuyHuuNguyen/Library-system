package com.g15.library_system.entity;

import com.g15.library_system.enums.LibraryCardStatus;
import java.util.List;
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
public class LibraryCard extends BaseEntity {
  private Reader owner;
  private Long expireAt;
  private List<Transaction> transactions;
  private LibraryCardStatus libraryCardStatus;
  private List<OverdueFee> overdueFees;

  public void addOwner(Reader reader) {
    this.owner = reader;
  }
}
