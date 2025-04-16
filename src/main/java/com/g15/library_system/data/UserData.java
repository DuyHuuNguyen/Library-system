package com.g15.library_system.data;

import com.g15.library_system.entity.Librarian;
import com.g15.library_system.entity.Observer;
import com.g15.library_system.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class UserData implements Data<User> {
  private static final UserData INSTANCE = new UserData();
  private final List<Observer> users = new ArrayList<>();

  private UserData() {
    this.initializeData();
  }

  @Override
  public void add(User user) {}

  @Override
  public void add(List<User> t) {}

  @Override
  public void remove(User user) {}

  @Override
  public void remove(int index) {}

  public static UserData getInstance() {
    return INSTANCE;
  }

  private void initializeData() {
    List<Librarian> librarians = new ArrayList<>();
    // users...

    this.users.addAll(librarians);
  }
}
