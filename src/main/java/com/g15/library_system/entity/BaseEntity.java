package com.g15.library_system.entity;

import java.time.Instant;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
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

  protected Long createdAt;

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

  public Optional<Month> getCreatedMonth() {
    if (createdAt == null) return Optional.empty();
    return Optional.of(Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).getMonth());
  }

  public Optional<String> getCreatedDayString() {
    if (createdAt == null) return Optional.empty();
    return Optional.of(
        Instant.ofEpochMilli(createdAt)
            .atZone(ZoneId.systemDefault())
            .toLocalDate()
            .format(DateTimeFormatter.ofPattern("dd")));
  }

  public Optional<Integer> getCreatedYear() {
    if (createdAt == null) return Optional.empty();
    return Optional.of(Instant.ofEpochMilli(createdAt).atZone(ZoneId.systemDefault()).getYear());
  }
}
