package com.g15.library_system.service;

import com.g15.library_system.entity.Book;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.entity.Reader;
import java.util.List;

public interface ExcelService {
  void exportExcelBook(List<Book> books, String name, String title);

  List<Book> readExcelFileToBooks(String url);

  void exportExcelLibrarians(List<Librarian> librarians, String name, String title);

  List<Librarian> readExcelFileToLibrarians(String url);

  void exportExcelReaders(List<Reader> librarians, String name, String title);

  List<Reader> readExcelFileToReaders(String url);
}
