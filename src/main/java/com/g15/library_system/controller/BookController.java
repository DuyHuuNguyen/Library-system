package com.g15.library_system.controller;

import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.request.ImportExcelRequest;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.dto.response.NotifyBookResponse;
import com.g15.library_system.entity.Book;
import com.g15.library_system.facade.BookFacade;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class BookController {
  private final BookFacade bookFacade;

  public List<String> searchTitleContains(String title) {
    return bookFacade.searchTitleContains(title);
  }

  public List<String> supportSearch(String text) {
    return bookFacade.supportSearch(text);
  }

  public Optional<Book> findByTitle(String title) {
    return bookFacade.findByTitle(title);
  }

  public List<BookResponse> findALl() {
    return this.bookFacade.findAll();
  }

  public void addNewBook(Book newBook) {
    this.bookFacade.add(newBook);
  }

  public List<BookResponse> findByTextOfTextFieldSearchOption(String text) {
    return this.bookFacade.findByTextOfTextFieldSearchOption(text);
  }

  public void exportExcelBooks(List<Book> books, String nameFile, String headerFile) {
    this.bookFacade.exportExcel(books, nameFile, headerFile);
  }

  public List<Book> getAll() {
    return this.bookFacade.getAll();
  }

  public List<NotifyBookResponse> getAllNewBooks() {
    return this.bookFacade.getAllNewBook();
  }

  public void exportExcel(ExportExcelRequest request) {
    this.bookFacade.exportExcel(request);
  }

  public void sendEmailNotificationNewBook(
      EmailNotificationNewBooksDTO emailNotificationNewBooksDTO) {
    this.bookFacade.sendEmailNotificationNewBook(emailNotificationNewBooksDTO);
  }

  public void importExcel(ImportExcelRequest importExcelRequest) {
    this.bookFacade.importExcel(importExcelRequest);
  }

  public void markAnnouncedNewBook() {
    this.bookFacade.markAnnouncedBook();
  }
}
