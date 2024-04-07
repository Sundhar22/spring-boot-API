package com.example.springbootwithdevtiro.dao.impl;

import com.example.springbootwithdevtiro.dao.impl.BookDaoImpl;
import com.example.springbootwithdevtiro.dao.impl.TestUtils.TestUtil;
import com.example.springbootwithdevtiro.domain.Books;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
public class BooksDaoImplTest {

    @Mock
    private  JdbcTemplate template;

    @InjectMocks
    private  BookDaoImpl underTest ;

    @Test
    public  void testThatDataPassingCorrectly(){
        Books book = TestUtil.bookDaoObj();

        underTest.create(book);
        verify(template).update(
                eq("insert into books(isbn,tittle,authorId) values (?,?,?)"),
                eq(book.getIsbn()),eq(book.getTittle()),eq(book.getAuthorId())
        );

    }
    @Test
    public  void  testingThatGettingBook(){

        underTest.find("921-345-535");

        verify(template).query(
                eq("select * from books where isbn=?"), ArgumentMatchers.<BookDaoImpl.BookRowMapper>any(),eq("921-345-535")
        );
    }

    @Test
    public  void  testingThatGettingAllBooks() {

        underTest.findAll();

        verify(template).query(
                eq("Select * From books"),ArgumentMatchers.<BookDaoImpl.BookRowMapper>any()
        );
    }


    @Test
    public void  checkingThatUpdateOnDb(){

        var book=TestUtil.bookDaoObj();
        underTest.update(book.getIsbn(),book);

        verify(template).update("UPDATE books set isbn=?,tittle=?,authorId=? where isbn=?",
                book.getIsbn(),book.getTittle(),book.getAuthorId(),book.getIsbn()
                );



    }

    @Test
    public  void  checkingThatDeleteFunc(){
        underTest.delete(TestUtil.bookDaoObj().getIsbn());
        verify(template).update("DELETE FROM books WHERE isbn=?",TestUtil.bookDaoObj().getIsbn());

    }
}
