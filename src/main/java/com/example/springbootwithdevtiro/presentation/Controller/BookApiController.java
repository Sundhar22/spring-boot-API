package com.example.springbootwithdevtiro.presentation.Controller;

import java.util.Optional;

import com.example.springbootwithdevtiro.Mapper.Mapper;
import com.example.springbootwithdevtiro.Service.BookService;
import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import com.example.springbootwithdevtiro.presentation.DTO.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookApiController {
    private final BookService service;

    private final Mapper<BookDTO, BookEntity> mapper;

    public BookApiController(final BookService service, final Mapper<BookDTO, BookEntity> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> createOrUpdateBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO book) {
        var bookEntity = mapper.toEntity ( book );
        Boolean exist = service.isExist ( isbn );
        BookEntity result = service.createBook ( isbn, bookEntity );

        if (exist) {
            return new ResponseEntity<> ( mapper.toDTO ( result ), HttpStatus.OK );
        } else {
            return new ResponseEntity<> ( mapper.toDTO ( result ), HttpStatus.CREATED );
        }
    }

    @GetMapping(path = "/books")
    public Page<BookDTO> getAllBook(Pageable pageable) {
        Page<BookEntity> booksList = service.findAll ( pageable );
        return booksList.map ( mapper::toDTO );
    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> getOneBook(@PathVariable String isbn) {
        Optional<BookEntity> book = service.findOne ( isbn );
        return book.map (
                bookEntity -> {
                    BookDTO dto = mapper.toDTO ( bookEntity );
                    return new ResponseEntity<> ( dto, HttpStatus.OK );
                }
        ).orElse (
                new ResponseEntity<> ( HttpStatus.NOT_FOUND )
        );
    }

    @PatchMapping(path = "/books/{isbn}")
    public ResponseEntity<BookDTO> patchTheBook(@PathVariable("isbn") String isbn, @RequestBody BookDTO book) {
        if (!service.isExist ( isbn )) {
            return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
        }
        BookEntity bookEntity = mapper.toEntity ( book );
        BookEntity entity = service.patchUpdate ( isbn, bookEntity );
        System.out.println ( entity );
        return new ResponseEntity<> ( mapper.toDTO ( entity ), HttpStatus.OK );
    }

    @DeleteMapping(path = "/books/{isbn}")

    public ResponseEntity<BookDTO> deleteAuthor(@PathVariable("isbn") String isbn) {
        if (!service.isExist ( isbn )) {
            return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
        }
        service.delete ( isbn );
        return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
    }
}
