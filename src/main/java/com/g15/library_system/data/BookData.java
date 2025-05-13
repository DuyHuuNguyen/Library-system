package com.g15.library_system.data;

import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.observers.BookLendedObserver;
import com.g15.library_system.observers.SubjectBookLended;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class BookData implements Data<Book>, SubjectBookLended {
  private static final BookData INSTANCE = new BookData();
  private final List<Book> books = new ArrayList<>();
  private List<BookLendedObserver> observers = new ArrayList<>();

  private BookData() {
    this.initializeData();
  }

  @Override
  public void add(Book b) {
    this.books.add(b);
    notifyObservers();
  }

  @Override
  public void add(List<Book> t) {}

  @Override
  public void remove(Book book) {}

  @Override
  public void remove(int index) {}

  public static BookData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<Book> bookInt =
        List.of(
            Book.builder()
                .id(1L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("J.K. Rowling")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Harry Potter and the Sorcerer's Stone")
                .publisher("Bloomsbury")
                .publishYear(1997)
                .genreType(GenreType.FANTASY)
                .currentQuantity(10)
                .totalQuantity(100)
                .build(),
            Book.builder()
                .id(2L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("George Orwell")
                .bookStatus(BookStatus.AVAILABLE)
                .title("1984")
                .publisher("Secker & Warburg")
                .publishYear(1949)
                .genreType(GenreType.DYSTOPIAN)
                .currentQuantity(8)
                .totalQuantity(50)
                .build(),
            Book.builder()
                .id(3L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Harper Lee")
                .bookStatus(BookStatus.BORROWED)
                .title("To Kill a Mockingbird")
                .publisher("J.B. Lippincott & Co.")
                .publishYear(1960)
                .genreType(GenreType.FICTION)
                .currentQuantity(2)
                .totalQuantity(30)
                .build(),
            Book.builder()
                .id(4L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("F. Scott Fitzgerald")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Great Gatsby")
                .publisher("Charles Scribner's Sons")
                .publishYear(1925)
                .genreType(GenreType.CLASSIC)
                .currentQuantity(5)
                .totalQuantity(25)
                .build(),
            Book.builder()
                .id(5L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("J.R.R. Tolkien")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Hobbit")
                .publisher("George Allen & Unwin")
                .publishYear(1937)
                .genreType(GenreType.FANTASY)
                .currentQuantity(7)
                .totalQuantity(35)
                .build(),
            Book.builder()
                .id(6L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Leo Tolstoy")
                .bookStatus(BookStatus.BORROWED)
                .title("War and Peace")
                .publisher("The Russian Messenger")
                .publishYear(1869)
                .genreType(GenreType.HISTORICAL)
                .currentQuantity(1)
                .totalQuantity(20)
                .build(),
            Book.builder()
                .id(7L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Gabriel García Márquez")
                .bookStatus(BookStatus.AVAILABLE)
                .title("One Hundred Years of Solitude")
                .publisher("Harper & Row")
                .publishYear(1967)
                .genreType(GenreType.MAGICAL_REALISM)
                .currentQuantity(6)
                .totalQuantity(30)
                .build(),
            Book.builder()
                .id(8L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Mark Twain")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Adventures of Huckleberry Finn")
                .publisher("Chatto & Windus")
                .publishYear(1885)
                .genreType(GenreType.ADVENTURE)
                .currentQuantity(4)
                .totalQuantity(25)
                .build(),
            Book.builder()
                .id(9L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Jane Austen")
                .bookStatus(BookStatus.BORROWED)
                .title("Pride and Prejudice")
                .publisher("T. Egerton")
                .publishYear(1813)
                .genreType(GenreType.ROMANCE)
                .currentQuantity(3)
                .totalQuantity(15)
                .build(),
            Book.builder()
                .id(10L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Homer")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Odyssey")
                .publisher("Ancient Greece")
                .publishYear(-800)
                .genreType(GenreType.EPIC)
                .currentQuantity(9)
                .totalQuantity(40)
                .build(),
            Book.builder()
                .id(11L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Charles Dickens")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Great Expectations")
                .publisher("Chapman & Hall")
                .publishYear(1861)
                .genreType(GenreType.CLASSIC)
                .currentQuantity(6)
                .totalQuantity(25)
                .build(),
            Book.builder()
                .id(12L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Fyodor Dostoevsky")
                .bookStatus(BookStatus.BORROWED)
                .title("Crime and Punishment")
                .publisher("The Russian Messenger")
                .publishYear(1866)
                .genreType(GenreType.PHILOSOPHICAL)
                .currentQuantity(2)
                .totalQuantity(18)
                .build(),
            Book.builder()
                .id(13L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Herman Melville")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Moby-Dick")
                .publisher("Harper & Brothers")
                .publishYear(1851)
                .genreType(GenreType.ADVENTURE)
                .currentQuantity(5)
                .totalQuantity(22)
                .build(),
            Book.builder()
                .id(14L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("George R.R. Martin")
                .bookStatus(BookStatus.AVAILABLE)
                .title("A Game of Thrones")
                .publisher("Bantam Books")
                .publishYear(1996)
                .genreType(GenreType.FANTASY)
                .currentQuantity(10)
                .totalQuantity(50)
                .build(),
            Book.builder()
                .id(15L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Arthur Conan Doyle")
                .bookStatus(BookStatus.BORROWED)
                .title("The Adventures of Sherlock Holmes")
                .publisher("George Newnes")
                .publishYear(1892)
                .genreType(GenreType.MYSTERY)
                .currentQuantity(3)
                .totalQuantity(20)
                .build(),
            Book.builder()
                .id(16L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Mary Shelley")
                .bookStatus(BookStatus.AVAILABLE)
                .title("Frankenstein")
                .publisher("Lackington, Hughes, Harding, Mavor & Jones")
                .publishYear(1818)
                .genreType(GenreType.HORROR)
                .currentQuantity(7)
                .totalQuantity(30)
                .build(),
            Book.builder()
                .id(17L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("J.D. Salinger")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Catcher in the Rye")
                .publisher("Little, Brown and Company")
                .publishYear(1951)
                .genreType(GenreType.FICTION)
                .currentQuantity(6)
                .totalQuantity(28)
                .build(),
            Book.builder()
                .id(18L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Virginia Woolf")
                .bookStatus(BookStatus.BORROWED)
                .title("To the Lighthouse")
                .publisher("Hogarth Press")
                .publishYear(1927)
                .genreType(GenreType.MODERNIST)
                .currentQuantity(2)
                .totalQuantity(12)
                .build(),
            Book.builder()
                .id(19L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Ernest Hemingway")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Old Man and the Sea")
                .publisher("Charles Scribner's Sons")
                .publishYear(1952)
                .genreType(GenreType.FICTION)
                .currentQuantity(9)
                .totalQuantity(35)
                .build(),
            Book.builder()
                .id(20L)
                .createdAt(System.currentTimeMillis())
                .updatedAt(System.currentTimeMillis())
                .author("Dante Alighieri")
                .bookStatus(BookStatus.AVAILABLE)
                .title("The Divine Comedy")
                .publisher("Medieval Italy")
                .publishYear(1320)
                .genreType(GenreType.EPIC)
                .currentQuantity(8)
                .totalQuantity(30)
                .build());

    books.addAll(bookInt);
  }

  // observer
  @Override
  public void registerObserver(BookLendedObserver o) {
    observers.add(o);
  }

  @Override
  public void removeObserver(BookLendedObserver o) {
    observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (BookLendedObserver o : observers) {
      o.update();
    }
  }
}
