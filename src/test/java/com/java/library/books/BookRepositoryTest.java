package com.java.library.books;

import com.java.library.books.controller.LibraryDataController;
import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    public LibraryDataController libraryDataController;

    @Test
    public void testGetAllBooksByLibraryId(){
            //given
        long libId = 1l;

        //when
       // Library generatedLibrary = libraryDataController.createLibrary(library);
        List<Book> allBooksFromLibrary = libraryDataController.getAllBooksFromLibrary(libId);

        //then
        Assert.assertNotNull(allBooksFromLibrary);
        assertEquals(2,allBooksFromLibrary.size());
        assertEquals( java.util.Optional.of(2l), java.util.Optional.ofNullable(allBooksFromLibrary.get(0).getId()));
        assertEquals( java.util.Optional.of(3l), java.util.Optional.ofNullable(allBooksFromLibrary.get(1).getId()));

    }

    @Test
    public void updateBook(){
        //given
        long bookId = 2l;

        //when
        // Library generatedLibrary = libraryDataController.createLibrary(library);
        Library library = new Library();
        library.setLibraryName("Bangalore");

        Book book1 = new Book();
        book1.setAuthor("ABV12");
        book1.setIsbn("IDGB453543");
        book1.setPublisher("Tata McGraaw:: ");
        book1.setLanguage("English");
        book1.setLibrary(library);
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        library.setBookList(bookSet);
        ResponseEntity<Book> bookResponseEntity = libraryDataController.updateBook(bookId, book1);

        //then
        Assert.assertNotNull(bookResponseEntity);
        assertEquals("ABV12",bookResponseEntity.getBody().getAuthor());


    }

}
