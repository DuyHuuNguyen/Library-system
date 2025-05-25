package com.g15.library_system.dto;

import com.g15.library_system.enums.ReturnStatus;
import com.g15.library_system.enums.TransactionType;
import java.io.Serializable;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionContentDTO implements Serializable {
  private Long id;
  private Map<String, Integer> books;
  private String librarianName;
  private Long libraryCardId;
  private String readerName;
  private String readerEmail;
  private Long expectedReturnAt;
  private Long actualReturnAt;
  private String description;
  private TransactionType transactionType;
  private ReturnStatus returnStatus;
  private Double overdueFeeAmount;
}
