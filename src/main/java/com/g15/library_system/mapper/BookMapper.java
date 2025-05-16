package com.g15.library_system.mapper;

import com.g15.library_system.dto.BookExcelDTO;
import com.g15.library_system.dto.TitleAndFirstImageBookDTO;
import com.g15.library_system.dto.response.BookResponse;
import com.g15.library_system.dto.response.NotifyBookResponse;
import com.g15.library_system.entity.Book;
import java.util.List;
import java.util.Map;

public interface BookMapper {
  BookResponse toBookResponse(Book book);

  Object[][] toBookData(List<BookResponse> bookResponses);

  String toDataSearch(Book book, String text);

  Object[][] toBookDataWithQuantity(Map<Book, Integer> books);

  BookExcelDTO toBookExcelDTO(Book book, Long index);

  Book toBook(BookExcelDTO bookExcelDTO);

  NotifyBookResponse toNotifyBookResponse(Book book);

  Object[][] toDataNotifyBookTable(List<NotifyBookResponse> responses);

  TitleAndFirstImageBookDTO toTitleAndFirstImageBookDTO(NotifyBookResponse response);
}
