package com.g15.library_system.dto.returnBookDTOs;

import com.g15.library_system.enums.BookStatus;
import java.time.LocalDate;
import javax.swing.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BorrowBookDTO {
  private Long bookId;
  private ImageIcon firstCoverImage;
  private String bookTitle;
  private int bookQuantity;
  private LocalDate borrowDate;
  private LocalDate dueDate;
  private BookStatus status;
}
