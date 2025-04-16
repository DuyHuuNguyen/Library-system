package com.g15.library_system.entity;

import com.g15.library_system.enums.TransactionType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction extends BaseEntity {

  private List<Book> books;

  private User librarian;

  private long expectedReturnAt;
  private long actualReturnAt;

  private TransactionType transactionType;
}
