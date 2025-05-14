package com.g15.library_system.dto;

import io.github.biezhi.excel.plus.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookExcelDTO {
  @ExcelColumn(title = "Index", index = 0)
  private Long index;

  @ExcelColumn(title = "Title", index = 1)
  private String title;

  @ExcelColumn(title = "Author", index = 2)
  private String author;

  @ExcelColumn(title = "Publisher", index = 3)
  private String publisher;

  @ExcelColumn(title = "Publisher Year", index = 4)
  private int publishYear;

  @ExcelColumn(title = "Genre", index = 5)
  private String genreType;

  @ExcelColumn(title = "Current Quantity", index = 6)
  private int currentQuantity;

  @ExcelColumn(title = "Total Quantity", index = 7)
  private int totalQuantity;

  @ExcelColumn(title = "Status", index = 8)
  private String bookStatus;
}
