package com.java.library.books.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.library.books.AbstractTest;
import com.java.library.books.dao.LibraryRepository;
import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LibraryDataControllerTest extends AbstractTest {

    @MockBean
    public LibraryDataController libraryDataControllerMock;

    @Mock
    public LibraryRepository libraryRepository;

    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void createLibrary() throws Exception {

        String uri = "/library/api/createlibrary";
        Library libraryObj = libraryObjectCreation();
        String inputJson = super.mapToJson(libraryObj);

        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Library libraryList = super.mapFromJson(content, Library.class);
        assertNotNull(libraryList);

    }

    private Library libraryObjectCreation() {
        Library library = new Library();
        library.setLibraryName("Bangalore");


        Book book1 = new Book();
        book1.setAuthor("ABV");
        book1.setIsbn("IDGB453543");
        book1.setPublisher("Tata McGraaw:: ");
        book1.setLanguage("English");
        book1.setLibrary(library);
        Book book2 = new Book();
        book2.setAuthor("ABdsfdfV");
        book2.setIsbn("IDGBfds453543");
        book2.setPublisher("Tatafdd McGraaw:: ");
        book2.setLanguage("Engliddsh");
        book2.setLibrary(library);
        //bookManagementService.save(book1);
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        bookSet.add(book2);
        library.setBookList(bookSet);
        return library;
    }

    private List<Book> bookList(){
        Library library = new Library();
        library.setLibraryName("Bangalore");


        Book book1 = new Book();
        book1.setAuthor("ABV");
        book1.setIsbn("IDGB453543");
        book1.setPublisher("Tata McGraaw:: ");
        book1.setLanguage("English");
        book1.setLibrary(library);
        Book book2 = new Book();
        book2.setAuthor("ABdsfdfV");
        book2.setIsbn("IDGBfds453543");
        book2.setPublisher("Tatafdd McGraaw:: ");
        book2.setLanguage("Engliddsh");
        book2.setLibrary(library);
        //bookManagementService.save(book1);
        List<Book> bookSet = new ArrayList<>();
        bookSet.add(book1);
        bookSet.add(book2);
        library.setBookList(bookSet);
        return bookSet;
    }

    @Test
    void getAllBooksFromLibrary() throws Exception{
        String uri = "/library/api/getAllBooks/1";
        when(libraryDataControllerMock.getAllBooksFromLibrary(1l)).thenReturn(bookList());

        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();


        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Book[] libraryList  = super.mapFromJson(content, Book[].class);

        assertNotNull(libraryList);
        assertEquals(2,libraryList.length);
        assertEquals("IDGB453543",libraryList[0].getIsbn());
        assertEquals("ABV",libraryList[0].getAuthor());
        assertNull(libraryList[0].getTitle());

        assertEquals("IDGBfds453543",libraryList[1].getIsbn());
        assertEquals("ABdsfdfV",libraryList[1].getAuthor());
        assertNull(libraryList[1].getTitle());



    }

    @Test
    void addBookToLibrary() {


    }



    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}