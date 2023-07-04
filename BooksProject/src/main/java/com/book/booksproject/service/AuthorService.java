package com.book.booksproject.service;

import com.book.booksproject.entity.Author;
import com.book.booksproject.error.DuplicatedRecordEX;
import com.book.booksproject.error.RecordNotFoundEX;
import com.book.booksproject.repository.AuthorRepository;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final MessageSource messageSource;
    public Author getAuthor(Long id) {
        var author = authorRepository.findById(id).orElse(null);
        if(author==null)
            throw new RecordNotFoundEX("Author not found(-_-)");
        return author;
    }

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public Author addAuthor(Author author) {
        try {
            StringBuilder msg=new StringBuilder();
            if (author.equals(null) ) {
                throw new RuntimeException();
            }
            if(this.findAuthorByEmail(author.getEmail())!=null){
                 msg.append(messageSource.getMessage("error.duplicated.record.message",
                         Arrays.asList(author.getEmail()).toArray(),
                        LocaleContextHolder.getLocale()));

                throw new DuplicatedRecordEX(msg.toString());
            }

            var savedAuthor = authorRepository.save(author);
            return savedAuthor;
        } catch (DuplicatedRecordEX ex) {
            throw new DuplicatedRecordEX(ex.getMessage());
        }

    }
    public Author findAuthorByEmail(String email){
        var author=authorRepository.findAuthorByEmail(email).orElse(null);
        return author;
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
