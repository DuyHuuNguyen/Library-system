package com.g15.library_system.entity;

import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
  private String title;

  private String author;

  private String publisher;

  private int publishYear;

  private GenreType genreType;

  private int currentQuantity;

  private int totalQuantity;

  private List<String> images;

  private BookStatus bookStatus;

  public boolean titleContains(String title) {
    return this.title.toLowerCase().contains(title.toLowerCase());
  }

  public boolean hasSameTitle(String title) {
    return this.title.equalsIgnoreCase(title);
  }
}
