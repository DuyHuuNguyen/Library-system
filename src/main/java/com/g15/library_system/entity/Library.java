package com.g15.library_system.entity;

import java.util.List;
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
public class Library extends BaseEntity implements Subject {
  private List<Observer> users;
  private String name;
  private String event;
  private List<Book> books;

  @Override
  public void addObserver(Observer o) {}

  @Override
  public void removeObserver(Observer o) {}

  @Override
  public void notifyNewBook(Book book) {}

  @Override
  public void notifyNewEvent(String newEvent) {}
}
