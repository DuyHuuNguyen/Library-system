package com.g15.library_system.facade;

import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.dto.response.NotifyBookResponse;
import com.g15.library_system.entity.Book;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BookFacade {
  List<String> searchTitleContains(String title);

  Optional<Book> findByTitle(String selectedTitle);

  List<BookResponse> findAll();

  void add(Book newBook);

  List<String> supportSearch(String text);

  List<BookResponse> findByTextOfTextFieldSearchOption(String text);

  Object[][] toBookDataWithQuantity(Map<Book, Integer> books);

  void exportExcel(List<Book> books, String nameFile, String headerFile);

  List<Book> getAll();

  List<NotifyBookResponse> getAllNewBook();

  void exportExcel(ExportExcelRequest request);

  void sendEmailNotificationNewBook(EmailNotificationNewBooksDTO emailNotificationNewBooksDTO);
}
