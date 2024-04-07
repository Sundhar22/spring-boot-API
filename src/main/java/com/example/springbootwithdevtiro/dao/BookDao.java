package com.example.springbootwithdevtiro.dao;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithdevtiro.domain.Books;
public interface BookDao {
    void create(Books book);

    Optional<Books> find(String s);

    List<Books> findAll();

    void update(String  isbn, Books book);

    void delete(String isbn);
}
