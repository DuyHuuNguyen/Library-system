package com.g15.library_system.facade;

import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.SendOTPRequest;

public interface LibrarianFacade {
  boolean login(LoginRequest loginRequest);

  void sendOTP(SendOTPRequest sendOTPRequest);
}
