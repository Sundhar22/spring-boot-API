package com.example.springbootwithdevtiro.Mapper.impl;

import com.example.springbootwithdevtiro.Mapper.Mapper;
import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import com.example.springbootwithdevtiro.presentation.DTO.BookDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookDTO, BookEntity> {

    private final ModelMapper mapper;

    public BookMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public BookDTO toDTO(BookEntity entity) {
        return mapper.map ( entity, BookDTO.class );
    }

    @Override
    public BookEntity toEntity(BookDTO dto) {
        return mapper.map ( dto , BookEntity.class );
    }
}
