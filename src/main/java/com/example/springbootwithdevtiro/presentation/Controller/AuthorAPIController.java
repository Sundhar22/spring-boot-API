package com.example.springbootwithdevtiro.presentation.Controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.springbootwithdevtiro.Mapper.Mapper;
import com.example.springbootwithdevtiro.Service.AuthorService;
import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import com.example.springbootwithdevtiro.presentation.DTO.AuthorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorAPIController {

    private final AuthorService authorService;

    private final Mapper<AuthorDTO, AuthorEntity> authorMapper;

    public AuthorAPIController(AuthorService authorService, Mapper<AuthorDTO, AuthorEntity> authorMapper) {
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @PostMapping(value = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO author) {
        var toEntity = authorMapper.toEntity ( author );
        AuthorEntity savedEntity = authorService.saveAuthor ( toEntity );
        return new ResponseEntity<> ( authorMapper.toDTO ( savedEntity ), HttpStatus.CREATED );
    }

    @GetMapping(path = "/authors")
    public List<AuthorDTO> getAllAuthorsData() {
        List<AuthorEntity> authorEntityList = authorService.findAll ( );
        return authorEntityList.stream ( ).map (
                authorMapper::toDTO
        ).collect ( Collectors.toList ( ) );
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> getOne(@PathVariable("id") Long id) {
        Optional<AuthorEntity> author = authorService.findOne ( id );
        return author.map (
                authorEntity -> {
                    AuthorDTO dto =
                            authorMapper.toDTO ( authorEntity );
                    return new ResponseEntity<> ( dto, HttpStatus.OK );
                }
        ).orElse (
                new ResponseEntity<> ( HttpStatus.NOT_FOUND )
        );
    }

    @PutMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {

        if (!authorService.isIdExist ( id )) {
            return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
        }
        dto.setId ( id );
        AuthorEntity author = authorService.saveAuthor (
                authorMapper.toEntity ( dto )
        );
        return new ResponseEntity<> ( authorMapper.toDTO ( author ), HttpStatus.OK );
    }

    @PatchMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> updatePatch(@PathVariable("id") Long id, @RequestBody AuthorDTO dto) {
        if (!authorService.isIdExist ( id )) {
            return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
        }
        AuthorEntity authorEntity = authorService.patch ( id, authorMapper.toEntity ( dto ) );
        return new ResponseEntity<> ( authorMapper.toDTO ( authorEntity ), HttpStatus.OK );
    }

    @DeleteMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable("id") Long id) {
        if (!authorService.isIdExist ( id )) {
            return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
        }
        authorService.delete ( id );
        return new ResponseEntity<> ( HttpStatus.NOT_FOUND );
    }
}
