package com.g15.library_system.data;

import com.g15.library_system.entity.Library;
import java.util.List;
import lombok.Getter;

@Getter
public class LibraryData implements Data<Library> {
  private static final LibraryData INSTANCE = new LibraryData();
  private final BookData bookDate = BookData.getInstance();
  private final UserData userData = UserData.getInstance();

  private final Library library =
      new Library()
          .builder()
          .id(1L)
          .name("Library NLU")
          .books(bookDate.getBooks())
          .users(userData.getUsers())
          .build();

  private LibraryData() {
    this.initializeData();
  }

  @Override
  public void add(Library b) {}

  @Override
  public void add(List<Library> t) {}

  @Override
  public void remove(Library Library) {}

  @Override
  public void remove(int index) {}

  public static LibraryData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {}
}
