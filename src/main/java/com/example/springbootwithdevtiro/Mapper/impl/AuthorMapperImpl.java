package com.example.springbootwithdevtiro.Mapper.impl;

import com.example.springbootwithdevtiro.Mapper.Mapper;
import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import com.example.springbootwithdevtiro.presentation.DTO.AuthorDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorDTO,AuthorEntity> {

    private final ModelMapper mapper;

    public AuthorMapperImpl(ModelMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public AuthorDTO toDTO(AuthorEntity entity) {
        return mapper.map(entity,AuthorDTO.class);
    }

    @Override
    public AuthorEntity toEntity(AuthorDTO dto) {
        return mapper.map ( dto, AuthorEntity.class );
    }
}
