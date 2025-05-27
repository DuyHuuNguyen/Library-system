package com.g15.library_system.repository.impl;

import com.g15.library_system.data.LibrarianData;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.repository.LibrarianRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LibrarianRepositoryImpl implements LibrarianRepository {
  private LibrarianData librarianData = LibrarianData.getInstance();
  @Override
  public Optional<Librarian> findByEmail(String email) {
    return librarianData.getLibrarians().stream()
        .filter(librarian -> librarian.isSameEmail(email))
        .findFirst();
  }
    @Override
    public List<Librarian> findAll() {
        return librarianData.getLibrarians();
    }

    @Override
    public Optional<Librarian> findByName(String name) {
        return librarianData.getLibrarians().stream().filter(librarian -> librarian.hasSameName(name)).findFirst();
    }

    @Override
    public void save(Librarian newLibrarian) {
        this.librarianData.add(newLibrarian);
    }

}
