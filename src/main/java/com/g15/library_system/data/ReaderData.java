package com.g15.library_system.data;

import com.g15.library_system.entity.*;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
import com.g15.library_system.observers.ReaderObserver;
import com.g15.library_system.observers.ReaderSubject;
import com.g15.library_system.util.DateUtil;
import com.g15.library_system.util.ReaderIdGenerator;
import com.g15.library_system.util.TransactionIdGenerator;
import com.g15.library_system.view.managementView.returnBooks.factories.simpleFactory.FineStrategyFactory;
import com.g15.library_system.view.managementView.returnBooks.strategies.FineStrategyType;
import java.time.LocalDate;
import java.util.*;
import lombok.Getter;

@Getter
public class ReaderData implements Data<Reader>, ReaderSubject {
  private static final ReaderData INSTANCE = new ReaderData();
  // observers
  private List<ReaderObserver> observers = new ArrayList<>();
  private PriorityQueue<Long> availableIds = new PriorityQueue<>();

  private final List<Librarian> librarians = LibrarianData.getInstance().getLibrarians();
  private final Set<Book> books = BookData.getInstance().getBooks();

  private final List<Reader> readers = new ArrayList<>();
  private final List<Transaction> borrowTransactions = new ArrayList<>();
  private final List<Transaction> returnTransactions = new ArrayList<>();

  private ReaderData() {
    this.initializeData();
  }

  @Override
  public synchronized void add(Reader reader) {
    if (reader.getId() == null || reader.getId() == 0) {
      if (!availableIds.isEmpty()) {
        reader.setId(availableIds.poll()); // Dùng ID bị xoá trước đó
      } else {
        reader.setId(ReaderIdGenerator.generateId());
      }
    }
    if (availableIds.contains(reader.getId())) {
      availableIds.poll();
    }
    this.readers.add(reader);
    notifyObservers();
  }

  @Override
  public synchronized void add(List<Reader> readers) {
    this.readers.addAll(readers);
    notifyObservers();
  }

  @Override
  public synchronized void remove(Reader reader) {
    this.readers.remove(reader);
    availableIds.add(reader.getId());
    notifyObservers();
  }

  @Override
  public synchronized void remove(int index) {
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
    return new ArrayList<>(readers);
  }

  public List<Transaction> getBorrowTransactions() {
    return new ArrayList<>(borrowTransactions);
  }

  private void initializeData() {
    var james =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
            .email("nguyenvoquoctuan2104@gmail.com")
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
    var borrowTransaction1 =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 2)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 9)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 10)))
            .description("Borrowed")
            .build();

    var returnTransaction1 =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.RETURNED)
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 25)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 10)))
            .description("Returned")
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("I Believe"))
                            .findFirst()
                            .orElseThrow(),
                        3)))
            .overdueFine(
                new OverdueFine(
                    1200, FineStrategyFactory.createStrategy(FineStrategyType.BOOK_AGE)))
            .librarian(librarians.get(0))
            .build();

    libCard1.addBorrowTransaction(borrowTransaction1);
    libCard1.addReturnTransaction(returnTransaction1);
    james.addLibraryCard(libCard1);

    var emma =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
            .email("23130075@st.hcmuaf.edu.vn")
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
                .id(TransactionIdGenerator.generateId())
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
                            6)))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 22)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 6, 1)))
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 20)))
                //                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024,
                // 10, 5)))
                .description("Borrowed 'Pride and Prejudice'")
                .build());
    libCard2.addBorrowTransactions(transactions2);
    emma.addLibraryCard(libCard2);

    var michael =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 20)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 6, 1)))
                .description("borrow The Hobbit")
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 20)))
                .description("Returned 'I Believe'")
                .build());
    libCard3.addBorrowTransactions(transactions3);
    michael.addLibraryCard(libCard3);

    var sophia =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
    Transaction borrowTransaction =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 6, 2)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 6, 16)))
            .description("Borrowed 'The Catcher in the Rye'")
            .build();

    Transaction returnTransaction2 =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.RETURNED)
            .librarian(librarians.get(0))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("I Believe"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 10)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 10)))
            .description("Borrowed 'I Believe'")
            .build();

    Transaction returnTransaction3 =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.RETURNED)
            .librarian(librarians.get(1))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("The Odyssey"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 28)))
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 28)))
            .description("Returned 'The Odyssey'")
            .overdueFine(
                new OverdueFine(
                    6500, FineStrategyFactory.createStrategy(FineStrategyType.BOOK_AGE)))
            .build();
    libCard4.addBorrowTransaction(borrowTransaction);
    libCard4.addReturnTransaction(returnTransaction2);
    libCard4.addReturnTransaction(returnTransaction3);

    sophia.addLibraryCard(libCard4);

    var william =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
            .id(TransactionIdGenerator.generateId())
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
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 10)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 5, 20)))
            .description("Borrowed 'Moby-Dick'")
            .build();
    libCard5.addBorrowTransaction(transactions5);
    william.addLibraryCard(libCard5);

    var olivia =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
                .description("The Old Man and the Sea'")
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(0))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 18)))
                .description("Returned 'Fahrenheit 451'")
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
                .transactionType(TransactionType.BORROW)
                .librarian(librarians.get(1))
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 18)))
                .description("Borrowed 'The Odyssey'")
                .build());
    libCard6.addBorrowTransactions(transactions6);
    olivia.addLibraryCard(libCard6);

    var ethan =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
    Transaction returnTransactionEthan =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.RETURNED)
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
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 18)))
            .description("Returned 'Moby Dick'")
            .overdueFine(
                new OverdueFine(
                    18000, FineStrategyFactory.createStrategy(FineStrategyType.PER_BOOK)))
            .build();

    Transaction borrowTransactionEthan =
        Transaction.builder()
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.BORROW)
            .librarian(librarians.get(1))
            .books(
                new TreeMap<>(
                    Map.of(
                        books.stream()
                            .filter(b -> b.getTitle().equals("Great Expectations"))
                            .findFirst()
                            .orElseThrow(),
                        1)))
            .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 10)))
            .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 20)))
            .description("Borrowed 'Great Expectations'")
            .build();

    libCard7.addReturnTransaction(returnTransactionEthan);
    libCard7.addBorrowTransaction(borrowTransactionEthan);
    ethan.addLibraryCard(libCard7);

    var ava =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
            .id(TransactionIdGenerator.generateId())
            .transactionType(TransactionType.RETURNED)
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
            .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 28)))
            .description("Borrowed 'War and Peace'")
            .overdueFine(
                new OverdueFine(
                    1600, FineStrategyFactory.createStrategy(FineStrategyType.DAILY_FINE)))
            .build();
    libCard8.addReturnTransaction(transactions8);
    ava.addLibraryCard(libCard8);

    var alexander =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
    var borrowTrans9 =
        List.of(
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
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
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 18)))
                .description("Borrowed 'Crime and Punishment'")
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 10)))
                .expectedReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 20)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 26)))
                .description("Borrowed 'To Kill a Mockingbird'")
                .build());
    var returnTrans9 =
        List.of(
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
                .transactionType(TransactionType.RETURNED)
                .librarian(librarians.get(0))
                .books(
                    new TreeMap<>(
                        Map.of(
                            books.stream()
                                .filter(b -> b.getTitle().equals("The Divine Comedy"))
                                .findFirst()
                                .orElseThrow(),
                            1)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 26)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 26)))
                .description("Returned 'The Divine Comedy'")
                .overdueFine(
                    new OverdueFine(
                        18000, FineStrategyFactory.createStrategy(FineStrategyType.DAILY_FINE)))
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
                .transactionType(TransactionType.RETURNED)
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 28)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 28)))
                .description("Returned 'The Adventures of Sherlock Holmes'")
                .overdueFine(
                    new OverdueFine(
                        18000, FineStrategyFactory.createStrategy(FineStrategyType.DAILY_FINE)))
                .build());

    libCard9.addReturnTransactions(returnTrans9);
    libCard9.addBorrowTransactions(borrowTrans9);
    alexander.addLibraryCard(libCard9);

    var isabella =
        Reader.builder()
            .id(ReaderIdGenerator.generateId())
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
                .id(TransactionIdGenerator.generateId())
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
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 11, 1)))
                .description("Borrowed 'The Divine Comedy'")
                .build(),
            Transaction.builder()
                .id(TransactionIdGenerator.generateId())
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
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 27)))
                .actualReturnAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 27)))
                .description("Returned")
                .build());
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

    for (Reader reader : readers) {
      LibraryCard card = reader.getLibraryCard();
      for (Transaction borrowTran : card.getBorrowTransactions()) {
        if (!borrowTransactions.contains(borrowTran)
            && borrowTran.getTransactionType() == TransactionType.BORROW) {
          borrowTransactions.add(borrowTran);
        }
      }
      for (Transaction returnTran : card.getReturnTransactions()) {
        if (!returnTransactions.contains(returnTran)
            && returnTran.getTransactionType() == TransactionType.RETURNED) {
          returnTransactions.add(returnTran);
        }
      }
    }

    // new readers
    {
      var reader1 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("emma.jameson@gmail.com")
              .firstName("Emma")
              .lastName("Jameson")
              .address("101 Maple Street")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 10)))
              .dateOfBirth(1104537600000L) // 2005-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0901111111")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Computer Science")
                      .enrollmentYear(2022)
                      .studentID("CS2022001")
                      .build())
              .build();
      var libCard11 =
          LibraryCard.builder()
              .id(11L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader1.addLibraryCard(libCard11);

      var reader2 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("liam.nguyen@gmail.com")
              .firstName("Liam")
              .lastName("Nguyen")
              .address("202 Oak Avenue")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 15)))
              .dateOfBirth(1072915200000L) // 2004-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0902222222")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Information Systems")
                      .enrollmentYear(2020)
                      .studentID("IS2020002")
                      .build())
              .build();
      var libCard12 =
          LibraryCard.builder()
              .id(12L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader2.addLibraryCard(libCard12);

      var reader3 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("sophia.tran@gmail.com")
              .firstName("Sophia")
              .lastName("Tran")
              .address("303 Pine Blvd")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 15)))
              .dateOfBirth(1136073600000L) // 2006-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0903333333")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Software Engineering")
                      .enrollmentYear(2023)
                      .studentID("SE2023003")
                      .build())
              .build();
      var libCard13 =
          LibraryCard.builder()
              .id(13L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader3.addLibraryCard(libCard13);

      var reader4 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("noah.pham@gmail.com")
              .firstName("Noah")
              .lastName("Pham")
              .address("404 Birch Lane")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 8)))
              .dateOfBirth(1167609600000L) // 2007-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0904444444")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Cybersecurity")
                      .enrollmentYear(2021)
                      .studentID("CS2021004")
                      .build())
              .build();
      var libCard14 =
          LibraryCard.builder()
              .id(14L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader4.addLibraryCard(libCard14);

      var reader5 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("ava.hoang@gmail.com")
              .firstName("Ava")
              .lastName("Hoang")
              .address("505 Cedar Street")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 10)))
              .dateOfBirth(1199145600000L) // 2008-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0905555555")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Artificial Intelligence")
                      .enrollmentYear(2024)
                      .studentID("AI2024005")
                      .build())
              .build();
      var libCard15 =
          LibraryCard.builder()
              .id(15L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader5.addLibraryCard(libCard15);

      var reader6 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("oliver.do@gmail.com")
              .firstName("Oliver")
              .lastName("Do")
              .address("606 Spruce Rd")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 25)))
              .dateOfBirth(1230768000000L) // 2009-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0906666666")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Data Science")
                      .enrollmentYear(2023)
                      .studentID("DS2023006")
                      .build())
              .build();
      var libCard16 =
          LibraryCard.builder()
              .id(16L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader6.addLibraryCard(libCard16);

      var reader7 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("mia.vu@gmail.com")
              .firstName("Mia")
              .lastName("Vu")
              .address("707 Willow Way")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 3, 1)))
              .dateOfBirth(1104537600000L) // 2005-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0907777777")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Information Technology")
                      .enrollmentYear(2022)
                      .studentID("IT2022007")
                      .build())
              .build();
      var libCard17 =
          LibraryCard.builder()
              .id(17L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader7.addLibraryCard(libCard17);

      var reader8 =
          Reader.builder()
              .id(ReaderIdGenerator.generateId())
              .email("ethan.le@gmail.com")
              .firstName("Ethan")
              .lastName("Le")
              .address("808 Elm Circle")
              .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 4, 20)))
              .dateOfBirth(1072915200000L) // 2004-01-01
              .avatarKey("/images/John_Doe.png")
              .phoneNumber("0908888888")
              .readerType(
                  StudentReaderType.builder()
                      .faculty("Networking")
                      .enrollmentYear(2020)
                      .studentID("NW2020008")
                      .build())
              .build();
      var libCard18 =
          LibraryCard.builder()
              .id(18L)
              .createdAt(System.currentTimeMillis())
              .expireAt(System.currentTimeMillis() + 60L * 24 * 60 * 60 * 1000)
              .libraryCardStatus(LibraryCardStatus.ACTIVE)
              .build();
      reader8.addLibraryCard(libCard18);

      readers.add(reader1);
      readers.add(reader2);
      readers.add(reader3);
      readers.add(reader4);
      readers.add(reader5);
      readers.add(reader6);
      readers.add(reader7);
      readers.add(reader8);
    }
  }

  // observer methods for sign up trends chart
  @Override
  public synchronized void registerObserver(ReaderObserver o) {
    this.observers.add(o);
  }

  @Override
  public synchronized void removeObserver(ReaderObserver o) {
    this.observers.remove(o);
  }

  @Override
  public synchronized void notifyObservers() {
    for (ReaderObserver observer : observers) {
      observer.updateReaderData();
    }
  }

  public Reader findId(Long id) {
    return readers.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
  }
}
