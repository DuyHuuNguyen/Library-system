package com.g15.library_system.facade.impl;

import com.g15.library_system.dto.EmailNotificationNewBooksDTO;
import com.g15.library_system.dto.request.ExportExcelRequest;
import com.g15.library_system.dto.request.ImportExcelRequest;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.dto.response.NotifyBookResponse;
import com.g15.library_system.entity.Book;
import com.g15.library_system.facade.BookFacade;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.service.BookService;
import com.g15.library_system.service.EmailProducerService;
import com.g15.library_system.service.ExcelProducerService;
import com.g15.library_system.service.ExcelService;
import com.g15.library_system.util.DateUtil;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {
  private static final Logger log = LoggerFactory.getLogger(BookFacadeImpl.class);
  private final BookService bookService;
  private final BookMapper bookMapper;
  private final ExcelService excelService;
  private final EmailProducerService emailProducerService;
  private final ExcelProducerService excelProducerService;

  @Override
  public List<String> searchTitleContains(String title) {
    return bookService.findAll().stream()
        .filter(book -> book.titleContains(title))
        .map(Book::getTitle)
        .toList();
  }

  @Override
  public Optional<Book> findByTitle(String title) {
    return bookService.findByTitle(title);
  }

  @Override
  public List<BookResponse> findAll() {
    return this.bookService.findAll().stream().map(this.bookMapper::toBookResponse).toList();
  }

  @Override
  public void add(Book newBook) {
    this.bookService.save(newBook);
  }

  @Override
  public List<String> supportSearch(String text) {
    return this.bookService.findAll().stream()
        .filter(book -> book.isSameInfo(text))
        .map(book -> this.bookMapper.toDataSearch(book, text))
        .toList();
  }

  @Override
  public List<BookResponse> findByTextOfTextFieldSearchOption(String text) {
    return this.bookService.findAll().stream()
        .filter(book -> book.isSameInfo(text))
        .map(book -> this.bookMapper.toBookResponse(book))
        .toList();
  }

  @Override
  public Object[][] toBookDataWithQuantity(Map<Book, Integer> books) {
    return this.bookMapper.toBookDataWithQuantity(books);
  }

  @Override
  public void exportExcel(List<Book> books, String nameFile, String headerFile) {
    excelService.exportExcelBook(books, nameFile, headerFile);
  }

  @Override
  public List<Book> getAll() {
    return this.bookService.findAll();
  }

  @Override
  public List<NotifyBookResponse> getAllNewBook() {
    return this.bookService.findAll().stream()
        .filter(book -> DateUtil.isNowDay(book.getCreatedAt()))
        .map(book -> this.bookMapper.toNotifyBookResponse(book))
        .collect(Collectors.toList());
  }

  @Override
  public void exportExcel(ExportExcelRequest request) {
    this.excelProducerService.export(request);
  }

  @Override
  public void sendEmailNotificationNewBook(
      EmailNotificationNewBooksDTO emailNotificationNewBooksDTO) {
    this.emailProducerService.send(emailNotificationNewBooksDTO);
  }

  @Override
  public void importExcel(ImportExcelRequest importExcelRequest) {
    this.excelProducerService.importExcel(importExcelRequest);
  }
}
