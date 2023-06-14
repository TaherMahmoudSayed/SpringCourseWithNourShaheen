package com.book.booksproject.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class BookDto {
    private String name;
    private double price;
    private AuthorDto author;
    private String authorName;
    private String authorEmail;
    private Boolean isFavorite;


}
