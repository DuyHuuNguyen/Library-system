package com.g15.library_system.dto;

import com.g15.library_system.entity.Book;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnBookDTO {
  private Long transactionId;
  private Long readerId;
  private String readerFullName;
  private String readerPhoneNumber;
  private String readerEmail;
  private String returnDate;
  private Map<Book, Integer> books;
  private String status;
  private String totalFine;
  private String staffProcessed;
  private String notes;
}
