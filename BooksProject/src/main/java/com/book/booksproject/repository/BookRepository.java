package com.book.booksproject.repository;

import com.book.booksproject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    int deleteByAuthorId(Long id);

    @Override
        //@EntityGraph(attributePaths = {"author", "author.cardInfo"}**nested objects, author for example has an object called cardInfo**)
        //@EntityGraph(attributePaths = {"author"})
    Optional<Book> findById(Long aLong);

}
