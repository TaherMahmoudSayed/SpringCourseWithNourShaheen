package com.example.springdatamongodb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.lang.annotation.Documented;

@Document(collection= "authors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Author {
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String phone;
}
