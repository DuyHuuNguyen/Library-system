package com.g15.library_system.repository.impl;

import com.g15.library_system.data.UserData;
import com.g15.library_system.entity.Observer;
import com.g15.library_system.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
  private final UserData userData = UserData.getInstance();

  @Override
  public Optional<Observer> findById(long id) {
    return userData.getUsers().stream().filter(user -> user.isSameId(id)).findFirst();
  }

  @PostConstruct
  void run() {
    log.info(this.findById(7L).toString());
  }
}
