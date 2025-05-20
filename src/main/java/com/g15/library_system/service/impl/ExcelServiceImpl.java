package com.g15.library_system.service.impl;

import com.g15.library_system.dto.ImportExcelBookDTO;
import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.mapper.BookMapper;
import com.g15.library_system.mapper.LibrarianMapper;
import com.g15.library_system.mapper.ReaderMapper;
import com.g15.library_system.service.ExcelService;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import io.github.biezhi.excel.plus.Reader;
import io.github.biezhi.excel.plus.Writer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import javax.swing.*;
import lombok.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExcelServiceImpl implements ExcelService {
  private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);
  private final BookMapper bookMapper;
  private final ReaderMapper readerMapper;
  private final LibrarianMapper librarianMapper;

  @Override
  @SneakyThrows
  public void exportExcelBook(List<Book> books, String name, String title) {
    AtomicLong index = new AtomicLong(1);
    var bookExcelDTOS =
        books.stream()
            .map(book -> this.bookMapper.toBookExcelDTO(book, index.getAndIncrement()))
            .toList();
    Writer.create()
        .withRows(bookExcelDTOS)
        .headerTitle(title)
        .to(new File("src/main/resources/excel/" + name));
  }

  @Override
  public List<Book> readExcelFileToBooks(String url) {
    var bookExcelDTOS = Reader.create(ImportExcelBookDTO.class).from(new File(url)).asList();
    log.info("haha -> {}", bookExcelDTOS.getFirst());
    //    return List.of();
    try {
      return bookExcelDTOS.stream()
          .map(importExcelBookDTO -> this.bookMapper.toBook(importExcelBookDTO))
          .toList();
    } catch (Exception e) {
      log.error("Error format excel 🫠🫠🫠");
      ToastNotification panel =
          new ToastNotification(
              JOptionPane.getFrameForComponent(null),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.TOP_CENTER,
              "Error format excel");
      panel.showNotification();
      return new ArrayList<>();
    }
  }

  @Override
  @SneakyThrows
  public void exportExcelLibrarians(List<Librarian> librarians, String name, String title) {
    AtomicLong index = new AtomicLong(1);
    var readerExcelDTOS =
        librarians.stream()
            .map(
                librarian ->
                    this.librarianMapper.toLibrarianExcelDTO(librarian, index.getAndIncrement()))
            .toList();
    Writer.create()
        .withRows(readerExcelDTOS)
        .headerTitle(title)
        .to(new File("src/main/resources/excel/" + name));
  }

  @Override
  public List<Librarian> readExcelFileToLibrarians(String url) {
    return List.of();
  }

  @Override
  @SneakyThrows
  public void exportExcelReaders(
      List<com.g15.library_system.entity.Reader> readers, String name, String title) {
    AtomicLong index = new AtomicLong(1);

    var readerExcelDTOS =
        readers.stream()
            .map(reader -> this.readerMapper.toReaderExcelDTO(reader, index.getAndIncrement()))
            .toList();
    Writer.create()
        .withRows(readerExcelDTOS)
        .headerTitle(title)
        .to(new File("src/main/resources/excel/" + name));
  }

  @Override
  public List<com.g15.library_system.entity.Reader> readExcelFileToReaders(String url) {
    // code here
    return List.of();
  }
}
