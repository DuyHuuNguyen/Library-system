package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.ReaderExcelDTO;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.mapper.ReaderMapper;
import com.g15.library_system.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class ReaderMapperImpl implements ReaderMapper {
  @Override
  public ReaderExcelDTO toReaderExcelDTO(Reader reader, Long index) {
    if (reader == null) return null;

    return ReaderExcelDTO.builder()
        .index(index)
        .firstName(reader.getFirstName())
        .lastName(reader.getLastName())
        .email(reader.getEmail())
        .phoneNumber(reader.getPhoneNumber())
        .avatarKey(reader.getAvatarKey())
        .dateOfBirth(DateUtil.convertToLocalDate(reader.getDateOfBirth()))
        .address(reader.getAddress())
        .build();
  }
}
