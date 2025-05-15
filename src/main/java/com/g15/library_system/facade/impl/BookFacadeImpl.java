package com.g15.library_system.facade.impl;

import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.entity.Book;
import com.g15.library_system.facade.BookFacade;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.service.BookService;
import com.g15.library_system.service.ExcelService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookFacadeImpl implements BookFacade {
  private final BookService bookService;
  private final BookMapper bookMapper;
  private final ExcelService excelService;

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

  @PostConstruct
  public void run() {
    //    this.exportExcel(this.getAll(),"demo-api-export.xlsx","header");
    this.excelService.readExcelFileToBooks("src/main/resources/exceldemo-api-export.xlsx");
  }
}
