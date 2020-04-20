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
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


class LibraryDataControllerTest extends AbstractTest {

    @Autowired
    public LibraryDataController libraryDataController;

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

    @Test
    void getAllBooksFromLibrary() throws Exception{
        String uri = "/library/api/getAllBooks/1";
        MvcResult mvcResult = super.mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Library libraryList  = super.mapFromJson(content, Library.class);


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