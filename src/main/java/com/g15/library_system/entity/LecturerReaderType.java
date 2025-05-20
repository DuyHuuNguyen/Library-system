package com.g15.library_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class LecturerReaderType implements ReaderType {
  private String department;
  private String position;

  @Override
  public String getTypeName() {
    return "Lecturer";
  }
}
