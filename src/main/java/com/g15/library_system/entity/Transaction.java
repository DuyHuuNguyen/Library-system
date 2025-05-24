package com.g15.library_system.entity;

import com.g15.library_system.enums.ReturnStatus;
import com.g15.library_system.enums.TransactionType;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Stream;
import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Transaction extends BaseEntity {

  private Map<Book, Integer> books;
  private User librarian;
  @ToString.Exclude private LibraryCard libraryCard;
  private Long expectedReturnAt;
  private Long actualReturnAt;
  private String description;
  private TransactionType transactionType;
  private OverdueFine overdueFine;

  public void addLibrarycard(LibraryCard card) {
    this.libraryCard = card;
  }

  @ToString.Include(name = "libraryCardId")
  public Long getLibraryCardId() {
    return this.libraryCard != null ? this.libraryCard.getId() : null;
  }

  public boolean findById(Long id) {
    return this.hasSameId(id);
  }

  public Stream<Map.Entry<String, Long>> getGenreWithQuantities() {
    if (this.books == null) return Stream.empty();
    return this.books.entrySet().stream()
        .map(entry -> Map.entry(entry.getKey().getGenreType().getValue(), (long) entry.getValue()));
  }

  public Map<Book, Integer> getBooks() {
    return this.books != null ? this.books : Collections.emptyMap();
  }

  public Book findBookById(Long bookId) {
    for (Book book : books.keySet()) {
      if (book.getId().equals(bookId)) {
        return book;
      }
    }
    return null;
  }

  public ReturnStatus getReturnStatus() {
    if (this.actualReturnAt == null) {
      return ReturnStatus.UNRETURNED;
    }

    if (expectedReturnAt == null) {
      return ReturnStatus.ON_TIME; // *
    }

    return actualReturnAt <= expectedReturnAt ? ReturnStatus.ON_TIME : ReturnStatus.OVERDUE;
  }

  public void applyFine(LocalDate returnDate) {
    if (overdueFine != null && validateReturnDate()) {
      overdueFine.calculateFine(this, returnDate);
    }
  }

  public boolean validateReturnDate() {
    return expectedReturnAt != null && actualReturnAt != null && actualReturnAt <= expectedReturnAt;
  }
}
