package com.g15.library_system.data;

import com.g15.library_system.entity.LibraryCard;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
@Deprecated
public class LibraryCardData implements Data<LibraryCard> {
  private static final LibraryCardData INSTANCE = new LibraryCardData();
  private final List<LibraryCard> LibraryCards = new ArrayList<>();

  private LibraryCardData() {
    this.initializeData();
  }

  @Override
  public void add(LibraryCard b) {
    this.LibraryCards.add(b);
  }

  @Override
  public void add(List<LibraryCard> t) {}

  @Override
  public void remove(LibraryCard LibraryCard) {}

  @Override
  public void remove(int index) {}

  public static LibraryCardData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    var lc1 =
        LibraryCard.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(1L)
                    .firstName("Nguyen")
                    .lastName("Huu Duy")
                    .email("duynguyen@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(101L).transactionType(TransactionType.BORROW).build(),
                    Transaction.builder().id(102L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc2 =
        LibraryCard.builder()
            .id(2L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(2L)
                    .firstName("Alice")
                    .lastName("Tran")
                    .email("alice.tran@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 2 * 365L * 24 * 60 * 60 * 1000) // 2 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(103L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.SUSPENDED)
            .build();

    var lc3 =
        LibraryCard.builder()
            .id(3L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(3L)
                    .firstName("Bob")
                    .lastName("Le")
                    .email("bob.le@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 3 * 365L * 24 * 60 * 60 * 1000) // 3 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(104L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc4 =
        LibraryCard.builder()
            .id(4L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(4L)
                    .firstName("Charlie")
                    .lastName("Pham")
                    .email("charlie.pham@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(105L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.EXPIRED)
            .build();

    var lc5 =
        LibraryCard.builder()
            .id(5L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(5L)
                    .firstName("David")
                    .lastName("Vu")
                    .email("david.vu@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 4 * 365L * 24 * 60 * 60 * 1000) // 4 năm sau
            .transactions(List.of())
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc6 =
        LibraryCard.builder()
            .id(6L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(6L)
                    .firstName("Emma")
                    .lastName("Hoang")
                    .email("emma.hoang@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 5 * 365L * 24 * 60 * 60 * 1000) // 5 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(106L).transactionType(TransactionType.BORROW).build(),
                    Transaction.builder().id(107L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc7 =
        LibraryCard.builder()
            .id(7L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(7L)
                    .firstName("Sophia")
                    .lastName("Ly")
                    .email("sophia.ly@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 2 * 365L * 24 * 60 * 60 * 1000) // 2 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(108L).transactionType(TransactionType.BORROW).build()))
            .libraryCardStatus(LibraryCardStatus.SUSPENDED)
            .build();

    var lc8 =
        LibraryCard.builder()
            .id(8L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(8L)
                    .firstName("James")
                    .lastName("Vo")
                    .email("james.vo@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 3 * 365L * 24 * 60 * 60 * 1000) // 3 năm sau
            .transactions(List.of())
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    var lc9 =
        LibraryCard.builder()
            .id(9L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(9L)
                    .firstName("Isabella")
                    .lastName("Dang")
                    .email("isabella.dang@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .libraryCardStatus(LibraryCardStatus.EXPIRED)
            .build();

    var lc10 =
        LibraryCard.builder()
            .id(10L)
            .createdAt(System.currentTimeMillis())
            .owner(
                Reader.builder()
                    .id(10L)
                    .firstName("Ethan")
                    .lastName("Nguyen")
                    .email("ethan.nguyen@example.com")
                    .build())
            .expireAt(System.currentTimeMillis() + 4 * 365L * 24 * 60 * 60 * 1000) // 4 năm sau
            .transactions(
                List.of(
                    Transaction.builder().id(110L).transactionType(TransactionType.RETURN).build()))
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();

    List<LibraryCard> LibraryCardInt = List.of(lc1, lc2, lc3, lc4, lc5, lc6, lc7, lc8, lc9, lc10);

    LibraryCards.addAll(LibraryCardInt);
  }
}
