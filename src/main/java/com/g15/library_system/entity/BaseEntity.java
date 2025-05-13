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

  private Long updatedAt;

  //  protected void prePersist() {
  //    isActive = true;
  //    if (this.createdAt == null) createdAt = Instant.now().toEpochMilli();
  //    if (this.updated_at == null) updated_at = Instant.now().toEpochMilli();
  //  }
  //
  //  protected void preUpdate() {
  //    this.updated_at = Instant.now().toEpochMilli();
  //  }

  protected boolean hasSameBrandId(long id) {
    return this.id == id;
  }

  public void changeActive(boolean isActive) {
    this.isActive = isActive;
  }

  public boolean hasSameId(Long id) {
    return this.id == id;
  }
}
