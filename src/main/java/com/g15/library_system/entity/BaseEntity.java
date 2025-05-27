package com.g15.library_system.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@ToString
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

  private Long id;

  @Builder.Default private boolean isActive = true;

  private Long createdAt;

  protected boolean hasSameBrandId(long id) {
    return this.id == id;
  }

  public void changeActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean hasSameId(Long id) {
    return this.id == id;
  }

  public boolean idContains(Long id) {
    return this.id.toString().contains(id.toString());
  }
}
