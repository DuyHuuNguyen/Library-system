package com.g15.library_system.dto;

import com.g15.library_system.enums.GenreType;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ChangeInfoBookDTO {
  private String title;

  private String author;

  private String publisher;

  private int publishYear;

  private GenreType genreType;

  private int currentQuantity;

  private int totalQuantity;

  private String image;
}
