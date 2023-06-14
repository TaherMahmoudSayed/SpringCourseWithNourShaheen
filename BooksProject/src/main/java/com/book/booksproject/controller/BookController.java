package com.book.booksproject.controller;

import com.book.booksproject.dto.BookDto;
import com.book.booksproject.entity.Book;
import com.book.booksproject.mapper.Mapper;
import com.book.booksproject.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping("eager/{id}")
    public ResponseEntity<?> findByIdEager(@PathVariable Long id) {

        BookDto dto = bookService.getBookAndAuthor(id);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("lazy/{id}")
    public ResponseEntity<?> findByIdLazy(@PathVariable Long id) {

        BookDto dto = bookService.getBook(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(bookService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody BookDto dto) {

        Book book = Mapper.BookDtoToBook(dto);

        return ResponseEntity.ok(bookService.insert(book));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody Book entity) {

        return ResponseEntity.ok(bookService.update(entity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/auther/{id}")
    public ResponseEntity<?> deleteByAutherId(@PathVariable Long id) {

        return ResponseEntity.ok(bookService.deleteByAuthorId(id));
    }

}
