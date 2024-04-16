package com.example.springbootwithdevtiro.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.springbootwithdevtiro.Service.BookService;
import com.example.springbootwithdevtiro.persistence.Repositories.BookRepository;
import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class BookServiceImp implements BookService {

    private final BookRepository repository;

    public BookServiceImp(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public BookEntity createBook(String isbn, BookEntity book) {
        book.setIsbn ( isbn );
        return repository.save ( book );
    }

    @Override
    public List<BookEntity> findAll() {
        return StreamSupport.stream ( repository.findAll ( ).spliterator ( ), false ).collect ( Collectors.toList ( ) );
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return repository.findAll ( pageable );
    }

    @Override
    public Optional<BookEntity> findOne(String isbn) {
        return repository.findById ( isbn );
    }

    @Override
    public Boolean isExist(String isbn) {
        return repository.existsById ( isbn );
    }

    @Override
    public BookEntity patchUpdate(String isbn, BookEntity bookEntity) {
        bookEntity.setIsbn ( isbn );
        return repository.findById ( isbn ).map (
                book -> {

                    Optional.ofNullable ( bookEntity.getTittle ( ) ).ifPresent (
                            book::setTittle
                    );
                    Optional.ofNullable ( bookEntity.getAuthor ( ).getName ( ) ).ifPresent (
                            name -> book.getAuthor ( ).setName ( name )
                    );
                    Optional.ofNullable ( bookEntity.getAuthor ( ).getId ( ) ).ifPresent (
                            id -> book.getAuthor ( ).setId ( id )
                    );
                    Optional.ofNullable ( bookEntity.getAuthor ( ).getAge ( ) ).ifPresent (
                            age -> book.getAuthor ( ).setAge ( age )
                    );
                    return repository.save ( book );
                } ).orElseThrow ( () ->
                new RuntimeException ( "Book Data Not Found" )
        );
    }

    @Override
    public void delete(String isbn) {
        repository.deleteById ( isbn );
    }
}
