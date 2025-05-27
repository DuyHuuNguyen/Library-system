package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.LibrarianExcelDTO;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.mapper.LibrarianMapper;
import com.g15.library_system.util.DateUtil;
import org.springframework.stereotype.Service;

@Service
public class LibrarianMapperImpl implements LibrarianMapper {
  @Override
  public LibrarianExcelDTO toLibrarianExcelDTO(Librarian librarian, long index) {
    if (librarian == null) return null;
    return LibrarianExcelDTO.builder()
        .index(index)
        .firstName(librarian.getFirstName())
        .lastName(librarian.getLastName())
        .email(librarian.getEmail())
        .phoneNumber(librarian.getPhoneNumber())
        .avatarKey(librarian.getAvatarKey())
        .dateOfBirth(DateUtil.convertToLocalDate(librarian.getDateOfBirth()))
        .address(librarian.getAddress())
        .build();
  }
}
