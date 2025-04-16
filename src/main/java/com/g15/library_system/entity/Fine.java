package com.g15.library_system.entity;

import com.g15.library_system.enums.FineStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@ToString(callSuper = true)
@Getter
@SuperBuilder
@AllArgsConstructor
public class Fine extends BaseEntity {
  private float price;
  private int overdueDays;
  private FineStatus fineStatus;
}
