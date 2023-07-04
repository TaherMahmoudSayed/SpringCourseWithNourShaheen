package com.example.springdatamongodb.repository;

import com.example.springdatamongodb.entity.Author;

public interface AuthorCustomRepository {
    public void updateEmail(String email,String name,String phone);
}
