package com.book.booksproject.base;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.yaml.snakeyaml.events.Event;

@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T, Event.ID> {
    @Modifying
    @Transactional
    @Query("update #{#entityName} t SET t.statusCode = :statusCode WHERE t.id = :id")
    void updateEntity(@Param("id") ID id, @Param("statusCode") String statusCode);
}
