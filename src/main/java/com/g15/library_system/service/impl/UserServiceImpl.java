package com.g15.library_system.service.impl;

import com.g15.library_system.entity.User;
import com.g15.library_system.repository.UserRepository;
import com.g15.library_system.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
  private final UserRepository userRepository;

  @Override
  public Optional<User> findById(long id) {
    return null;
  }
}
