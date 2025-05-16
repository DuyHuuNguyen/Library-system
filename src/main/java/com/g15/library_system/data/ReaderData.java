package com.g15.library_system.data;

import com.g15.library_system.entity.*;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import java.util.*;
import lombok.Getter;

@Getter
public class ReaderData implements Data<Reader> {
  private static final ReaderData INSTANCE = new ReaderData();
  private final List<Librarian> librarians = LibrarianData.getInstance().getLibrarians();
  private final List<Book> books = BookData.getInstance().getBooks();
  private final List<Reader> readers = new ArrayList<>();
  private final List<Transaction> transactions = new ArrayList<>();
  private final List<OverdueFee> overdueFees = FineData.getInstance().getOverdueFees();

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
    var james =
        Reader.builder()
            .id(1L)
            .email("reader1@gmail.com")
            .firstName("John")
            .lastName("Doe")
            .address("123 Main St")
            .dateOfBirth(978307200000L) // 2001-01-01
            .avatarKey("avatar1")
            .phoneNumber("123456789")
            .isSubscribe(true)
            .build();
    var libCard1 =
        LibraryCard.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 180L * 24 * 60 * 60 * 1000) // 6 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions1 =
        List.of(
            Transaction.builder()
                .id(101L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.getFirst())
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(
                                    b ->
                                        b.getTitle()
                                            .equals("Harry Potter and the Sorcerer's Stone"))
                                .findFirst()
                                .orElseThrow(),
                            3)))
                .expectedReturnAt(
                    System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000) // 30 days ago
                .actualReturnAt(
                    System.currentTimeMillis()
                        - 25L * 24 * 60 * 60 * 1000) // returned 5 days after borrow
                .description("Borrowed 'Harry Potter and the Sorcerer's Stone'")
                .build(),
            Transaction.builder()
                .id(102L)
                .transactionType(TransactionType.RETURN)
                .expectedReturnAt(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 25L * 24 * 60 * 60 * 1000)
                .description("Returned 'Harry Potter and the Sorcerer's Stone'")
                .overdueFee(overdueFees.get(0))
                .build());
    libCard1.addTransactions(transactions1);
    james.addLibraryCard(libCard1);

    var emma =
        Reader.builder()
            .id(2L)
            .email("emma.wilson@outlook.com")
            .firstName("Emma")
            .lastName("Wilson")
            .address("456 Oak Ave, Apt 2B")
            .dateOfBirth(1009843200000L) // 2002-01-01
            .avatarKey("avatar2")
            .phoneNumber("234567890")
            .isSubscribe(true)
            .build();
    var libCard2 =
        LibraryCard.builder()
            .id(2L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 90L * 24 * 60 * 60 * 1000) // 3 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions2 =
        List.of(
            Transaction.builder()
                .id(103L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Great Gatsby"))
                                .findFirst()
                                .orElseThrow(),
                            3,
                            books.stream()
                                .filter(b -> b.getTitle().equals("To Kill a Mockingbird"))
                                .findFirst()
                                .orElseThrow(),
                            2)))
                .expectedReturnAt(
                    System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000) // 14 days from now
                .actualReturnAt(null)
                .description("Borrowed 'The Great Gatsby' and 'To Kill a Mockingbird'")
                .build(),
            Transaction.builder()
                .id(104L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("Pride and Prejudice"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'Pride and Prejudice'")
                .build());
    libCard2.addTransactions(transactions2);
    emma.addLibraryCard(libCard2);

    var michael =
        Reader.builder()
            .id(3L)
            .email("michael.brown@yahoo.com")
            .firstName("Michael")
            .lastName("Brown")
            .address("789 Pine Rd, Suite 301")
            .dateOfBirth(1041379200000L) // 2003-01-01
            .avatarKey("avatar3")
            .phoneNumber("345678901")
            .build();
    var libCard3 =
        LibraryCard.builder()
            .id(3L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 270L * 24 * 60 * 60 * 1000) // 9 months
            .libraryCardStatus(LibraryCardStatus.EXPIRED)
            .build();
    var transactions3 =
        List.of(
            Transaction.builder()
                .id(105L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Hobbit"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 45L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 40L * 24 * 60 * 60 * 1000)
                .description("Returned 'The Hobbit'")
                .overdueFee(overdueFees.get(1))
                .build(),
            Transaction.builder()
                .id(106L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("1984"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 60L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 58L * 24 * 60 * 60 * 1000)
                .description("Returned '1984'")
                .overdueFee(overdueFees.get(2))
                .build());
    libCard3.addTransactions(transactions3);
    michael.addLibraryCard(libCard3);

    var sophia =
        Reader.builder()
            .id(4L)
            .email("sophia.g@hotmail.com")
            .firstName("Sophia")
            .lastName("Garcia")
            .address("321 Elm St, PH5")
            .dateOfBirth(1072915200000L) // 2004-01-01
            .avatarKey("avatar4")
            .phoneNumber("456789012")
            .build();
    var libCard4 =
        LibraryCard.builder()
            .id(4L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 730L * 24 * 60 * 60 * 1000) // 2 years
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions4 =
        List.of(
            Transaction.builder()
                .id(107L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Catcher in the Rye"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 21L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'The Catcher in the Rye'")
                .build(),
            Transaction.builder()
                .id(108L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("1984"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 21L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed '1984'")
                .build(),
            Transaction.builder()
                .id(109L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Odyssey"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 15L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 14L * 24 * 60 * 60 * 1000)
                .description("Returned 'The Odyssey'")
                .overdueFee(overdueFees.get(3))
                .build());
    libCard4.addTransactions(transactions4);
    sophia.addLibraryCard(libCard4);

    var william =
        Reader.builder()
            .id(5L)
            .email("will.taylor@gmail.com")
            .firstName("William")
            .lastName("Taylor")
            .address("654 Maple Dr, Unit 12")
            .dateOfBirth(1104537600000L) // 2005-01-01
            .avatarKey("avatar5")
            .phoneNumber("567890123")
            .isSubscribe(true)
            .build();
    var libCard5 =
        LibraryCard.builder()
            .id(5L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 45L * 24 * 60 * 60 * 1000) // 45 days
            .libraryCardStatus(LibraryCardStatus.SUSPENDED)
            .build();
    var transactions5 =
        Transaction.builder()
            .id(110L)
            .transactionType(TransactionType.BORROW)
            .librarian(librarians.get(0))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("Moby-Dick"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .expectedReturnAt(System.currentTimeMillis() + 10L * 24 * 60 * 60 * 1000)
            .actualReturnAt(null)
            .description("Borrowed 'Moby-Dick'")
            .build();
    libCard5.addTransaction(transactions5);
    william.addLibraryCard(libCard5);

    var olivia =
        Reader.builder()
            .id(6L)
            .email("olivia.m@icloud.com")
            .firstName("Olivia")
            .lastName("Martinez")
            .address("987 Cedar Ln, Floor 3")
            .dateOfBirth(1136073600000L) // 2006-01-01
            .avatarKey("avatar6")
            .phoneNumber("678901234")
            .isSubscribe(true)
            .build();
    var libCard6 =
        LibraryCard.builder()
            .id(6L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 548L * 24 * 60 * 60 * 1000) // 1.5 years
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions6 =
        List.of(
            Transaction.builder()
                .id(111L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Old Man and the Sea"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("The Old Man and the Sea'")
                .build(),
            Transaction.builder()
                .id(112L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .expectedReturnAt(System.currentTimeMillis() - 20L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 19L * 24 * 60 * 60 * 1000)
                .description("Returned 'Fahrenheit 451'")
                .overdueFee(overdueFees.get(4))
                .build(),
            Transaction.builder()
                .id(113L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .expectedReturnAt(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'The Odyssey'")
                .build());
    libCard6.addTransactions(transactions6);
    olivia.addLibraryCard(libCard6);

    var ethan =
        Reader.builder()
            .id(7L)
            .email("ethan.anderson@outlook.com")
            .firstName("Ethan")
            .lastName("Anderson")
            .address("147 Birch Ave, Room 7B")
            .dateOfBirth(1167609600000L) // 2007-01-01
            .avatarKey("avatar7")
            .phoneNumber("789012345")
            .build();
    var libCard7 =
        LibraryCard.builder()
            .id(7L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 150L * 24 * 60 * 60 * 1000) // 5 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions7 =
        List.of(
            Transaction.builder()
                .id(114L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("Moby-Dick"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 10L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 8L * 24 * 60 * 60 * 1000)
                .description("Returned 'Moby Dick'")
                .overdueFee(overdueFees.get(5))
                .build(),
            Transaction.builder()
                .id(115L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("Great Expectations"))
                            .findFirst()
                            .orElseThrow(),
                        1))
                .expectedReturnAt(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'Great Expectations'")
                .build());
    libCard7.addTransactions(transactions7);
    ethan.addLibraryCard(libCard7);

    var ava =
        Reader.builder()
            .id(8L)
            .email("ava.thompson@yahoo.com")
            .firstName("Ava")
            .lastName("Thompson")
            .address("258 Walnut St, Apt 4C")
            .dateOfBirth(1199145600000L) // 2008-01-01
            .avatarKey("avatar8")
            .phoneNumber("890123456")
            .build();
    var libCard8 =
        LibraryCard.builder()
            .id(8L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 120L * 24 * 60 * 60 * 1000) // 4 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions8 =
        Transaction.builder()
            .id(116L)
            .transactionType(TransactionType.BORROW)
            .librarian(librarians.get(0))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("War and Peace"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .expectedReturnAt(System.currentTimeMillis() + 21L * 24 * 60 * 60 * 1000)
            .actualReturnAt(null)
            .description("Borrowed 'War and Peace'")
            .build();
    libCard8.addTransaction(transactions8);
    ava.addLibraryCard(libCard8);

    var alexander =
        Reader.builder()
            .id(9L)
            .email("alex.white@gmail.com")
            .firstName("Alexander")
            .lastName("White")
            .address("369 Cherry Rd, Suite 15")
            .dateOfBirth(1230768000000L) // 2009-01-01
            .avatarKey("avatar9")
            .phoneNumber("901234567")
            .build();
    var libCard9 =
        LibraryCard.builder()
            .id(9L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000) // 2 months
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions9 =
        List.of(
            Transaction.builder()
                .id(117L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("Crime and Punishment"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 14L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'Crime and Punishment'")
                .build(),
            Transaction.builder()
                .id(118L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Divine Comedy"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 25L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 23L * 24 * 60 * 60 * 1000)
                .description("Returned 'The Divine Comedy'")
                .overdueFee(overdueFees.get(6))
                .build(),
            Transaction.builder()
                .id(119L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("To Kill a Mockingbird"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'To Kill a Mockingbird'")
                .build(),
            Transaction.builder()
                .id(120L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(
                                    b -> b.getTitle().equals("The Adventures of Sherlock Holmes"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 15L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 14L * 24 * 60 * 60 * 1000)
                .description("Returned 'The Adventures of Sherlock Holmes'")
                .overdueFee(overdueFees.get(7))
                .build());
    libCard9.addTransactions(transactions9);
    alexander.addLibraryCard(libCard9);

    var isabella =
        Reader.builder()
            .id(10L)
            .email("isabella.lee@hotmail.com")
            .firstName("Isabella")
            .lastName("Lee")
            .address("741 Ash Ct, PH3")
            .dateOfBirth(1262304000000L) // 2010-01-01
            .avatarKey("avatar10")
            .phoneNumber("012345678")
            .build();
    var libCard10 =
        LibraryCard.builder()
            .id(10L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 year
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions10 =
        List.of(
            Transaction.builder()
                .id(121L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Divine Comedy"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() + 21L * 24 * 60 * 60 * 1000)
                .actualReturnAt(null)
                .description("Borrowed 'The Divine Comedy'")
                .build(),
            Transaction.builder()
                .id(122L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(
                                    b ->
                                        b.getTitle()
                                            .equals("Harry Potter and the Sorcerer's Stone"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 28L * 24 * 60 * 60 * 1000)
                .description("Returned 'Harry Potter and the Sorcerer's Stone'")
                .overdueFee(overdueFees.get(8))
                .build());
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

    transactions.addAll(transactions1);
    transactions.addAll(transactions2);
    transactions.addAll(transactions3);
    transactions.addAll(transactions4);
    transactions.add(transactions5);
    transactions.addAll(transactions6);
    transactions.addAll(transactions7);
    transactions.add(transactions8);
    transactions.addAll(transactions9);
    transactions.addAll(transactions10);
  }
}
