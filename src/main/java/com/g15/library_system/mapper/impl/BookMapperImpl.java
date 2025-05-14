package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.BookExcelDTO;
import com.g15.library_system.dto.BookWithQuantityDTO;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.entity.Book;
import com.g15.library_system.enums.BookStatus;
import com.g15.library_system.enums.GenreType;
import com.g15.library_system.enums.Status;
import com.g15.library_system.mapper.BookMapper;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class BookMapperImpl implements BookMapper {

  @Override
  public BookResponse toBookResponse(Book book) {
    if (book == null) return null;

    return BookResponse.builder()
        .id(book.getId())
        .title(book.getTitle())
        .author(book.getAuthor())
        .publisher(book.getPublisher())
        .publishYear(book.getPublishYear())
        .genreType(book.getGenreType())
        .currentQuantity(book.getCurrentQuantity())
        .totalQuantity(book.getTotalQuantity())
        .images(book.getImages())
        .bookStatus(book.getBookStatus())
        .build();
  }

  @Override
  public Object[][] toBookData(List<BookResponse> bookResponses) {
    Object[][] data = new Object[bookResponses.size()][];
    for (int i = 0; i < data.length; i++) {
      var book = bookResponses.get(i);
      data[i] =
          new Object[] {
            //                  "",
            book.getTitle(),
            book.getAuthor(),
            book.getPublisher(),
            book.getPublishYear().toString(),
            book.getGenreType().toString(),
            book.getCurrentQuantity().toString(),
            book.getTotalQuantity().toString(),
            Status.AVAILABLE.toString().toLowerCase()
          };
    }
    return data;
  }

  @Override
  public String toDataSearch(Book book, String text) {
    if (book.isSamePublisher(text)) return book.getPublisher();

    if (book.isSameTitle(text)) return book.getTitle();

    return book.getAuthor();
  }

  @Override
  public Object[][] toBookDataWithQuantity(List<BookWithQuantityDTO> books) {
    if (books == null) return null;
    Object[][] data = new Object[books.size()][];
    for (int i = 0; i < data.length; i++) {
      var bookWithQuantityDTO = books.get(i);
      Book book = bookWithQuantityDTO.getBook();
      data[i] =
          new Object[] {
            false,
            book.getTitle(),
            book.getAuthor(),
            book.getGenreType().toString(),
            bookWithQuantityDTO.getQuantity().toString()
          };
    }
    return data;
  }

  @Override
  public BookExcelDTO toBookExcelDTO(Book book, Long index) {
    if (book == null) return null;
    return BookExcelDTO.builder()
        .index(index)
        .title(book.getTitle())
        .author(book.getAuthor())
        .publisher(book.getPublisher())
        .publishYear(book.getPublishYear())
        .genreType(book.getGenreType().toString())
        .currentQuantity(book.getCurrentQuantity())
        .totalQuantity(book.getTotalQuantity())
        .bookStatus(book.getBookStatus().toString())
        .build();
  }

  @Override
  public Book toBook(BookExcelDTO bookExcelDTO) {
    return Book.builder()
        .title(bookExcelDTO.getTitle())
        .author(bookExcelDTO.getAuthor())
        .publisher(bookExcelDTO.getPublisher())
        .publishYear(bookExcelDTO.getPublishYear())
        .genreType(Enum.valueOf(GenreType.class, bookExcelDTO.getGenreType()))
        .currentQuantity(bookExcelDTO.getCurrentQuantity())
        .totalQuantity(bookExcelDTO.getTotalQuantity())
        .bookStatus(Enum.valueOf(BookStatus.class, bookExcelDTO.getBookStatus()))
        .build();
  }
}
