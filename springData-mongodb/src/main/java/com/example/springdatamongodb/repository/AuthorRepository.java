package com.example.springdatamongodb.repository;

import com.example.springdatamongodb.entity.Author;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends MongoRepository<Author,String> {

    @Query(
             "{email: '?0'}"
    )
    public Author findAuthorByEmail(String email);
}
