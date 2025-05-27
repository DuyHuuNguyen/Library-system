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
    return "Xin thông báo đến "
        + fullName
        + " rằng bạn đã đăng kí thành công thẻ thành viên của thư viện!"
        + "\nBạn sẽ được nhận các thông báo khi có sách mới thuộc thể loại bạn hay mượn!"
        + "\nXin cám ơn!";
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
