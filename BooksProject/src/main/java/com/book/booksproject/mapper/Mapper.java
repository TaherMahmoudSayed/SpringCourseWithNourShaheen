package com.book.booksproject.mapper;

import com.book.booksproject.dto.AuthorDto;
import com.book.booksproject.dto.BookDto;
import com.book.booksproject.entity.Author;
import com.book.booksproject.entity.Book;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public static AuthorDto authorToAuthorDto(Author author) {
        return AuthorDto.builder().bookCount(author.getBookCount())
                .name(author.getFullName())
                .email(author.getEmail())
                .createdDate(LocalDateTime.now())
                .imagePath(author.getImagePath())
                .ipAddress(author.getIpAddress())
                .build();
    }

    public static BookDto bookToBookDtoWithAuthor(Book book) {
        return BookDto.builder()
                .author(Mapper.authorToAuthorDto(book.getAuthor()))
                .name(book.getName())
                .price(book.getPrice())
                .authorEmail(book.getAuthor().getEmail())
                .build();
    }

    public static BookDto bookToBookDto(Book book) {
        return BookDto.builder()
                .name(book.getName())
                .price(book.getPrice())
                .build();
    }

    public static Book BookDtoToBook(BookDto dto) {
        return Book.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .author(null)
                .discounted(0)
                .build();
    }


}
