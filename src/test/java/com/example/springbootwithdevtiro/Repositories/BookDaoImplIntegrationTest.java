package com.example.springbootwithdevtiro.dao.impl;

import java.util.Optional;

import com.example.springbootwithdevtiro.persistence.Repositories.AuthorRepository;
import com.example.springbootwithdevtiro.persistence.Repositories.BookRepository;
import com.example.springbootwithdevtiro.TestUtils.TestUtil;

import com.example.springbootwithdevtiro.persistence.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookDaoImplIntegrationTest {

    private final AuthorRepository authorTest;
    private final BookRepository underTest;
    @Autowired
    public BookDaoImplIntegrationTest(AuthorRepository authorTest, BookRepository underTest) {
        this.authorTest = authorTest;
        this.underTest = underTest;
    }

    @Test
    public void TestingThatIntegrationTest(){
        //        authorTest.save ();
        BookEntity book =TestUtil.bookDaoObj(TestUtil.authorObj());
        underTest.save (book);
        Optional<BookEntity> result= underTest.findById (book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void  testingThatFindAllWithData(){
        var book =TestUtil.bookDaoObj(TestUtil.authorObj());
        var book2 =TestUtil.bookDaoObj2 (TestUtil.authorObj());
        var book3 =TestUtil.bookDaoObj3(TestUtil.authorObj());
        var book4 =TestUtil.bookDaoObj4(TestUtil.authorObj());


        underTest.save(book);
        underTest.save(book2);
        underTest.save(book3);
        underTest.save(book4);

        Iterable<BookEntity> result = underTest.findAll();
        assertThat(result).hasSize(4).containsExactlyInAnyOrder(book,book2,book3,book4);
    }

    @Test
    public  void testThatUpdating(){
        authorTest.save(TestUtil.authorObj());
        var book=TestUtil.bookDaoObj(TestUtil.authorObj());
        underTest.save(book);
        book.setTittle ( "Check__T" );
        underTest.save (book);
        Optional<BookEntity> books = underTest.findById( book.getIsbn());
        books.isPresent ();
        assertThat ( books.get () ).isEqualTo ( book );
    }

    @Test

    public  void testThatDeleteData(){
        authorTest.save(TestUtil.authorObj());
        var book=TestUtil.bookDaoObj(TestUtil.authorObj());
        underTest.save(book);

        underTest.deleteById ( book.getIsbn () );

        Optional<BookEntity> result = underTest.findById ( book.getIsbn ( ) );
        assertThat ( result).isEmpty ();
    }

}
