package com.g15.library_system.entity;

import com.g15.library_system.data.ReaderData;
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
    Map<Book, Integer> borrowCount = new HashMap<>();
    Map<Book, Integer> returnCount = new HashMap<>();

    for (Transaction t : borrowTransactions) {
      if (t.getTransactionType() == TransactionType.BORROW && t.getBooks() != null) {
        for (Map.Entry<Book, Integer> entry : t.getBooks().entrySet()) {
          borrowCount.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
      }
    }

    for (Transaction t : returnTransactions) {
      if (t.getTransactionType() == TransactionType.RETURNED && t.getBooks() != null) {
        for (Map.Entry<Book, Integer> entry : t.getBooks().entrySet()) {
          returnCount.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
      }
    }

    Map<Book, Integer> unreturned = new HashMap<>();
    for (Map.Entry<Book, Integer> entry : borrowCount.entrySet()) {
      Book book = entry.getKey();
      int borrowed = entry.getValue();
      int returned = returnCount.getOrDefault(book, 0);
      int remaining = borrowed - returned;
      if (remaining > 0) {
        unreturned.put(book, remaining);
      }
    }

    return unreturned;
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
