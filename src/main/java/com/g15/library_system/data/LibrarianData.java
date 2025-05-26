package com.g15.library_system.data;

import com.g15.library_system.entity.Librarian;
import com.g15.library_system.observers.LibrarianObserver;
import com.g15.library_system.observers.LibrarianSubject;
import java.util.ArrayList;
import java.util.List;

public class LibrarianData implements Data<Librarian>, LibrarianSubject {
  private static final LibrarianData INSTANCE = new LibrarianData();
  private final List<Librarian> librarians = new ArrayList<>();
  private List<LibrarianObserver> observers = new ArrayList<>();

  private LibrarianData() {
    this.initializeData();
  }

  public List<Librarian> getLibrarians() {
    return librarians;
  }

  @Override
  public void add(Librarian librarian) {
    this.librarians.add(librarian);
    notifyObservers();
  }

  @Override
  public void add(List<Librarian> t) {
    this.librarians.addAll(t);
    notifyObservers();
  }

  @Override
  public void remove(Librarian librarian) {
    this.librarians.remove(librarian);
    notifyObservers();
  }

  @Override
  public void remove(int index) {
    this.librarians.remove(index);
    notifyObservers();
  }

  public static LibrarianData getInstance() {
    return INSTANCE;
  }

  public void initializeData() {
    List<Librarian> Librarians =
        List.of(
            Librarian.builder()
                .id(1L)
                //                            .libraryCard(lc1)
                .email("dev")
                .password("1")
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .dateOfBirth(3487562934875L)
                .avatarKey("avatar1")
                .phoneNumber("0123456789")
                .build(),
            Librarian.builder()
                .id(2L)
                //                            .libraryCard(lc2)
                .email("duynguyenavg@gmail.com")
                .firstName("Jane")
                .lastName("Smith")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("0987654321")
                .build(),
            Librarian.builder()
                .id(2L)
                //                            .libraryCard(lc2)
                .email("librarian3@gmail.com")
                .firstName("David")
                .lastName("Beckham")
                .address("456 Elm St")
                .dateOfBirth(3487562934876L)
                .avatarKey("avatar2")
                .phoneNumber("0987654321")
                .build());

    this.librarians.addAll(Librarians);
  }

  @Override
  public void registerObserver(LibrarianObserver o) {
    this.observers.add(o);
  }

  @Override
  public void removeObserver(LibrarianObserver o) {
    this.observers.remove(o);
  }

  @Override
  public void notifyObservers() {
    for (LibrarianObserver observer : observers) {
      observer.updateLibrarianData();
    }
  }
}
