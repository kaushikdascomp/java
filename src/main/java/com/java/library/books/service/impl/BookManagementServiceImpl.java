package com.java.library.books.service.impl;

import com.java.library.books.dao.BookRepository;
import com.java.library.books.domain.Book;
import com.java.library.books.service.BookManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookManagementServiceImpl implements BookManagementService {

    @Autowired
    private BookRepository bookRepository;


    @Override
    public List<Book> findAllById(Iterable<Long> iterable) {
        return bookRepository.findAllById(iterable);
    }

    @Override
    public Optional<Book> findById(Long aLong) {
        return bookRepository.findById(aLong);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book save(Book book) {

        return bookRepository.save(book);
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public void deleteById(Long aLong) {
        bookRepository.deleteById(aLong);
    }


}
