package com.g15.library_system.facade;

import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.ResetPasswordRequest;
import com.g15.library_system.dto.request.SendOTPRequest;
import com.g15.library_system.dto.request.VerifyOTPRequest;

public interface LibrarianFacade {
  boolean login(LoginRequest loginRequest);

  void sendOTP(SendOTPRequest sendOTPRequest);

  boolean resetPassword(ResetPasswordRequest resetPasswordRequest);

  boolean verifyOTP(VerifyOTPRequest verifyOTPRequest);
}
