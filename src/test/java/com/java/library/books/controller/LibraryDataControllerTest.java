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

import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class LibraryDataControllerTest extends AbstractTest {

    @MockBean
    public LibraryDataController libraryDataControllerMock;


    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }

    @Test
    void createLibrary() throws Exception {
        //given
        String uri = "/library/api/createlibrary";
        Library libraryObj = libraryObjectCreation();
        String inputJson = super.mapToJson(libraryObj);

        //when
        when(libraryDataControllerMock.createLibrary(libraryObj)).thenReturn(ResponseEntity.of(Optional.of(libraryObj)));

        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }



    @Test
    void getAllBooksFromLibrary() throws Exception{
       //given
        String uri = "/library/api/getAllBooks/1";

        //when
        when(libraryDataControllerMock.getAllBooksFromLibrary(1l)).thenReturn(bookList());

        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

        //then
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
    void addBookToLibrary() throws Exception {
        //given
        String uri = "/library/api/save/1";
        Library library = new Library();
        library.setId(1l);
        library.setLibraryName("Bangalore");
        Book book3 = new Book();
        book3.setId(4l);
        book3.setAuthor("Chetan");
        book3.setIsbn("ISGDBKF");
        book3.setPublisher("Tata McGraaw Hill:: ");
        book3.setLanguage("English");
        book3.setLibrary(library);

        //when
        when(libraryDataControllerMock.addBookToLibrary(1l,book3)).thenReturn(library);

        String inputJson = super.mapToJson(book3);
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }



    @Test
    void updateBook() throws Exception{
        //given
        String uri = "/library/api/update/1";
        Library library = new Library();
        library.setId(1l);
        library.setLibraryName("Bangalore");
        Book book3 = new Book();
        book3.setIsbn("ISGDBKF");
        book3.setPublisher("Tata McGraaw Hill:: ");
        book3.setLibrary(library);

        //when
        when(libraryDataControllerMock.updateBook(1l,book3)).thenReturn(ResponseEntity.of(Optional.of(book3)));

        String inputJson = super.mapToJson(book3);
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        //then
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

    }

    @Test
    void deleteBook() {
    }
}