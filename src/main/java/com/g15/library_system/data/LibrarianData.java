package com.g15.library_system.data;

import com.g15.library_system.entity.Librarian;
import java.util.ArrayList;
import java.util.List;

public class LibrarianData implements Data<Librarian> {
  private static final LibrarianData INSTANCE = new LibrarianData();
  private final List<Librarian> librarians = new ArrayList<>();

  private LibrarianData() {
    this.initializeData();
  }

  public List<Librarian> getLibrarians() {
    return librarians;
  }

  @Override
  public void add(Librarian librarian) {}

  @Override
  public void add(List<Librarian> t) {}

  @Override
  public void remove(Librarian librarian) {}

  @Override
  public void remove(int index) {}

  public static LibrarianData getInstance() {
    return INSTANCE;
  }

  public void initializeData() {
    List<Librarian> Librarians =
        List.of(
            Librarian.builder()
                .id(1L)
                //                            .libraryCard(lc1)
                .email("lib1@gmail.com")
                .firstName("Ronaldo")
                .lastName("Siu")
                .address("123 Main St")
                .dateOfBirth(3487562934875L)
                .avatarKey("avatar1")
                .phoneNumber("123456789")
                .build(),
            Librarian.builder()
                .id(2L)
                //                            .libraryCard(lc2)
                .email("reader2@gmail.com")
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("987654321")
                .build(),
            Librarian.builder()
                .id(2L)
                //                            .libraryCard(lc2)
                .email("reader2@gmail.com")
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("987654321")
                .build());

    this.librarians.addAll(Librarians);
  }
}
