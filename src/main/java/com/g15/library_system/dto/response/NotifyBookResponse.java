package com.g15.library_system.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
public class NotifyBookResponse {
  private String title;
  private String publisher;
  public String author;
  public String firstImage;
}
