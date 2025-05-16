package com.g15.library_system.dto;

import java.util.List;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class EmailNotificationNewBooksDTO {
  private String[] emails;
  private List<TitleAndFirstImageBookDTO> titleAndFirstImageDTOS;
}
