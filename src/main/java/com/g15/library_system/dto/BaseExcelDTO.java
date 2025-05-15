package com.g15.library_system.dto;

import io.github.biezhi.excel.plus.annotation.ExcelColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseExcelDTO {
  @ExcelColumn(title = "Index", index = 0)
  private Long index;
}
