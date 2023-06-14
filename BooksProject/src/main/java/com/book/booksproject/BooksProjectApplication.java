package com.book.booksproject;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BooksProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BooksProjectApplication.class, args);
        log.info("app is starting");
    }

}
