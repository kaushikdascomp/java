package com.java.library.books.service;

import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;

import java.util.List;
import java.util.Set;

public interface LibraryManagementService {


    Library findById(Long aLong);

    Library save(Library library);

    List<Book> getAllBooksFromLibrary(Long aLong);


    Iterable<Library> findAll();


}
