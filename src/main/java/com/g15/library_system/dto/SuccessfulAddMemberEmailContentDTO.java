package com.g15.library_system.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class SuccessfulAddMemberEmailContentDTO extends EmailContent {
  @Override
  public String toString() {
    return "";
  }

  public static void main(String[] args) {
    EmailMessageDTO emailMessage =
        EmailMessageDTO.<SuccessfulAddMemberEmailContentDTO>builder()
            .to("abc@gm.com")
            .subject("Đăng ki thành công")
            .content(SuccessfulAddMemberEmailContentDTO.builder().build())
            .build();
  }
}
