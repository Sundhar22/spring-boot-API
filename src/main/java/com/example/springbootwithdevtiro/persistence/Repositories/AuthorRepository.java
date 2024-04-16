package com.example.springbootwithdevtiro.persistence.Repositories;

import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity, Long>, PagingAndSortingRepository<AuthorEntity, Long> {
    Iterable<AuthorEntity> ageLessThan(int i);

    @Query("select a from AuthorEntity a where a.age <?1")
    Iterable<AuthorEntity> authorAgeShouldBeLessThan(int i);
}
