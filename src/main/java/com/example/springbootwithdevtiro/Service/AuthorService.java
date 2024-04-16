package com.example.springbootwithdevtiro.Service;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {
    AuthorEntity saveAuthor(AuthorEntity author);

    List<AuthorEntity> findAll();

    Optional<AuthorEntity> findOne(Long id);

    Boolean isIdExist(Long id);

    AuthorEntity patch(Long id, AuthorEntity dto);

    void delete(Long id);
}
