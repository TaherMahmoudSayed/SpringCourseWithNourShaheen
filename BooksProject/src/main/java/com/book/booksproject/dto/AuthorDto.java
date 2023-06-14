package com.book.booksproject.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder

public class AuthorDto {


    private String name;

    private String ipAddress;

    private String email;

    private long bookCount;

    private String imagePath;

    private LocalDateTime createdDate;

}
