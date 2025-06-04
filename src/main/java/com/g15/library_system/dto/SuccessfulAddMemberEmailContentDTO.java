package com.g15.library_system.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
// @ToString
public class SuccessfulAddMemberEmailContentDTO extends EmailContent {
  private String fullName;

  @Override
  public String toString() {
    return "Please be informed "
            + fullName
            + ", that you have successfully registered for the library membership card!"
            + "\nYou will receive notifications when new books in your favorite genres are available!"
            + "\nThank you!";
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
  //  public static void main(String[] args) {
  //    EmailMessageDTO emailMessage =
  //        EmailMessageDTO.<SuccessfulAddMemberEmailContentDTO>builder()
  //            .to("abc@gm.com")
  //            .subject("Đăng ki thành công")
  //            .content(SuccessfulAddMemberEmailContentDTO.builder().build())
  //            .build();
  //  }
}
