package com.g15.library_system.mapper;

import com.g15.library_system.dto.BookWithQuantityDTO;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.entity.Book;
import java.util.List;

public interface BookMapper {
  BookResponse toBookResponse(Book book);

  Object[][] toBookData(List<BookResponse> bookResponses);

  String toDataSearch(Book book, String text);

  Object[][] toBookDataWithQuantity(List<BookWithQuantityDTO> books);
}
