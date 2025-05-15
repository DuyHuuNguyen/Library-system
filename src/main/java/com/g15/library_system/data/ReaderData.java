package com.g15.library_system.data;

import com.g15.library_system.entity.LibraryCard;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReaderData implements Data<Reader> {
  private static final ReaderData INSTANCE = new ReaderData();
  private final List<Reader> readers = new ArrayList<>();

  private ReaderData() {
    this.initializeData();
  }

  @Override
  public void add(Reader b) {
    this.readers.add(b);
  }

  @Override
  public void add(List<Reader> t) {}

  @Override
  public void remove(Reader Reader) {}

  @Override
  public void remove(int index) {}

  public static ReaderData getInstance() {
    return INSTANCE;
  }

  public List<Reader> getReaders() {
    return readers;
  }

  private void initializeData() {
    var james = Reader.builder()
            .id(1L)
            .email("reader1@gmail.com")
            .firstName("John")
            .lastName("Doe")
            .address("123 Main St")
        .dateOfBirth(978307200000L) // 2001-01-01
            .avatarKey("avatar1")
            .phoneNumber("123456789")
            .build();
    var libCard1 = LibraryCard.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 180L * 24 * 60 * 60 * 1000) // 6 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions1 = List.of(
            Transaction.builder().id(101L).transactionType(TransactionType.BORROW).build(),
            Transaction.builder().id(102L).transactionType(TransactionType.RETURN).build());
    libCard1.addTransactions(transactions1);
    james.addLibraryCard(libCard1);

    var emma = Reader.builder()
        .id(2L)
        .email("emma.wilson@outlook.com")
        .firstName("Emma")
        .lastName("Wilson")
        .address("456 Oak Ave, Apt 2B")
        .dateOfBirth(1009843200000L) // 2002-01-01
        .avatarKey("avatar2")
        .phoneNumber("234567890")
        .build();
    var libCard2 = LibraryCard.builder()
        .id(2L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 90L * 24 * 60 * 60 * 1000) // 3 months
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions2 = List.of(
        Transaction.builder().id(103L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(104L).transactionType(TransactionType.BORROW).build());
    libCard2.addTransactions(transactions2);
    emma.addLibraryCard(libCard2);

    // Third reader
    var michael = Reader.builder()
        .id(3L)
        .email("michael.brown@yahoo.com")
        .firstName("Michael")
        .lastName("Brown")
        .address("789 Pine Rd, Suite 301")
        .dateOfBirth(1041379200000L) // 2003-01-01
        .avatarKey("avatar3")
        .phoneNumber("345678901")
        .build();
    var libCard3 = LibraryCard.builder()
        .id(3L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 270L * 24 * 60 * 60 * 1000) // 9 months
        .libraryCardStatus(LibraryCardStatus.EXPIRED)
        .build();
    var transactions3 = List.of(
        Transaction.builder().id(105L).transactionType(TransactionType.RETURN).build(),
        Transaction.builder().id(106L).transactionType(TransactionType.RETURN).build());
    libCard3.addTransactions(transactions3);
    michael.addLibraryCard(libCard3);

    // Fourth reader
    var sophia = Reader.builder()
        .id(4L)
        .email("sophia.g@hotmail.com")
        .firstName("Sophia")
        .lastName("Garcia")
        .address("321 Elm St, PH5")
        .dateOfBirth(1072915200000L) // 2004-01-01
        .avatarKey("avatar4")
        .phoneNumber("456789012")
        .build();
    var libCard4 = LibraryCard.builder()
        .id(4L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 730L * 24 * 60 * 60 * 1000) // 2 years
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions4 = List.of(
        Transaction.builder().id(107L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(108L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(109L).transactionType(TransactionType.RETURN).build());
    libCard4.addTransactions(transactions4);
    sophia.addLibraryCard(libCard4);

    // Fifth reader
    var william = Reader.builder()
        .id(5L)
        .email("will.taylor@gmail.com")
        .firstName("William")
        .lastName("Taylor")
        .address("654 Maple Dr, Unit 12")
        .dateOfBirth(1104537600000L) // 2005-01-01
        .avatarKey("avatar5")
        .phoneNumber("567890123")
        .build();
    var libCard5 = LibraryCard.builder()
        .id(5L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 45L * 24 * 60 * 60 * 1000) // 45 days
        .libraryCardStatus(LibraryCardStatus.SUSPENDED)
        .build();
    var transactions5 = Transaction.builder().id(110L).transactionType(TransactionType.BORROW).build();
    libCard5.addTransaction(transactions5);
    william.addLibraryCard(libCard5);

    var olivia = Reader.builder()
        .id(6L)
        .email("olivia.m@icloud.com")
        .firstName("Olivia")
        .lastName("Martinez")
        .address("987 Cedar Ln, Floor 3")
        .dateOfBirth(1136073600000L) // 2006-01-01
        .avatarKey("avatar6")
        .phoneNumber("678901234")
        .build();
    var libCard6 = LibraryCard.builder()
        .id(6L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 548L * 24 * 60 * 60 * 1000) // 1.5 years
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions6 = List.of(
        Transaction.builder().id(111L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(112L).transactionType(TransactionType.RETURN).build(),
        Transaction.builder().id(113L).transactionType(TransactionType.BORROW).build());
    libCard6.addTransactions(transactions6);
    olivia.addLibraryCard(libCard6);

    var ethan = Reader.builder()
        .id(7L)
        .email("ethan.anderson@outlook.com")
        .firstName("Ethan")
        .lastName("Anderson")
        .address("147 Birch Ave, Room 7B")
        .dateOfBirth(1167609600000L) // 2007-01-01
        .avatarKey("avatar7")
        .phoneNumber("789012345")
        .build();
    var libCard7 = LibraryCard.builder()
        .id(7L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 150L * 24 * 60 * 60 * 1000) // 5 months
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions7 = List.of(
        Transaction.builder().id(114L).transactionType(TransactionType.RETURN).build(),
        Transaction.builder().id(115L).transactionType(TransactionType.BORROW).build());
    libCard7.addTransactions(transactions7);
    ethan.addLibraryCard(libCard7);

    var ava = Reader.builder()
        .id(8L)
        .email("ava.thompson@yahoo.com")
        .firstName("Ava")
        .lastName("Thompson")
        .address("258 Walnut St, Apt 4C")
        .dateOfBirth(1199145600000L) // 2008-01-01
        .avatarKey("avatar8")
        .phoneNumber("890123456")
        .build();
    var libCard8 = LibraryCard.builder()
        .id(8L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 120L * 24 * 60 * 60 * 1000) // 4 months
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions8 = Transaction.builder().id(116L).transactionType(TransactionType.BORROW).build();
    libCard8.addTransaction(transactions8);
    ava.addLibraryCard(libCard8);

    var alexander = Reader.builder()
        .id(9L)
        .email("alex.white@gmail.com")
        .firstName("Alexander")
        .lastName("White")
        .address("369 Cherry Rd, Suite 15")
        .dateOfBirth(1230768000000L) // 2009-01-01
        .avatarKey("avatar9")
        .phoneNumber("901234567")
        .build();
    var libCard9 = LibraryCard.builder()
        .id(9L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000) // 2 months
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions9 = List.of(
        Transaction.builder().id(117L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(118L).transactionType(TransactionType.RETURN).build(),
        Transaction.builder().id(119L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(120L).transactionType(TransactionType.RETURN).build());
    libCard9.addTransactions(transactions9);
    alexander.addLibraryCard(libCard9);

    var isabella = Reader.builder()
        .id(10L)
        .email("isabella.lee@hotmail.com")
        .firstName("Isabella")
        .lastName("Lee")
        .address("741 Ash Ct, PH3")
        .dateOfBirth(1262304000000L) // 2010-01-01
        .avatarKey("avatar10")
        .phoneNumber("012345678")
        .build();
    var libCard10 = LibraryCard.builder()
        .id(10L)
        .createdAt(System.currentTimeMillis())
        .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 year
        .libraryCardStatus(LibraryCardStatus.ACTIVE)
        .build();
    var transactions10 = List.of(
        Transaction.builder().id(121L).transactionType(TransactionType.BORROW).build(),
        Transaction.builder().id(122L).transactionType(TransactionType.RETURN).build());
    libCard10.addTransactions(transactions10);
    isabella.addLibraryCard(libCard10);

    readers.add(james);
    readers.add(emma);
    readers.add(michael);
    readers.add(sophia);
    readers.add(william);
    readers.add(olivia);
    readers.add(ethan);
    readers.add(ava);
    readers.add(alexander);
    readers.add(isabella);
  }
}
