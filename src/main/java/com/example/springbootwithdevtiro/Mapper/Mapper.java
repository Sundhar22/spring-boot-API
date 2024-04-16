package com.example.springbootwithdevtiro.Mapper;

public interface Mapper<A,B> {

    A toDTO(B entity);

    B toEntity(A dto);

}
