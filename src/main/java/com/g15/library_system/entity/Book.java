package com.g15.library_system.entity;

import com.g15.library_system.dto.ChangeInfoBookDTO;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import java.util.ArrayList;
import java.util.List;

import com.g15.library_system.enums.UpdateMethod;
import lombok.*;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString(callSuper = true)
@Getter
@Setter
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

  @Builder.Default private boolean isNewBook = true;

  @Builder.Default private BookStatus bookStatus = BookStatus.AVAILABLE;

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
    return "no image";
  }

  public boolean hasFirstImage() {
    return !this.images.isEmpty();
  }

  public void changeInfo(ChangeInfoBookDTO changeInfoBookDTO) {
    this.title = changeInfoBookDTO.getTitle();
    this.author = changeInfoBookDTO.getAuthor();
    this.publisher = changeInfoBookDTO.getPublisher();
    this.publishYear = changeInfoBookDTO.getPublishYear();
    this.genreType = changeInfoBookDTO.getGenreType();
    this.currentQuantity = changeInfoBookDTO.getCurrentQuantity();
    this.totalQuantity = changeInfoBookDTO.getTotalQuantity();
    this.images.clear();
    this.images.add(changeInfoBookDTO.getImage());
  }

  public void addImage(String image) {
    if (this.images == null) this.images = new ArrayList<>();
    this.images.add(image);
  }

  public void markOldBook() {
    this.isNewBook = false;
  }

  public void updateQuantity(Integer quantity, UpdateMethod method) {
    switch (method) {
      case SUBTRACT -> {
        if (quantity <= currentQuantity) {
          this.currentQuantity -= quantity;
        } else {
          throw new IllegalArgumentException("Cannot decrease quantity by " + quantity + " as it exceeds the current quantity of " + currentQuantity);
        }
      }
      case ADD -> {
        this.currentQuantity += quantity;
      }
    }

  }

  public boolean isAvailable() {
    return this.currentQuantity > 0;
  }
}
