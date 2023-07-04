package com.book.booksproject.entity;


import com.book.booksproject.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

//@SQLDelete(sql = "update books set is_deleted = true where id = ?")
//@Where(clause = "is_deleted = false")
@Entity
@Table(name = "books")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double price;
    private String bookImage;
    @Transient //will not be saved on database, computed colum
    private double discounted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @JsonBackReference
    private Author author;


    @PostLoad //will calc the entity one time when loading the entity
    public void calcDiscounted() {
        this.discounted = price * .25;
    }


}