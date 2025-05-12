package com.g15.library_system.dto.response;

import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import java.util.List;
import lombok.*;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
  private Long id;
  private String title;
  private String author;
  private String publisher;
  private Integer publishYear;
  private GenreType genreType;
  private Integer currentQuantity;
  private Integer totalQuantity;
  private List<String> images;
  private BookStatus bookStatus;

  public boolean isAvailable() {
    return this.currentQuantity > 0;
  }
}
