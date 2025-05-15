package com.g15.library_system.dto;

import java.util.List;
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
  private List<BookWithQuantityDTO> books;
  private String status;
  private String totalFine;
  private String staffProcessed;
  private String notes;
}
