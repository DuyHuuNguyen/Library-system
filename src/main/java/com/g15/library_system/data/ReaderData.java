package com.g15.library_system.data;

import com.g15.library_system.entity.Reader;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ReaderData implements Data<Reader> {
  private static final ReaderData INSTANCE = new ReaderData();
  private final List<Reader> readers = new ArrayList<>();

  private ReaderData() {
    this.initializeData();
  }

  @Override
  public void add(Reader b) {
    this.readers.add(b);
  }

  @Override
  public void add(List<Reader> t) {}

  @Override
  public void remove(Reader Reader) {}

  @Override
  public void remove(int index) {}

  public static ReaderData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<Reader> readerInt =
        List.of(
            Reader.builder()
                .id(1L)
                .email("reader1@gmail.com")
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .dateOfBirth(3487562934875L)
                .avatarKey("avatar1")
                .phoneNumber("123456789")
                .build(),
            Reader.builder()
                .id(2L)
                .email("reader2@gmail.com")
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("987654321")
                .build(),
            Reader.builder()
                .id(3L)
                .email("reader3@gmail.com")
                .firstName("Alice")
                .lastName("Johnson")
                .address("789 Oak St")
                .dateOfBirth(3487562934877L)
                .avatarKey("avatar3")
                .phoneNumber("555666777")
                .build(),
            Reader.builder()
                .id(4L)
                .email("reader4@gmail.com")
                .firstName("Bob")
                .lastName("Brown")
                .address("321 Pine St")
                .dateOfBirth(3487562934878L)
                .avatarKey("avatar4")
                .phoneNumber("111222333")
                .build(),
            Reader.builder()
                .id(5L)
                .email("reader5@gmail.com")
                .firstName("Charlie")
                .lastName("Davis")
                .address("654 Maple St")
                .dateOfBirth(3487562934879L)
                .avatarKey("avatar5")
                .phoneNumber("444555666")
                .build(),
            Reader.builder()
                .id(6L)
                .email("reader6@gmail.com")
                .firstName("David")
                .lastName("Miller")
                .address("987 Birch St")
                .dateOfBirth(3487562934880L)
                .avatarKey("avatar6")
                .phoneNumber("777888999")
                .build(),
            Reader.builder()
                .id(7L)
                .email("reader7@gmail.com")
                .firstName("Eve")
                .lastName("Wilson")
                .address("159 Cedar St")
                .dateOfBirth(3487562934881L)
                .avatarKey("avatar7")
                .phoneNumber("333222111")
                .build(),
            Reader.builder()
                .id(8L)
                .email("reader8@gmail.com")
                .firstName("Frank")
                .lastName("Moore")
                .address("753 Walnut St")
                .dateOfBirth(3487562934882L)
                .avatarKey("avatar8")
                .phoneNumber("666777888")
                .build(),
            Reader.builder()
                .id(9L)
                .email("reader9@gmail.com")
                .firstName("Grace")
                .lastName("Taylor")
                .address("852 Chestnut St")
                .dateOfBirth(3487562934883L)
                .avatarKey("avatar9")
                .phoneNumber("999000111")
                .build(),
            Reader.builder()
                .id(10L)
                .email("reader10@gmail.com")
                .firstName("Hank")
                .lastName("Anderson")
                .address("369 Redwood St")
                .dateOfBirth(3487562934884L)
                .avatarKey("avatar10")
                .phoneNumber("222333444")
                .build());

    readers.addAll(readerInt);
    var cards = LibraryCardData.getInstance().getLibraryCards();
    for (int i = 0; i < readers.size(); i++) {
      readers.get(i).addLibraryCard(cards.get(i));
    }
  }
}
