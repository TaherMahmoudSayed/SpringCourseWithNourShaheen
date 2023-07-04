package com.example.springdatamongodb.controller;

import com.example.springdatamongodb.entity.Author;
import com.example.springdatamongodb.service.AuthorService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
@Validated

public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {

        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> findAll() {

        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<?> insert(@RequestBody @Valid Author entity) {

        return ResponseEntity.ok(authorService.insert(entity));
    }

    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody @Valid Author entity) {

        return ResponseEntity.ok(authorService.update(entity));
    }
    @PutMapping("update/custom")
    public void updateByEmail(@RequestParam String email,@RequestParam String name,@RequestParam String phone){
        authorService.updateByEmail(email,name,phone);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        authorService.deleteById(id);
        return ResponseEntity.ok(null);
    }
    @GetMapping("find-by-email/{email}")
    public Author findAuthorByEmail( @PathVariable String email){
        return authorService.findAuthorByEmail(email);
    }
}
