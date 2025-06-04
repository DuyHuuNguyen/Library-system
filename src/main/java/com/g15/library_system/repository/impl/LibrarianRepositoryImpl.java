package com.g15.library_system.repository.impl;

import com.g15.library_system.data.LibrarianData;
import com.g15.library_system.entity.Librarian;
import com.g15.library_system.repository.LibrarianRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

  @Override
  public void modify(Librarian librarian) { this.librarianData.modify(librarian);
  }

  @Override
  public List<Librarian> searchLibrarians(String keyword) {
    String lowerKeyword = keyword.toLowerCase();
    return librarianData.getLibrarians().stream()
            .filter(l ->
                    l.getFirstName().toLowerCase().contains(lowerKeyword) ||
                            l.getLastName().toLowerCase().contains(lowerKeyword) ||
                            l.getEmail().toLowerCase().contains(lowerKeyword) ||
                            l.getPhoneNumber().toLowerCase().contains(lowerKeyword) ||
                            l.getAddress().toLowerCase().contains(lowerKeyword)
            )
            .collect(Collectors.toList());
  }

}
