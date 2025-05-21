package com.g15.library_system.facade.impl;

import com.g15.library_system.data.CacheData;
import com.g15.library_system.dto.ContentSendOTP;
import com.g15.library_system.dto.EmailMessageDTO;
import com.g15.library_system.dto.request.LoginRequest;
import com.g15.library_system.dto.request.SendOTPRequest;
import com.g15.library_system.facade.LibrarianFacade;
import com.g15.library_system.service.EmailProducerService;
import com.g15.library_system.service.LibrarianService;
import com.g15.library_system.util.RandomizationHelper;
import com.g15.library_system.view.overrideComponent.toast.ToastNotification;
import javax.swing.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LibrarianFacadeImpl implements LibrarianFacade {
  private final LibrarianService librarianService;
  private final EmailProducerService emailProducerService;

  @Override
  public boolean login(LoginRequest loginRequest) {
    var librarian = this.librarianService.findByEmail(loginRequest.getEmail()).orElse(null);
    var isNotFoundLibrarian = (librarian == null);
    if (isNotFoundLibrarian) return false;
    return librarian.isSamePassword(loginRequest.getPassword());
  }

  @Override
  public void sendOTP(SendOTPRequest sendOTPRequest) {

    var librarian = this.librarianService.findByEmail(sendOTPRequest.getEmail()).orElse(null);
    var isNotFoundLibrarian = (librarian == null);

    if (isNotFoundLibrarian) {
      ToastNotification panel =
          new ToastNotification(
              JOptionPane.getFrameForComponent(null),
              ToastNotification.Type.WARNING,
              ToastNotification.Location.CENTER,
              "Email not found");
      panel.showNotification();
      return;
    }
    var otp = RandomizationHelper.generaRandomCode();
    CacheData.addOTP(otp);

    var emai =
        EmailMessageDTO.<ContentSendOTP>builder()
            .to(sendOTPRequest.getEmail())
            .subject("YOUR OTP 😊")
            .content(ContentSendOTP.builder().otp(otp).build())
            .build();
    emailProducerService.send(emai);
  }
}
