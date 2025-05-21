package com.g15.library_system.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ContentSendOTP extends EmailContent {
  private String otp;

  @Override
  public String toString() {
    return "Your OTP is ".concat(otp);
  }
}
