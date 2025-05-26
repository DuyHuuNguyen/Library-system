package com.g15.library_system.data;

import com.g15.library_system.entity.*;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.observers.ReaderSubject;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;
import java.util.*;
import lombok.Getter;

@Getter
public class ReaderData implements Data<Reader>, ReaderSubject {
  private static final ReaderData INSTANCE = new ReaderData();
  // observers
  private List<ReaderObserver> observers = new ArrayList<>();
  private long nextId = 11;
  private PriorityQueue<Long> availableIds = new PriorityQueue<>();

  private final List<Librarian> librarians = LibrarianData.getInstance().getLibrarians();
  private final List<Book> books = BookData.getInstance().getBooks();
  private final List<Reader> readers = new ArrayList<>();
  private final List<Transaction> transactions = new ArrayList<>();
  private final List<OverdueFee> overdueFees = FineData.getInstance().getOverdueFees();

  private ReaderData() {
    this.initializeData();
  }

  @Override
  public void add(Reader reader) {
    // Gán ID nếu chưa có
    if (reader.getId() == null || reader.getId() == 0) {
      if (!availableIds.isEmpty()) {
        reader.setId(availableIds.poll()); // Dùng ID nhỏ nhất đang có sẵn
      } else {
        reader.setId(nextId++);
      }
    }
    if (availableIds.contains(reader.getId())) {
      availableIds.poll();
    }
    this.readers.add(reader);
    notifyObservers();
  }

  @Override
  public void add(List<Reader> readers) {
    this.readers.addAll(readers);
    notifyObservers();
  }

  @Override
  public void remove(Reader reader) {
    this.readers.remove(reader);
    availableIds.add(reader.getId());
    notifyObservers();
  }

  @Override
  public void remove(int index) {
    if (index >= 0 && index < readers.size()) {
      this.readers.remove(index);
      notifyObservers();
    } else {
      throw new IndexOutOfBoundsException("Index out of bounds: " + index);
    }
  }

  public static ReaderData getInstance() {
    return INSTANCE;
  }

  public List<Reader> getReaders() {
    return readers;
  }

  public int getTotalReturnedBooks() {
    return transactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.RETURN)
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public int getTotalLendedBooks() {
    return transactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.BORROW)
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public int getTotalOverdueBooks() {
    return transactions.stream()
        .filter(t -> t.getTransactionType() == TransactionType.BORROW)
        .filter(t -> t.getActualReturnAt() != null && t.getExpectedReturnAt() != null)
        .filter(t -> t.getActualReturnAt() > t.getExpectedReturnAt())
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  private void initializeData() {
    var james =
        Reader.builder()
            .id(1L)
            .email("duynguyenavg@gmail.com")
            .firstName("John")
            .lastName("Doe")
            .address("123 Main St")
            .dateOfBirth(978307200000L) // 2001-01-01
            .createdAt(1746988800000L) // 2025-05-11
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("123456789")
            .isSubscribe(true)
            .readerType(
                StudentReaderType.builder()
                    .faculty("Information Technology")
                    .enrollmentYear(2021)
                    .studentID("IT2021001")
                    .build())
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
                                .filter(b -> b.getTitle().equals("I Believe"))
                                .findFirst()
                                .orElseThrow(),
                            3)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 15)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 22)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 10)))
                .description("Borrowed")
                .build(),
            Transaction.builder()
                .id(102L)
                .createdAt(1746988800000L) // 2025-05-11
                .transactionType(TransactionType.RETURN)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 25)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 1)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 10)))
                .description("Returned")
                .overdueFee(overdueFees.get(0))
                .build());
    libCard1.addTransactions(transactions1);
    james.addLibraryCard(libCard1);

    var emma =
        Reader.builder()
            .id(2L)
            .email("huyhoang28752@gmail.com")
            .firstName("Emma")
            .lastName("Wilson")
            .address("456 Oak Ave, Apt 2B")
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 21)))
            .dateOfBirth(1009843200000L) // 2002-01-01
            .avatarKey("/images/Emma.jpg")
            .phoneNumber("234567890")
            .isSubscribe(true)
            .readerType(
                StudentReaderType.builder()
                    .faculty("Information Technology")
                    .enrollmentYear(2021)
                    .studentID("IT2021002")
                    .build())
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
                            this.books.stream()
                                .filter(b -> b.getTitle().equals("The Great Gatsby"))
                                .findFirst()
                                .orElseThrow(),
                            3,
                            this.books.stream()
                                .filter(b -> b.getTitle().equals("To Kill a Mockingbird"))
                                .findFirst()
                                .orElseThrow(),
                            2)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 1)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 11, 1)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 30)))
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 10)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 5)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2023, 9, 30)))
            .dateOfBirth(1041379200000L) // 2003-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("345678901")
            .readerType(
                LecturerReaderType.builder()
                    .department("Computer Science")
                    .position("Dean")
                    .build())
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
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Hobbit"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 20)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 18)))
                .description("Returned 'The Hobbit'")
                .overdueFee(overdueFees.get(1))
                .build(),
            Transaction.builder()
                .id(106L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("I Believe"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 10)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 7)))
                .description("Returned 'I Believe'")
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 9)))
            .dateOfBirth(1072915200000L) // 2004-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("456789012")
            .readerType(
                LecturerReaderType.builder()
                    .department("Information Technology")
                    .position("Senior Lecturer")
                    .build())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 28)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 28)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 25)))
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
                                .filter(b -> b.getTitle().equals("I Believe"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 25)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 2)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 10)))
                .description("Borrowed 'I Believe'")
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 1)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 1)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 28)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 3, 1)))
            .dateOfBirth(1104537600000L) // 2005-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("567890123")
            .isSubscribe(true)
            .readerType(
                StudentReaderType.builder()
                    .faculty("Software Engineering")
                    .enrollmentYear(2022)
                    .studentID("SE2022001")
                    .build())
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 10)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 6, 10)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 6, 5)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 9)))
            .dateOfBirth(1136073600000L) // 2006-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("678901234")
            .isSubscribe(true)
            .readerType(
                StudentReaderType.builder()
                    .faculty("Computer Science")
                    .enrollmentYear(2020)
                    .studentID("CS2020003")
                    .build())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 20)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 6, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 6, 18)))
                .description("The Old Man and the Sea'")
                .build(),
            Transaction.builder()
                .id(112L)
                .transactionType(TransactionType.RETURN)
                .librarian(librarians.get(0))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 18)))
                .description("Returned 'Fahrenheit 451'")
                .overdueFee(overdueFees.get(4))
                .build(),
            Transaction.builder()
                .id(113L)
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 18)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 9)))
            .dateOfBirth(1167609600000L) // 2007-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("789012345")
            .readerType(
                StudentReaderType.builder()
                    .faculty("Information Systems")
                    .enrollmentYear(2023)
                    .studentID("IS2023001")
                    .build())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 18)))
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 15)))
            .dateOfBirth(1199145600000L) // 2008-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("890123456")
            .readerType(
                StudentReaderType.builder()
                    .faculty("Data Science")
                    .enrollmentYear(2022)
                    .studentID("DS2022004")
                    .build())
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
            .transactionType(TransactionType.RETURN)
            .librarian(librarians.get(0))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("War and Peace"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 18)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 3, 21)))
            .dateOfBirth(1230768000000L) // 2009-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("901234567")
            .readerType(
                StudentReaderType.builder()
                    .faculty("Artificial Intelligence")
                    .enrollmentYear(2021)
                    .studentID("AI2021003")
                    .build())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 20)))
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 16)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 23)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 18)))
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
                                .filter(
                                    b ->
                                        b.getTitle()
                                            .equals("Harry Potter and the Sorcerer's Stone"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 20)))
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 2)))
            .dateOfBirth(1262304000000L) // 2010-01-01
            .avatarKey("/images/John_Doe.png")
            .phoneNumber("012345678")
            .readerType(
                StudentReaderType.builder()
                    .faculty("Cybersecurity")
                    .enrollmentYear(2023)
                    .studentID("CY2023002")
                    .build())
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
                                .filter(
                                    b ->
                                        b.getTitle()
                                            .equals("Harry Potter and the Sorcerer's Stone"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 20)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 27)))
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
                                .filter(b -> b.getTitle().equals("I Believe"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .expectedReturnAt(System.currentTimeMillis() - 30L * 24 * 60 * 60 * 1000)
                .actualReturnAt(System.currentTimeMillis() - 28L * 24 * 60 * 60 * 1000)
                .description("Returned")
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

  // observer methods for sign up trends chart
  @Override
  public void registerObserver(ReaderObserver o) {
    this.observers.add(o);
  }

  @Override
  public void removeObserver(ReaderObserver o) {
    this.observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (ReaderObserver observer : observers) {
      observer.updateReaderData();
    }
  }

  public Reader findId(long id) {
    for (Reader reader : readers) {
      if (reader.getId().equals(id)) {
        return reader;
      }
    }
    return null;
  }
}
