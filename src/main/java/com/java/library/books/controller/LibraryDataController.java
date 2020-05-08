package com.java.library.books.controller;

import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import com.java.library.books.service.BookManagementService;
import com.java.library.books.service.LibraryManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/library/api/")
public class LibraryDataController {

        public static Logger logger = LoggerFactory.getLogger(LibraryDataController.class);

        @Autowired
        public BookManagementService bookManagementService;

        @Autowired
        public LibraryManagementService libraryManagementService;

        @GetMapping("/getAllBooks/{libraryId}")
        @ResponseStatus(HttpStatus.OK)
        public @ResponseBody List<Book> getAllBooksFromLibrary(@PathVariable("libraryId") long id){
                return libraryManagementService.getAllBooksFromLibrary(id);
        }

        @PostMapping("/save/{libraryId}")
        @ResponseStatus(HttpStatus.OK)
        public @ResponseBody Library addBookToLibrary(@PathVariable("libraryId") long id, @RequestBody Book book) {
                logger.info("Libray Controller: START : addBookToLibrary ");
                Optional<Library> getLibraryDetails = Optional.ofNullable(libraryManagementService.findById(id));
                if (getLibraryDetails.isPresent()) {
                        Library library = getLibraryDetails.get();
                        book.setLibrary(library);
                        library.getBookList().add(book);
                        Library addBookToLibrary = libraryManagementService.save(library);
                        return addBookToLibrary;
                }else{
                        return getLibraryDetails.get();
                }
        }

        @PostMapping("/createlibrary")
        public ResponseEntity<Library> createLibrary(@RequestBody Library library){
                logger.info("Libray Controller: START : create New Library ");

                Library saveLibrary = libraryManagementService.save(library);
                if(saveLibrary != null){
                        return new ResponseEntity<>(saveLibrary, HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }

        }

        @PutMapping("/update/{id}")
        public ResponseEntity<Book> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
                Optional<Book> bookDetails = bookManagementService.findById(id);

                if (bookDetails.isPresent()) {
                        Book bookFromDB = bookDetails.get();
                        bookFromDB.setTitle(book.getTitle());
                        bookFromDB.setLanguage(book.getLanguage());
                        bookFromDB.setPublisher(book.getPublisher());
                        bookFromDB.setIsbn(book.getIsbn());
                        bookFromDB.setAuthor(book.getAuthor());
                        bookFromDB.setSubject(book.getSubject());
                        bookFromDB.setPages(book.getPages());

                        return new ResponseEntity<>(bookManagementService.save(bookFromDB), HttpStatus.OK);
                } else {
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") long id) {
                try {
                        bookManagementService.deleteById(id);
                        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                } catch (Exception e) {
                        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
                }
        }


}
