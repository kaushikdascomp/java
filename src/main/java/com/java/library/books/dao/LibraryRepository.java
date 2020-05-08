package com.java.library.books.dao;

import com.java.library.books.domain.Library;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LibraryRepository extends CrudRepository<Library,Long> {

    @Override
    Optional<Library> findById(Long aLong);

    @Override
    Iterable<Library> findAll();

    @Override
    Library save(Library library);


}
