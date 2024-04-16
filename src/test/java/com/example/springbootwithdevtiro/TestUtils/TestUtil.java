package com.example.springbootwithdevtiro.TestUtils;

import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import com.example.springbootwithdevtiro.presentation.DTO.AuthorDTO;
import com.example.springbootwithdevtiro.presentation.DTO.BookDTO;

public final class TestUtil {
    private TestUtil() {

    }

    public static AuthorEntity authorObj() {
        return AuthorEntity.builder ( )
                           .id ( 1L )
                           .age ( 25 )
                           .name ( "Moses" ).build ( );
    }

    public static AuthorEntity authorObj2() {
        return AuthorEntity.builder ( )
                           .id ( 2L )
                           .age ( 23 ).name ( "Quincy" )
                           .build ( );
    }

    public static AuthorEntity authorObj3() {
        return AuthorEntity.builder ( )
                           .id ( 3L )
                           .age ( 54 )
                           .name ( "Wendy" )
                           .build ( );
    }

    public static AuthorEntity authorObj4() {
        return AuthorEntity.builder ( )
                           .id ( 4L )
                           .age ( 34 )
                           .name ( "Linda" )
                           .build ( );
    }

    public static BookEntity bookDaoObj(final AuthorEntity author) {
        return BookEntity.builder ( )
                         .isbn ( "1234" )
                         .tittle ( "The book of life" )
                         .author ( author ).build ( );
    }

    public static BookDTO bookDToObj(final AuthorDTO author) {
        return BookDTO.builder ( )
                      .isbn ( "1234" )
                      .tittle ( "The book of life" )
                      .author ( author ).build ( );
    }

    public static BookEntity bookDToObj2(final AuthorEntity author) {
        return BookEntity.builder ( )
                         .tittle ( "The day of the lord" ).author ( author )
                         .isbn ( "2323" ).build ( );
    }

    public static BookEntity bookDaoObj2(final AuthorEntity author) {
        return BookEntity.builder ( )
                         .tittle ( "The day of the lord" ).author ( author )
                         .isbn ( "2323" ).build ( );
    }

    public static BookEntity bookDaoObj3(final AuthorEntity author) {

        return BookEntity.builder ( )
                         .isbn ( "23452" )
                         .author ( author ).tittle ( "east with Guard" ).build ( );
    }

    public static BookEntity bookDaoObj4(final AuthorEntity author) {

        return BookEntity.builder ( )
                         .isbn ( "432" )
                         .tittle ( "The life" )
                         .author ( author ).build ( );
    }
}
