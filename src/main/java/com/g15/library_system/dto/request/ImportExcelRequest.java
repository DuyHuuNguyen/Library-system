package com.g15.library_system.dto.request;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class ImportExcelRequest {
  private String url;
}
