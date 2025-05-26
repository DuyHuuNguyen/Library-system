package com.g15.library_system.data;

import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.observers.BookObserver;
import com.g15.library_system.observers.BookSubject;
import com.g15.library_system.util.DateUtil;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class BookData implements Data<Book>, BookSubject {
  private static final BookData INSTANCE = new BookData();
  private final List<Book> books = new ArrayList<>();
  private List<BookObserver> observers = new ArrayList<>();

  private BookData() {
    this.initializeData();
  }

  @Override
  public void add(Book book) {
    log.error("is add {}", this.books.add(book));
    notifyObservers();
  }

  @Override
  public void add(List<Book> books) {
    this.books.addAll(books);
    notifyObservers();
  }

  @Override
  public void remove(Book book) {
    this.books.remove(book);
    notifyObservers();
  }

  @Override
  public void remove(int index) {
    if (index >= 0 && index < books.size()) {
      this.books.remove(index);
      notifyObservers();
    }
  }

  public void update(Book book) {
    for (int i = 0; i < this.books.size(); i++) {
      Book existingBook = this.books.get(i);
      if (existingBook.hasSameId(book.getId())) {
        this.books.set(i, book);
        break;
      }
    }
    notifyObservers();
  }

  public static BookData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<Book> booksInit =
        List.of(
            Book.builder()
                .id(1L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.now()))
                .author("J.K. Rowling")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Harry Potter and the Sorcerer's Stone")
                .publisher("Bloomsbury")
                .publishYear(1997)
                .genreType(GenreType.FANTASY)
                .currentQuantity(10)
                .totalQuantity(20)
                .images(
                    new ArrayList<>(
                        List.of("/bookImages/harryPotterAndTheSorcererStone/image1.png")))
                .build(),
            Book.builder()
                .id(2L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.now()))
                .author("George Orwell")
                .bookStatus(BookStatus.AVAILABLE)
                .title("I Believe")
                .publisher("Secker & Warburg")
                .publishYear(1949)
                .genreType(GenreType.DYSTOPIAN)
                .currentQuantity(8)
                .totalQuantity(25)
                .isNewBook(true)
                .images(new ArrayList<>(List.of("/bookImages/iBelieve/IBelieve.png")))
                .build(),
            Book.builder()
                .id(3L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 4, 22)))
                .author("Harper Lee")
                .bookStatus(BookStatus.BORROWED)
                .title("To Kill a Mockingbird")
                .publisher("J.B. Lippincott & Co.")
                .publishYear(1960)
                .genreType(GenreType.FICTION)
                .currentQuantity(2)
                .totalQuantity(30)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of("/bookImages/toKillAMockingbird/To Kill a Mockingbird.png")))
                .build(),
            Book.builder()
                .id(4L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 6, 14)))
                .author("F. Scott Fitzgerald")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Great Gatsby")
                .publisher("Charles Scribner's Sons")
                .publishYear(1925)
                .genreType(GenreType.CLASSIC)
                .currentQuantity(5)
                .totalQuantity(25)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/theGreatGatsby/image1.png")))
                .build(),
            Book.builder()
                .id(5L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 9)))
                .author("J.R.R. Tolkien")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Hobbit")
                .publisher("George Allen & Unwin")
                .publishYear(1937)
                .genreType(GenreType.FANTASY)
                .currentQuantity(7)
                .totalQuantity(35)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/theHobbit/The Hobbit.png")))
                .build(),
            Book.builder()
                .id(6L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2023, 9, 25)))
                .author("Leo Tolstoy")
                .bookStatus(BookStatus.BORROWED)
                .title("War and Peace")
                .publisher("The Russian Messenger")
                .publishYear(1869)
                .genreType(GenreType.HISTORICAL)
                .currentQuantity(1)
                .totalQuantity(20)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/warAndPeace/War and peace.png")))
                .build(),
            Book.builder()
                .id(7L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 29)))
                .author("Gabriel García Márquez")
                .bookStatus(BookStatus.AVAILABLE)
                .title("One Hundred Years of Solitude")
                .publisher("Harper & Row")
                .publishYear(1967)
                .genreType(GenreType.MAGICAL_REALISM)
                .currentQuantity(6)
                .totalQuantity(30)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/OneHundredYearsOfSolitude/One Hundred Years of Solitude.png")))
                .build(),
            Book.builder()
                .id(8L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2023, 12, 17)))
                .author("Mark Twain")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Adventures of Huckleberry Finn")
                .publisher("Chatto & Windus")
                .publishYear(1885)
                .genreType(GenreType.ADVENTURE)
                .currentQuantity(4)
                .totalQuantity(25)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/theAdventureOfHuckleberryFinn/The Adventures of Huckleberry Finn.png")))
                .build(),
            Book.builder()
                .id(9L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 1, 6)))
                .author("Jane Austen")
                .bookStatus(BookStatus.BORROWED)
                .title("Pride and Prejudice")
                .publisher("T. Egerton")
                .publishYear(1813)
                .genreType(GenreType.ROMANCE)
                .currentQuantity(3)
                .totalQuantity(15)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of("/bookImages/prideAndPrejudice/Pride and Prejudice.png")))
                .build(),
            Book.builder()
                .id(10L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 2, 28)))
                .author("Homer")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Odyssey")
                .publisher("Ancient Greece")
                .publishYear(-800)
                .genreType(GenreType.EPIC)
                .currentQuantity(9)
                .totalQuantity(12)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/theOdyssey/The Odyssey.jpg")))
                .build(),
            Book.builder()
                .id(11L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 4, 15)))
                .author("Charles Dickens")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Great Expectations")
                .publisher("Chapman & Hall")
                .publishYear(1861)
                .genreType(GenreType.CLASSIC)
                .currentQuantity(6)
                .totalQuantity(25)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/greatExpectations/image1.jpg",
                            "/bookImages/greatExpectations/image2.jpg",
                            "/bookImages/greatExpectations/image3.jpg")))
                .build(),
            Book.builder()
                .id(12L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 5, 20)))
                .author("Fyodor Dostoevsky")
                .bookStatus(BookStatus.BORROWED)
                .title("Crime and Punishment")
                .publisher("The Russian Messenger")
                .publishYear(1866)
                .genreType(GenreType.PHILOSOPHICAL)
                .currentQuantity(2)
                .totalQuantity(21)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/crimeAndPunishment/image1.jpg",
                            "/bookImages/crimeAndPunishment/image2.jpg",
                            "/bookImages/crimeAndPunishment/image3.jpg")))
                .build(),
            Book.builder()
                .id(13L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 7, 3)))
                .author("Herman Melville")
                .bookStatus(BookStatus.LOST)
                .title("Moby-Dick")
                .publisher("Harper & Brothers")
                .publishYear(1851)
                .genreType(GenreType.ADVENTURE)
                .currentQuantity(5)
                .totalQuantity(28)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/mobyDick/image1.jpg",
                            "/bookImages/mobyDick/image2.jpg",
                            "/bookImages/mobyDick/image3.jpg")))
                .build(),
            Book.builder()
                .id(14L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 8, 18)))
                .author("George R.R. Martin")
                .bookStatus(BookStatus.AVAILABLE)
                .title("A Game of Thrones")
                .publisher("Bantam Books")
                .publishYear(1996)
                .genreType(GenreType.FANTASY)
                .currentQuantity(10)
                .totalQuantity(32)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/aGameOfThrones/image1.jpg",
                            "/bookImages/aGameOfThrones/image2.jpg")))
                .build(),
            Book.builder()
                .id(15L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 9, 29)))
                .author("Arthur Conan Doyle")
                .bookStatus(BookStatus.BORROWED)
                .title("The Adventures of Sherlock Holmes")
                .publisher("George Newnes")
                .publishYear(1892)
                .genreType(GenreType.MYSTERY)
                .currentQuantity(3)
                .totalQuantity(23)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/theAdventuresOfSherlockHolmes/image1.jpg",
                            "/bookImages/theAdventuresOfSherlockHolmes/image2.jpg")))
                .build(),
            Book.builder()
                .id(16L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 10, 12)))
                .author("Mary Shelley")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Frankenstein")
                .publisher("Lackington, Hughes, Harding, Mavor & Jones")
                .publishYear(1818)
                .genreType(GenreType.HORROR)
                .currentQuantity(7)
                .totalQuantity(30)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/frankenstein/image1.jpg",
                            "/bookImages/frankenstein/image2.jpg")))
                .build(),
            Book.builder()
                .id(17L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 11, 8)))
                .author("J.D. Salinger")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Catcher in the Rye")
                .publisher("Little, Brown and Company")
                .publishYear(1951)
                .genreType(GenreType.FICTION)
                .currentQuantity(6)
                .totalQuantity(28)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/theCatcherInTheRye/image1.jpg",
                            "/bookImages/theCatcherInTheRye/image2.jpg")))
                .build(),
            Book.builder()
                .id(18L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2024, 12, 24)))
                .author("Virginia Woolf")
                .bookStatus(BookStatus.BORROWED)
                .title("To the Lighthouse")
                .publisher("Hogarth Press")
                .publishYear(1927)
                .genreType(GenreType.MODERNIST)
                .currentQuantity(2)
                .totalQuantity(12)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/toTheLighthouse/image1.jpg")))
                .build(),
            Book.builder()
                .id(19L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 1, 5)))
                .author("Ernest Hemingway")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Old Man and the Sea")
                .publisher("Charles Scribner's Sons")
                .publishYear(1952)
                .genreType(GenreType.FICTION)
                .currentQuantity(9)
                .totalQuantity(35)
                .isNewBook(false)
                .images(
                    new ArrayList<>(
                        List.of(
                            "/bookImages/theOldManAndTheSea/image1.jpg",
                            "/bookImages/theOldManAndTheSea/image2.jpg")))
                .build(),
            Book.builder()
                .id(20L)
                .createdAt(DateUtil.convertToEpochMilli(LocalDate.of(2025, 2, 10)))
                .author("Dante Alighieri")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Divine Comedy")
                .publisher("Medieval Italy")
                .publishYear(1320)
                .genreType(GenreType.EPIC)
                .currentQuantity(8)
                .totalQuantity(30)
                .isNewBook(false)
                .images(new ArrayList<>(List.of("/bookImages/theDivineComedy/image1.jpg")))
                .build());
    books.addAll(booksInit);
  }

  public int totalBooksQuantity() {
    return books.stream().mapToInt(Book::getTotalQuantity).sum();
  }

  // observer
  @Override
  public void registerObserver(BookObserver o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(BookObserver o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (BookObserver o : observers) {
      o.updateBookData();
    }
  }

  @Override
  public String toString() {
    return "BookData{" + "books=" + books + '}';
  }
}
