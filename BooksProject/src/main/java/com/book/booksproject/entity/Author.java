package com.book.booksproject.entity;

import com.book.booksproject.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@SQLDelete(sql = "update authers set is_deleted = true where id = ?")
//@Where(clause = "is_deleted = false")
@Entity
@Table(name = "authors")
@Setter
@Getter
@RequiredArgsConstructor

public class Author extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    private String ipAddress;
    private String email;
    private long bookCount;

//	@NotEmpty
//	@JsonManagedReference
//	@OneToMany(mappedBy = "author" , cascade = CascadeType.ALL)
//	private List<Book> books = new ArrayList<>();

    private String imagePath;


}