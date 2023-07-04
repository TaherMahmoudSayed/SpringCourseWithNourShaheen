package com.book.booksproject.entity;

import com.book.booksproject.base.BaseEntity;
import com.book.booksproject.validator.IpAddress;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.ArrayList;
import java.util.List;

//@SQLDelete(sql = "update authers set is_deleted = true where id = ?")
//@Where(clause = "is_deleted = false")
@Entity
@Table(name = "authors")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "update authors set is_deleted = true where id = ?")
@Where(clause = "is_deleted=false")
public class Author extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "author_id")
    private Long id;
    private String fullName;
    @IpAddress
    private String ipAddress;
    @Email(message = "{validation.constraints.email.message}")
    private String email;
    @Formula("(select count(*) from books book where book.author_id = author_id)")
    private long bookCount;

	@JsonManagedReference
	@OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
	private List<Book> books = new ArrayList<>();
    private String imagePath;


}