package com.java.library.books;

import com.java.library.books.controller.LibraryDataController;
import com.java.library.books.domain.Book;
import com.java.library.books.domain.Library;
import com.java.library.books.service.BookManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootApplication
public class LibrarymanagementApplication implements CommandLineRunner{

	private static final Logger log = LoggerFactory.getLogger(LibrarymanagementApplication.class);

	@Autowired
	public LibraryDataController libraryDataController;


	public static void main(String[] args) {
		SpringApplication.run(LibrarymanagementApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Application started::");
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
		Library libraryCreated = libraryDataController.createLibrary(library);

		System.out.println("Data:  "+libraryCreated);
		System.out.println("\nfindAll()");
		List<Book> allBooksFromLibrary = libraryDataController.getAllBooksFromLibrary(1l);
		System.out.println(allBooksFromLibrary);
	}
}
