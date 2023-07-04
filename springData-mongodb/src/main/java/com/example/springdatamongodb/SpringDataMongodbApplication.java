package com.example.springdatamongodb;

import com.example.springdatamongodb.entity.Author;
import com.example.springdatamongodb.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringDataMongodbApplication implements CommandLineRunner {
    private final AuthorService authorService;
    public static void main(String[] args) {
        SpringApplication.run(SpringDataMongodbApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        if(authorService.findAll().isEmpty()){
            Author author = new Author();
            author.setName("Mohamed");
            author.setEmail("moh@gmail.com");
            author.setPhone("012254254");

            Author author1 = new Author();
            author1.setName("Ali");
            author1.setEmail("ali@gmail.com");
            author1.setPhone("0155254254");

            Author author2 = new Author();
            author2.setName("Omer");
            author2.setEmail("omer@gmail.com");
            author2.setPhone("015555524");

            authorService.insertAll(Arrays.asList(author, author1, author2));

        }
    }
}
