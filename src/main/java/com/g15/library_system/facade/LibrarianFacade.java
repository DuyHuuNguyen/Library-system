package com.g15.library_system.facade;

import com.g15.library_system.dto.request.LoginRequest;

public interface LibrarianFacade {
  boolean login(LoginRequest loginRequest);
}
