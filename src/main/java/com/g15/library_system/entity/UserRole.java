package com.g15.library_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserRole extends BaseEntity {

  private Long userId;

  private Long roleId;
}
