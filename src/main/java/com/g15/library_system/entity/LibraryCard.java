package com.g15.library_system.entity;

import com.g15.library_system.enums.LibraryCardStatus;
import java.util.ArrayList;
import java.util.List;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard extends BaseEntity {
  @ToString.Exclude private Reader owner;
  private Long expireAt;
  @Builder.Default private List<Transaction> transactions = new ArrayList<>();
  private LibraryCardStatus libraryCardStatus;

  public void addOwner(Reader reader) {
    this.owner = reader;
  }

  public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
    transaction.addLibrarycard(this);
  }

  public void addTransactions(List<Transaction> transactions) {
    transactions.forEach(transaction -> this.addTransaction(transaction));
  }

  @ToString.Include(name = "userId")
  public Long getUserId() {
    return this.owner != null ? this.owner.getId() : null;
  }
}
