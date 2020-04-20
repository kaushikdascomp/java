package com.java.library.books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(classes = LibrarymanagementApplication.class)
@WebAppConfiguration
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
public abstract class AbstractTest {

    protected MockMvc mvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }


    public Library libraryObjectCreation() {
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

    public List<Book> bookList(){
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
}
