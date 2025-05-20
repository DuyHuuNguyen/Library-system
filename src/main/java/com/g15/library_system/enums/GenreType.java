package com.g15.library_system.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Arrays;
import java.util.List;


@Getter
@AllArgsConstructor
public enum GenreType {
  FANTASY("Fantasy"),
  DYSTOPIAN("Dystopian"),
  FICTION("Fiction"),
  CLASSIC("Classic"),
  HISTORICAL("Historical"),
  MAGICAL_REALISM("Magical Realism"),
  ADVENTURE("Adventure"),
  ROMANCE("Romance"),
  EPIC("Epic"),
  PHILOSOPHICAL("Philosophical"),
  MYSTERY("Mystery"),
  HORROR("Horror"),
  MODERNIST("Modernist"),
  DEMO("Demo");

  private final String value;

  public static GenreType find(String name) {
    for (var item : values()) {
      if (item.toString().equalsIgnoreCase(name)) {
        return item;
      }
    }
    return DEMO;
  }

  public static GenreType[] getAll() {
    return values();
  }

  public static List<String> findByName(String name) {
    return Arrays.stream(values())
        .map(genreType -> genreType.toString())
        .filter(genreTypeString -> genreTypeString.toLowerCase().contains(name.toLowerCase()))
        .toList();
  }
}
