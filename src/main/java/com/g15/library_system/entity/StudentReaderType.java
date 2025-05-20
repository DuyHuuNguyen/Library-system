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
public class StudentReaderType implements ReaderType {
  private String faculty;
  private int enrollmentYear;
  private String studentID;

  @Override
  public String getTypeName() {
    return "Student";
  }
}
