package com.g15.library_system.mapper;

import com.g15.library_system.dto.BookWithQuantityDTO;

import java.util.List;

public class ReturnBookMapper {

    public Object[][] toBookDataWithQuantity(List<BookWithQuantityDTO> books) {
        Object[][] data = new Object[books.size()][];
        for (int i = 0; i < data.length; i++) {
            var book = books.get(i);
            data[i] = new Object[] {
                book.getTitle(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishYear().toString(),
                book.getGenreType().toString(),
                book.getCurrentQuantity().toString(),
                book.getTotalQuantity().toString()
            };
        }
        return data;
    }
}
