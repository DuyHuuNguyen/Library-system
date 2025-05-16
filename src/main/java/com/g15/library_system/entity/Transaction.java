package com.g15.library_system.entity;

import com.g15.library_system.enums.TransactionType;
import java.util.Map;
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
public class Transaction extends BaseEntity {

  private Map<Book, Integer> books;
  private User librarian;
  @ToString.Exclude private LibraryCard libraryCard;
  private Long expectedReturnAt;
  private Long actualReturnAt;
  private String description;
  private TransactionType transactionType;
  private OverdueFee overdueFee;

  public void addLibrarycard(LibraryCard card) {
    this.libraryCard = card;
  }

  @ToString.Include(name = "libraryCardId")
  public Long getLibraryCardId() {
    return this.libraryCard != null ? this.libraryCard.getId() : null;
  }
}
