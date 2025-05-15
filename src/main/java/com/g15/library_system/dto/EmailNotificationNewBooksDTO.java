package com.g15.library_system.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class EmailNotificationNewBooksDTO {
  private String[] emails;
  private List<TitleAndFirstImageBookDTO> titleAndFirstImageDTOS;
}
