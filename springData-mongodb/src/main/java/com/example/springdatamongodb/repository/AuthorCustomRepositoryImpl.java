package com.example.springdatamongodb.repository;

import com.example.springdatamongodb.entity.Author;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class AuthorCustomRepositoryImpl implements AuthorCustomRepository{
    private final MongoTemplate mongoTemplate;
    @Override
    public void updateEmail(String email, String name, String phone) {
        Query query = new Query(Criteria.where("email").is(email));
        Update update = new Update();
        update.set("name", name);
        update.set("phone", phone);
        UpdateResult   result= mongoTemplate.updateFirst(query,update,Author.class);
        if(result == null)
            System.out.println("No documents updated");
        else
            System.out.println(result.getModifiedCount() + " document(s) updated..");


    }
}
