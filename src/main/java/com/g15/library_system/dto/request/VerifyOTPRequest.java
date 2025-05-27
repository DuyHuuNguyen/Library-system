package com.g15.library_system.dto.request;

import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VerifyOTPRequest {
  private String otp;
}
