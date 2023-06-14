package com.book.booksproject.service;

import com.book.booksproject.entity.Author;
import com.book.booksproject.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    public Author getAuthor(Long id) {
        var author = authorRepository.findById(id).orElse(null);
        return author;
    }

    public Author addAuthor(Author author) {
        try {
            if (author.equals(null)) {
                throw new RuntimeException();
            }
            var savedAuthor = authorRepository.save(author);
            return savedAuthor;
        } catch (Exception ex) {
            return null;
        }

    }

    public Author updateAuthor(Author author) {
        try {
            if (author.equals(null)) {
                throw new RuntimeException();
            }
            var savedAuthor = authorRepository.save(author);
            return savedAuthor;
        } catch (Exception ex) {
            return null;
        }
    }

    public String deleteAuthor(Long id) {
        try {
            var author = authorRepository.findById(id).orElse(null);
            authorRepository.delete(author);
            return "deleted";
        } catch (Exception ex) {
            return "error on deletion";
        }
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public List<Author> insertAll(List<Author> authors) {
        return authorRepository.saveAll(authors);
    }
}
