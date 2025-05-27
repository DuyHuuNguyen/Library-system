package com.g15.library_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailMessageDTO<T extends EmailContent> {
  private String to;
  private String subject;
  private T content;
}
