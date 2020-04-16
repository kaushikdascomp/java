package com.java.library.books.service;

import com.java.library.books.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookManagementService {

    List<Book> findAllById(Iterable<Long> iterable);

    Optional<Book> findById(Long aLong);

    Iterable<Book> findAll();

    Book save(Book book);

    void delete(Book book);

    void deleteById(Long aLong);
}
