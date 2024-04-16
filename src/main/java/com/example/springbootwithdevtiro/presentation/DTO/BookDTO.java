package com.example.springbootwithdevtiro.presentation.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private    String isbn;
    private    String tittle;
    private AuthorDTO author;
}
