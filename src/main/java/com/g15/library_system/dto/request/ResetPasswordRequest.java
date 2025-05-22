package com.g15.library_system.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ResetPasswordRequest {
  private String newPassword;
  private String confirmPassword;
}
