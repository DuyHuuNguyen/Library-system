package com.g15.library_system.util;

import com.g15.library_system.response.BaseResponse;
import com.g15.library_system.response.LoginResponse;

public class TokenStore {
  private static BaseResponse<LoginResponse> loginResponseBaseResponse = null;

  public static void saveToken(BaseResponse<LoginResponse> response) {
    loginResponseBaseResponse = response;
  }

  public static String getAccessToken() {
    return loginResponseBaseResponse.getMetadata().getAccessToken();
  }
}
