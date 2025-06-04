package com.g15.library_system.controller;

import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.ResetPasswordRequest;
import com.g15.library_system.dto.request.SendOTPRequest;
import com.g15.library_system.dto.request.VerifyOTPRequest;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.facade.LibrarianFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
  public List<Librarian> findALl() {
    return this.librarianFacade.findAll();
  }

  public Optional<Librarian> findByName(String name) {
    return librarianFacade.findByName(name);
  }

  public void addNewLibrarian(Librarian newLibrarian) {
      this.librarianFacade.add(newLibrarian);
  }

  public void updateLibrarian(Librarian librarian) {
    this.librarianFacade.modify(librarian);
  }

    public List<Librarian> searchLibrarians(String keyword) {
      return this.librarianFacade.searchLibrarians(keyword);
    }
}
