package com.g15.library_system.dto;

import io.github.biezhi.excel.plus.annotation.ExcelColumn;
import lombok.*;

// @Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class ImportExcelBookDTO extends BaseExcelDTO {

  @ExcelColumn(title = "Title", index = 1)
  private String title;

  @ExcelColumn(title = "Author", index = 2)
  private String author;

  @ExcelColumn(title = "Publisher", index = 3)
  private String publisher;

  @ExcelColumn(title = "Publisher Year", index = 4)
  private String publishYear;

  @ExcelColumn(title = "Genre", index = 5)
  private String genreType;

  @ExcelColumn(title = "Total Quantity", index = 6)
  private String totalQuantity;
}
