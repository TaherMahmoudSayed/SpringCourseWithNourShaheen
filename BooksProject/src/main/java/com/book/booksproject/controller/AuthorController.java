package com.book.booksproject.controller;

import com.book.booksproject.entity.Author;
import com.book.booksproject.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("get/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        var author = authorService.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        try {

            var savedAuthor = authorService.addAuthor(author);
            return ResponseEntity.ok(author);

        } catch (Exception ex) {
            return null;
        }

    }

    @PutMapping("/update")
    public ResponseEntity<Author> updateAuthor(@RequestBody Author author) {
        try {
            var updatedAuthor = authorService.updateAuthor(author);
            return ResponseEntity.ok(updatedAuthor);
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAuthor(@PathVariable Long id) {
        try {
            String response = authorService.deleteAuthor(id);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            return null;
        }
    }
}
