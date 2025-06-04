package com.g15.library_system.facade.impl;

import com.g15.library_system.data.CacheData;
import com.g15.library_system.dto.ContentSendOTP;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.ResetPasswordRequest;
import com.g15.library_system.dto.request.SendOTPRequest;
import com.g15.library_system.dto.request.VerifyOTPRequest;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.facade.LibrarianFacade;
import com.g15.library_system.mapper.LibrarianMapper;
import com.g15.library_system.service.EmailProducerService;
import com.g15.library_system.service.LibrarianService;
import com.g15.library_system.util.RandomizationHelper;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import java.awt.*;
import java.util.List;
import java.util.Optional;
import javax.swing.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianFacadeImpl implements LibrarianFacade {
  private static final Logger log = LoggerFactory.getLogger(LibrarianFacadeImpl.class);
  private final LibrarianService librarianService;
  private final EmailProducerService emailProducerService;

  private final LibrarianMapper librarianMapper;

  @Override
  public boolean login(LoginRequest loginRequest) {
    var librarian = this.librarianService.findByEmail(loginRequest.getEmail()).orElse(null);
    var isNotFoundLibrarian = (librarian == null);
    if (isNotFoundLibrarian) return false;
    CacheData.addCurrentLibrarian(librarian);
    return librarian.isSamePassword(loginRequest.getPassword());
  }

  @Override
  public void sendOTP(SendOTPRequest sendOTPRequest) {

    var librarian = this.librarianService.findByEmail(sendOTPRequest.getEmail()).orElse(null);
    var isNotFoundLibrarian = (librarian == null);

    JFrame frame = new JFrame();
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - frame.getWidth()) / 2;
    int y = 100;
    frame.setLocation(x, y);

    if (isNotFoundLibrarian) {
      ToastNotification panel =
          new ToastNotification(
              frame,
              ToastNotification.Type.WARNING,
              ToastNotification.Location.CENTER,
              "Email not found");
      panel.showNotification();
      return;
    }
    var otp = RandomizationHelper.generaRandomCode();
    log.info("Your otp : {}", otp);
    CacheData.addOTP(otp);

    var email =
        EmailMessageDTO.<ContentSendOTP>builder()
            .to(sendOTPRequest.getEmail())
            .subject("YOUR OTP 😊")
            .content(ContentSendOTP.builder().otp(otp).build())
            .build();
    emailProducerService.send(email);
    CacheData.addEmail(sendOTPRequest.getEmail());
  }

  @Override
  public boolean resetPassword(ResetPasswordRequest resetPasswordRequest) {
    var isPasswordMatch =
        resetPasswordRequest.getNewPassword().equals(resetPasswordRequest.getConfirmPassword());
    if (!isPasswordMatch || CacheData.isEmailEmpty()) {
      CacheData.addEmail("");
      return false;
    }
    var librarian = this.librarianService.findByEmail(CacheData.getEMAIL()).orElse(null);
    var isNotFoundLibrarian = (librarian == null);

    if (isNotFoundLibrarian) {
      ToastNotification panel =
          new ToastNotification(
              JOptionPane.getFrameForComponent(null),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.CENTER,
              "Email not found");
      panel.showNotification();
      return false;
    }
    librarian.changePassword(resetPasswordRequest.getNewPassword());
    return true;
  }

  @Override
  public boolean verifyOTP(VerifyOTPRequest verifyOTPRequest) {
    return CacheData.getOTP().equals(verifyOTPRequest.getOtp());
  }

  @Override
  public List<Librarian> findAll() {
    return this.librarianService.findAll().stream().map(this.librarianMapper::toLibrarian).toList();
  }

  @Override
  public Optional<Librarian> findByName(String name) {
    return librarianService.findByName(name);
  }

  @Override
  public void add(Librarian newLibrarian) {
    this.librarianService.save(newLibrarian);
  }

  @Override
  public List<Librarian> findByTextOfTextFieldSearchOption(String text) {
    return this.librarianService.findAll().stream()
        .filter(librarian -> librarian.isSameInfo(text))
        .map(librarian -> this.librarianMapper.toLibrarian(librarian))
        .toList();
  }

  @Override
  public List<Librarian> searchLibrarians(String keyword) {
    return this.librarianService.searchLibrarians(keyword);
  }
    @Override
    public void modify(Librarian librarian) {
        this.librarianService.modify(librarian);
    }
}
