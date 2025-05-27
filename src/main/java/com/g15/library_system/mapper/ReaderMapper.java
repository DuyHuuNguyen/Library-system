package com.g15.library_system.mapper;

import com.g15.library_system.dto.ReaderExcelDTO;
import com.g15.library_system.entity.Reader;

public interface ReaderMapper {
  ReaderExcelDTO toReaderExcelDTO(Reader reader, Long index);
}
