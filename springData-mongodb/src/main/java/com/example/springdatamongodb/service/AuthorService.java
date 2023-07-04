package com.example.springdatamongodb.service;


import com.example.springdatamongodb.entity.Author;
import com.example.springdatamongodb.repository.AuthorCustomRepository;
import com.example.springdatamongodb.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepo;
    private final AuthorCustomRepository customRepository;


    public Author findById(String id) {

        return authorRepo.findById(id).get();
    }

    public List<Author> findAll() {

        return authorRepo.findAll();
    }

    public Author insert(Author entity) {

        if (entity.getId() != null) {

            throw new RuntimeException();
        }

        return authorRepo.insert(entity);
    }

    public List<Author> insertAll(List<Author> entity) {
        if(entity==null)
            throw new IllegalArgumentException("arg shouldn't be null");
        return authorRepo.insert(entity);
    }

    public Author update(Author entity) {

        Author author = findById(entity.getId());
        author.setName(entity.getName());
        author.setEmail(entity.getEmail());
        author.setPhone(entity.getPhone());

        return authorRepo.save(author);
    }
    public void updateByEmail(String email,String name,String phone){
        customRepository.updateEmail(email,name,phone);
        log.info("updated success");
    }

    public Author findAuthorByEmail(String email){
        return authorRepo.findAuthorByEmail(email);
    }





    public void deleteById(String id) {

        authorRepo.deleteById(id);
    }
}
