package com.g15.library_system.repository;

import com.g15.library_system.entity.Observer;
import java.util.Optional;

public interface UserRepository {
  Optional<Observer> findById(long id);
}
