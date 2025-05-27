package com.g15.library_system.dto;

import io.github.biezhi.excel.plus.annotation.ExcelColumn;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class LibrarianExcelDTO extends BaseExcelDTO {

  @ExcelColumn(title = "First name", index = 1)
  private String firstName;

  @ExcelColumn(title = "Last name", index = 2)
  private String lastName;

  @ExcelColumn(title = "Email", index = 3)
  private String email;

  @ExcelColumn(title = "Phone number", index = 4)
  private String phoneNumber;

  @ExcelColumn(title = "Avatar", index = 5)
  private String avatarKey;

  @ExcelColumn(title = "Date of birth", index = 6)
  private LocalDate dateOfBirth;

  @ExcelColumn(title = "Address", index = 7)
  private String address;
}
