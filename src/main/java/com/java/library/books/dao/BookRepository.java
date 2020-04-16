package com.java.library.books.dao;

import com.java.library.books.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAllById(Iterable<Long> iterable);

    @Override
    Optional<Book> findById(Long aLong);

    @Override
    Iterable<Book> findAll();

    @Override
    Book save(Book book);

    @Override
    void delete(Book book);

    @Override
    void deleteById(Long aLong);
}
