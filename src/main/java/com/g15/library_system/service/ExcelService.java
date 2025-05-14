package com.g15.library_system.service;

import com.g15.library_system.entity.Book;
import java.util.List;

public interface ExcelService {
  void exportExcelBook(List<Book> books, String name, String title);

  List<Book> readExcelFileToBooks(String url);
}
