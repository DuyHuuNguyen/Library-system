package com.g15.library_system.data;

import com.g15.library_system.entity.Observer;
import com.g15.library_system.entity.Reader;
import com.g15.library_system.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class UserData implements Data<User> {
  private static final UserData INSTANCE = new UserData();
  private final List<Observer> users = new ArrayList<>();
  private final List<Reader> readers = ReaderData.getInstance().getReaders();

  private UserData() {
    this.initializeData();
  }

  public static UserData getInstance() {
    return INSTANCE;
  }

  @Override
  public synchronized void add(User user) {}

  @Override
  public synchronized void add(List<User> t) {}

  @Override
  public synchronized void remove(User user) {}

  @Override
  public synchronized void remove(int index) {}

  private void initializeData() {
    this.users.addAll(readers.stream().filter(Reader::getIsSubscribe).toList());
  }
}
