package com.g15.library_system.service.bookService;

public interface IBookService {
  int getTotalReturnedBooks();

  int getTotalBorrowedBooks();

  int getTotalBooks();
}
