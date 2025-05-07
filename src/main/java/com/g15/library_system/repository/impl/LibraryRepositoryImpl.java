package com.g15.library_system.repository.impl;

import com.g15.library_system.data.LibraryData;
import com.g15.library_system.repository.LibraryRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LibraryRepositoryImpl implements LibraryRepository {
  private LibraryData libraryData = LibraryData.getInstance();
}
