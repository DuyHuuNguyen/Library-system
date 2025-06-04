package com.g15.library_system.mapper.impl;

import com.g15.library_system.dto.LibrarianExcelDTO;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.mapper.LibrarianMapper;
import com.g15.library_system.util.DateUtil;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class LibrarianMapperImpl implements LibrarianMapper {
  @Override
  public Librarian toLibrarian(Librarian librarian) {
    if (librarian == null) return null;

    return Librarian.builder()
        .id(librarian.getId())
        .firstName(librarian.getFirstName())
        .lastName(librarian.getLastName())
        .email(librarian.getEmail())
        .password(librarian.getPassword())
        .phoneNumber(librarian.getPhoneNumber())
        .avatarKey(librarian.getAvatarKey())
        .dateOfBirth(librarian.getDateOfBirth())
        .address(librarian.getAddress())
        .build();
  }

  @Override
  public Object[][] toLibrarianData(List<Librarian> librarian) {
    Object[][] data = new Object[librarian.size()][];
    for (int i = 0; i < data.length; i++) {
      var librarians = librarian.get(i);
      data[i] =
          new Object[] {
            librarians.getFirstName(),
            librarians.getLastName(),
            librarians.getEmail(),
            librarians.getPassword(),
            librarians.getPhoneNumber(),
            librarians.getAvatarKey(),
            librarians.getDateOfBirth(),
            librarians.getAddress()
          };
    }
    return data;
  }

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
