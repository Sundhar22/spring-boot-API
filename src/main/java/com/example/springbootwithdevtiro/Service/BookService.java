package com.example.springbootwithdevtiro.Service;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookEntity createBook(String isbn, BookEntity Book);

    List<BookEntity> findAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(String isbn);

    Boolean isExist(String isbn);

    BookEntity patchUpdate(String isbn, BookEntity bookEntity);

    void delete(String isbn);
}
