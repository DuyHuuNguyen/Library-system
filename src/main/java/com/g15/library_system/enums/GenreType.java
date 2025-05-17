package com.g15.library_system.enums;

import java.util.Arrays;
import java.util.List;

public enum GenreType {
  FANTASY,
  DYSTOPIAN,
  FICTION,
  CLASSIC,
  HISTORICAL,
  MAGICAL_REALISM,
  ADVENTURE,
  ROMANCE,
  EPIC,
  PHILOSOPHICAL,
  MYSTERY,
  HORROR,
  MODERNIST,
  DEMO;

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
