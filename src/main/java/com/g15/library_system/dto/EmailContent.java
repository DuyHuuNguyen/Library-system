package com.g15.library_system.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.NoArgsConstructor;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = ContentSendOTP.class, name = "apply"),
})
@NoArgsConstructor
public abstract class EmailContent {
  public abstract String toString();
}
