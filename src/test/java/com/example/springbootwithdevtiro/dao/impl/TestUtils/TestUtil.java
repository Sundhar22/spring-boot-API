package com.example.springbootwithdevtiro.dao.impl.TestUtils;

import com.example.springbootwithdevtiro.domain.Author;
import com.example.springbootwithdevtiro.domain.Books;

public final class TestUtil {
    private TestUtil(){

    }

    public static Author authorObj() {
      return Author.builder()
                              .id(1L)
                              .age(25)
                              .name("Moses").build();

    }
    public static Author authorObj2() {
      return Author.builder()
                              .id(2L)
              .age( 23).name("Quincy")
        .
                              build();

    }
    public static Author authorObj3() {
      return Author.builder()
                              .id(3L)
                              .age(54)
                              .name("Wendy")

                   .build();

    }
    public static Author authorObj4() {
      return Author.builder()
                   .id(4L)
              .age( 34)
                .name("Linda")
                   .build();

    }
    public static Books bookDaoObj() {
        return Books.builder()
                    .isbn("1234")
                    .tittle("The book of life")
                    .authorId(1L).build();
    }public static Books bookDaoObj2() {
        return Books.builder()
                    .tittle("The day of the lord").authorId(1L)
                    .isbn("2323").build();

    }public static Books bookDaoObj3() {
        return Books.builder()
                    .isbn("23452")
                .authorId(2L).tittle("east with Guard").build();

    }public static Books bookDaoObj4() {
        return Books.builder()
                    .isbn("432")
                    .tittle("The life")
                    .authorId(3L).build();
    }
}
