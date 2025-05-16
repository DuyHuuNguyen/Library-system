package com.g15.library_system.entity;

import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity implements Comparable<Book> {
  private String title;

  private String author;

  private String publisher;

  private int publishYear;

  private GenreType genreType;

  private int currentQuantity;

  private int totalQuantity;

  private List<String> images;

  private BookStatus bookStatus;

  private boolean isNewBook;

  public boolean titleContains(String title) {
    return this.title.toLowerCase().contains(title.toLowerCase());
  }

  public boolean hasSameTitle(String title) {
    return this.title.equalsIgnoreCase(title);
  }

  public boolean isSameInfo(String text) {
    return this.isSameAuthor(text) || this.isSameTitle(text) || this.isSamePublisher(text);
  }

  public boolean isSameTitle(String text) {
    return this.title.toLowerCase().contains(text.toLowerCase());
  }

  public boolean isSameAuthor(String text) {
    return this.author.toLowerCase().contains(text.toLowerCase());
  }

  public boolean isSamePublisher(String text) {
    return this.publisher.toLowerCase().contains(text.toLowerCase());
  }

  @Override
  public int compareTo(Book o) {
    if (!this.hasSameId(o.getId())) return this.getId().compareTo(o.getId());
    else if (!this.getTitle().equalsIgnoreCase(o.getTitle()))
      return this.getTitle().compareTo(o.getTitle());
    return this.getAuthor().compareTo(o.author);
  }

  public String getFirstImage() {
    if (this.images != null) return this.images.getFirst();
    return "bull";
  }

  public boolean hasFirstImage() {
    return !this.images.isEmpty();
  }

  public void changeIsNewBookState(boolean isNewBook) {
    this.isNewBook = isNewBook;
  }
}
