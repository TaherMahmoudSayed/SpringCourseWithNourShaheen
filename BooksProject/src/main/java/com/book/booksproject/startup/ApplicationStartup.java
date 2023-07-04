package com.book.booksproject.startup;

import com.book.booksproject.entity.Author;
import com.book.booksproject.entity.Book;
import com.book.booksproject.service.AuthorService;
import com.book.booksproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements CommandLineRunner {

    private final AuthorService authorService;
    private final BookService bookService;

    @Override
    public void run(String... args) throws Exception {

        // adding some data for authors
        if (authorService.findAll().isEmpty()) {
            Author author1 = new Author();
            author1.setFullName("ALi");

            Author author2 = new Author();
            author2.setFullName("Mohamed");

            Author auther3 = new Author();
            auther3.setFullName("Ahmed");

            authorService.insertAll(Arrays.asList(author1, author2, auther3));
        }
        // adding some data for books
        if (bookService.findAll().isEmpty()) {
            Book book = new Book();
            book.setName("Java JPA");
            book.setPrice(200);
            book.setAuthor(authorService.getAuthor(1L));

            Book book2 = new Book();
            book2.setName("Data Base Mysql");
            book2.setPrice(300);
            book2.setAuthor(authorService.getAuthor(1L));

            Book book3 = new Book();
            book3.setName("Python");
            book3.setPrice(120);
            book3.setAuthor(authorService.getAuthor(2L));


            bookService.insertAll(Arrays.asList(book, book2, book3));
        }
    }

}
