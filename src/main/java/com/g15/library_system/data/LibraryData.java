package com.g15.library_system.data;

import com.g15.library_system.entity.Library;
import java.util.List;
import lombok.Getter;

@Getter
public class LibraryData implements Data<Library> {
  private static final LibraryData INSTANCE = new LibraryData();
  private final BookData bookData = BookData.getInstance();
  private final ReaderData readerData = ReaderData.getInstance();
  private final LibrarianData librarianData = LibrarianData.getInstance();
  private final UserData userData = UserData.getInstance();

  private final Library library =
      Library.builder()
          .id(1L)
          .name("Library NLU")
          .books(bookData.getBooks())
          .users(userData.getUsers())
          .readers(readerData.getReaders())
          .librarians(librarianData.getLibrarians())
          .transactions(readerData.getBorrowTransactions())
          .build();

  private LibraryData() {
    this.initializeData();
  }

  @Override
  public synchronized void add(Library b) {}

  @Override
  public synchronized void add(List<Library> t) {}

  @Override
  public synchronized void remove(Library Library) {}

  @Override
  public synchronized void remove(int index) {}

  public static LibraryData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {}
}
