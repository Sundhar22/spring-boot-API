package com.example.springbootwithdevtiro.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.springbootwithdevtiro.Service.AuthorService;
import com.example.springbootwithdevtiro.persistence.Repositories.AuthorRepository;
import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorServiceImpl implements AuthorService {

    final AuthorRepository repository;

    public AuthorServiceImpl(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorEntity saveAuthor(AuthorEntity author) {
        return repository.save ( author );
    }

    @Override
    public List<AuthorEntity> findAll() {
        return StreamSupport.stream ( repository.findAll ( ).spliterator ( ), false ).collect ( Collectors.toList ( ) );
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return repository.findById ( id );
    }

    @Override
    public Boolean isIdExist(Long id) {
        return repository.existsById ( id );
    }

    @Override
    public AuthorEntity patch(Long id, AuthorEntity entity) {
        entity.setId ( id );
        return repository.findById ( id ).map (
                authorEntity -> {
                    Optional.ofNullable ( entity.getName ( ) ).ifPresent ( authorEntity::setName );
                    Optional.ofNullable ( entity.getAge ( ) ).ifPresent ( authorEntity::setAge );
                    return repository.save ( authorEntity );
                }
        ).orElseThrow (
                () -> new RuntimeException ( "Author Not Found" ) );
    }

    @Override
    public void delete(Long id) {
        repository.deleteById ( id );
    }
}
