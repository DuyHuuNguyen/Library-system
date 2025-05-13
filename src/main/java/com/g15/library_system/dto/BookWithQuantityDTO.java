package com.g15.library_system.dto;

import com.g15.library_system.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookWithQuantityDTO {
  private Book book;
  private Integer quantity;
}
