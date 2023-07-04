package com.book.booksproject.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Path;

@Configuration
@Setter
@Getter
public class StorageProperties {

    private final Path baseFileLocation= Path.of("D:\\Projects\\nour shaheen cource\\SpringCourseWithNourShaheen\\BooksProject\\filesServer");

}
