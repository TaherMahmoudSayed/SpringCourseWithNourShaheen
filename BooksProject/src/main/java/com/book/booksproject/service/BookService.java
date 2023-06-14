package com.book.booksproject.service;

import com.book.booksproject.dto.BookDto;
import com.book.booksproject.entity.Book;
import com.book.booksproject.mapper.Mapper;
import com.book.booksproject.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor

public class BookService {
    private final static String USERS_PROC = ".INSERT_JP_USERS";
    private final BookRepository bookRepo;

    public Book insert(Book book) {

        return bookRepo.save(book);
    }

    public List<Book> insertAll(List<Book> entities) {

        return bookRepo.saveAll(entities);
    }

    public BookDto getBookAndAuthor(Long id) {
        Book book = bookRepo.findById(id).orElse(null);
        BookDto dto = Mapper.bookToBookDtoWithAuthor(book);
        return dto;
    }

    // returns the requested book only with no dependencies
    public BookDto getBook(Long id) {
        Book book = bookRepo.findById(id).orElse(null);
        BookDto dto = Mapper.bookToBookDto(book);
        return dto;
    }

    public Book update(Book entity) {

        Book book = bookRepo.findById((Long) entity.getId()).orElse(null);

        book.setName(entity.getName());

        return update(book);
    }

    public int deleteByAuthorId(Long id) {

        return bookRepo.deleteByAuthorId(id);
    }

    public List<Book> findAll() {
        return bookRepo.findAll();
    }

    public void deleteById(Long id) {
        bookRepo.deleteById(id);
    }
}
