package com.g15.library_system.data;

import com.g15.library_system.entity.LibraryCard;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.Transaction;
import com.g15.library_system.enums.LibraryCardStatus;
import com.g15.library_system.enums.TransactionType;
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
    var james =
        Reader.builder()
            .id(1L)
            .email("reader1@gmail.com")
            .firstName("John")
            .lastName("Doe")
            .address("123 Main St")
            .dateOfBirth(3487562934875L)
            .avatarKey("avatar1")
            .phoneNumber("123456789")
            .build();
    var libCard =
        LibraryCard.builder()
            .id(1L)
            .createdAt(System.currentTimeMillis())
            .expireAt(System.currentTimeMillis() + 365L * 24 * 60 * 60 * 1000) // 1 năm sau
            .libraryCardStatus(LibraryCardStatus.ACTIVE)
            .build();
    var transactions =
        List.of(
            Transaction.builder().id(101L).transactionType(TransactionType.BORROW).build(),
            Transaction.builder().id(102L).transactionType(TransactionType.RETURN).build());
    //    libCard.addTransactions(transactions);
    //    james.addLibraryCard(libCard);

    //    List<Reader> readers = List.of(james);

    //    readers.addAll(readers); error here
    readers.add(james);
  }
}
