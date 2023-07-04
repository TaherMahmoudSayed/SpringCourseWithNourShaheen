package com.book.booksproject.controller;

import com.book.booksproject.entity.Author;
import com.book.booksproject.error.DuplicatedRecordEX;
import com.book.booksproject.error.ErrorResponse;
import com.book.booksproject.error.FileStorageEX;
import com.book.booksproject.error.RecordNotFoundEX;
import com.book.booksproject.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/v1/authors")
@Validated
@Tag(name = "Author APIs")//swagger tag name
public class AuthorController {
    private final AuthorService authorService;

    @Operation(summary = "get an author by it ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Found the author",
                    content = {@Content(mediaType = "Application/json",
                            schema =@Schema(implementation = Author.class))}),
            @ApiResponse(responseCode = "404",description = "Author Not Found",
                    content = {@Content(mediaType = "Application/json",
                            schema =@Schema(implementation = ErrorResponse.class))}),

    })
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable Long id) {
        var author = authorService.getAuthor(id);
        return ResponseEntity.ok(author);
    }

    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody @Valid Author author) {

            var savedAuthor = authorService.addAuthor(author);
            return ResponseEntity.ok(author);


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
    @GetMapping("getAll")
    public ResponseEntity<List<Author>> getAllAuthors(){
        return ResponseEntity.ok().body(authorService.findAll());
    }
    @GetMapping("getByEmail/{email}")
    public ResponseEntity<?> getAuthorByEmail(@PathVariable String email){

       var author= authorService.findAuthorByEmail(email);
       return ResponseEntity.ok().body(author);
    }
    @ExceptionHandler(FileStorageEX.class)
    public ResponseEntity<ErrorResponse> handleAuthorNotFound(RecordNotFoundEX exc) {
        ErrorResponse error=ErrorResponse.builder()
                .dateTime(LocalDateTime.now())
                .message(exc.getMessage())
                .success(false).build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
