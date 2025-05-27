package com.g15.library_system.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmailMessageDTO<T extends EmailContent> {
  private String to;
  private String subject;
  private T content;
}
