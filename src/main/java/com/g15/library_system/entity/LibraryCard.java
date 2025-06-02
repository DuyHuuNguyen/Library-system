package com.g15.library_system.entity;

import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
  @Builder.Default private List<Transaction> borrowTransactions = new ArrayList<>();
  @Builder.Default private List<Transaction> returnTransactions = new ArrayList<>();
  private LibraryCardStatus libraryCardStatus;

  public void addOwner(Reader reader) {
    this.owner = reader;
  }

  public void addBorrowTransaction(Transaction transaction) {
    this.borrowTransactions.add(transaction);
    transaction.addLibrarycard(this);
  }

  public void addBorrowTransactions(List<Transaction> transactions) {
    transactions.forEach(transaction -> this.addBorrowTransaction(transaction));
  }

  public void addReturnTransaction(Transaction transaction) {
    this.returnTransactions.add(transaction);
    transaction.addLibrarycard(this);
  }

  public void addReturnTransactions(List<Transaction> transactions) {
    transactions.forEach(transaction -> this.addReturnTransaction(transaction));
  }

  @ToString.Include(name = "userId")
  public Long getUserId() {
    return this.owner != null ? this.owner.getId() : null;
  }

  public int getTotalReturnedBooks() {
    return returnTransactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.RETURNED)
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public int getTotalBorrowedBooks() {
    return borrowTransactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.BORROW)
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public int getTotalOverdueBooks() {
    return borrowTransactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.BORROW)
        .filter(t -> t.getActualReturnAt() != null && t.getExpectedReturnAt() != null)
        .filter(t -> t.getActualReturnAt() > t.getExpectedReturnAt())
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public Map<Book, Integer> getUnreturnedBooks() {
    Map<Book, Integer> totalBorrowed = new HashMap<>();
    Map<Book, Integer> totalReturned = new HashMap<>();

    for (Transaction borrow : borrowTransactions) {
      if (borrow.getActualReturnAt() == null) {
        for (Map.Entry<Book, Integer> entry : borrow.getBooks().entrySet()) {
          totalBorrowed.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
      }
    }

    for (Transaction retun : returnTransactions) {
      for (Map.Entry<Book, Integer> entry : retun.getBooks().entrySet()) {
        totalReturned.merge(entry.getKey(), entry.getValue(), Integer::sum);
      }
    }

    Map<Book, Integer> unreturnedBooks = new HashMap<>();

    for (Map.Entry<Book, Integer> entry : totalBorrowed.entrySet()) {
      Book book = entry.getKey();
      int borrowed = entry.getValue();
      int returned = totalReturned.getOrDefault(book, 0);

      int remaining = borrowed - returned;
      if (remaining > 0) {
        unreturnedBooks.put(book, remaining);
      }
    }

    return unreturnedBooks;
  }

  //  public int getTotalBooks() {
  //    return borrowTransactions.stream()
  //            .filter(t -> t.getTransactionType() == TransactionType.BORROW)
  //            .filter(t -> t.getActualReturnAt() != null && t.getExpectedReturnAt() != null)
  //            .filter(t -> t.getActualReturnAt() > t.getExpectedReturnAt())
  //            .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
  //            .sum();
  //  }
}
