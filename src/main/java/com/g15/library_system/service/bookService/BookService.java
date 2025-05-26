package com.g15.library_system.service.bookService;

import com.g15.library_system.data.ReaderData;
import com.g15.library_system.enums.TransactionType;

public class BookService implements IBookService {
  private ReaderData readerData = ReaderData.getInstance();

  public BookService() {}

  @Override
  public int getTotalBooks() {
    return readerData.getBorrowTransactions().stream()
            .filter(t -> t.getTransactionType() == TransactionType.BORROW)
            .filter(t -> t.getActualReturnAt() != null && t.getExpectedReturnAt() != null)
            .filter(t -> t.getActualReturnAt() > t.getExpectedReturnAt())
            .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
            .sum();
  }

  @Override
  public int getTotalBorrowedBooks() {
    return readerData.getBorrowTransactions().stream()
            .filter(t -> t.getTransactionType() == TransactionType.BORROW)
            .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
            .sum();
  }

  @Override
  public int getTotalReturnedBooks() {
    return readerData.getReturnTransactions().stream()
        .filter(t -> t.getTransactionType() == TransactionType.RETURNED)
        .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
        .sum();
  }

  public int getTotalOverdueBooks() {
    long now = System.currentTimeMillis();

    return readerData.getBorrowTransactions().stream()
            .filter(t -> t.getTransactionType() == TransactionType.BORROW)
            .filter(t -> t.getExpectedReturnAt() != null)
            .filter(t -> {
              Long expected = t.getExpectedReturnAt();
              Long actual = t.getActualReturnAt();
              return (actual != null && actual > expected) ||
                      (actual == null && now > expected);
            })
            .mapToInt(t -> t.getBooks().values().stream().mapToInt(Integer::intValue).sum())
            .sum();
  }

}
