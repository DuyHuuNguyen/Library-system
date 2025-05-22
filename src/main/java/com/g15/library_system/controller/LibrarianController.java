package com.g15.library_system.controller;

import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.ResetPasswordRequest;
import com.g15.library_system.dto.request.SendOTPRequest;
import com.g15.library_system.dto.request.VerifyOTPRequest;
import com.g15.library_system.facade.LibrarianFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class LibrarianController {
  private final LibrarianFacade librarianFacade;

  public boolean login(LoginRequest loginRequest) {
    return this.librarianFacade.login(loginRequest);
  }

  public void sendOTP(SendOTPRequest sendOTPRequest) {
    this.librarianFacade.sendOTP(sendOTPRequest);
  }

  public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
    return this.librarianFacade.resetPassword(resetPasswordRequest);
  }

  public boolean verifyOTP(VerifyOTPRequest verifyOTPRequest) {
    return this.librarianFacade.verifyOTP(verifyOTPRequest);
  }
}
