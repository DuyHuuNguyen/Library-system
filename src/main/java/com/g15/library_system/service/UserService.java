package com.g15.library_system.service;

import com.g15.library_system.entity.User;
import java.util.Optional;

public interface UserService {
  Optional<User> findById(long id);
}
