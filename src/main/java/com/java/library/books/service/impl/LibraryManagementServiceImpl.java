package com.java.library.books.service.impl;

import com.java.library.books.dao.LibraryRepository;
import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import com.java.library.books.service.LibraryManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service

public class LibraryManagementServiceImpl implements LibraryManagementService {

    @Autowired
    public LibraryRepository libraryRepository;

    @Override
    public Library findById(Long aLong) {
        Optional<Library> getLibraryDetails = libraryRepository.findById(aLong);
        return getLibraryDetails.get();
    }

    @Override
    public Library save(Library library) {
        return libraryRepository.save(library);
    }

    @Override

    public List<Book> getAllBooksFromLibrary(Long aLong) {
        Optional<Library> getLibraryDetails = libraryRepository.findById(aLong);
        return getLibraryDetails.isPresent() ? getLibraryDetails.get().getBookList(): Collections.emptyList();
    }

    @Override
    public Iterable<Library> findAll() {
        return libraryRepository.findAll();
    }
}
