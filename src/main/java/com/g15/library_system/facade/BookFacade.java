package com.g15.library_system.facade;

import com.g15.library_system.dto.BookWithQuantityDTO;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookFacade {
  List<String> searchTitleContains(String title);

  Optional<Book> findByTitle(String selectedTitle);

  List<BookResponse> findAll();

  Object[][] toBookDataWithQuantity(List<BookWithQuantityDTO> books);
}
