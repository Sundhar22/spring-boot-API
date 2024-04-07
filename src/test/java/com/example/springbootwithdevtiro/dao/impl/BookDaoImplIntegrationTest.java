package com.example.springbootwithdevtiro.dao.impl;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithdevtiro.dao.impl.TestUtils.TestUtil;
import com.example.springbootwithdevtiro.domain.Books;
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

    private final AuthorDaoImpl authorTest;
    private final BookDaoImpl underTest;
    @Autowired
    public BookDaoImplIntegrationTest(AuthorDaoImpl authorTest, BookDaoImpl underTest) {
        this.authorTest = authorTest;
        this.underTest = underTest;
    }

    @Test
    public void TestingThatIntegrationTest(){
        authorTest.create(TestUtil.authorObj());
        underTest.create(TestUtil.bookDaoObj());
        Optional<Books> result= underTest.find(TestUtil.bookDaoObj().getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(TestUtil.bookDaoObj());

    }

    @Test
    public void  testingThatFindAllWithData(){
        authorTest.create(TestUtil.authorObj());
        authorTest.create(TestUtil.authorObj2());
        authorTest.create(TestUtil.authorObj3());
        authorTest.create(TestUtil.authorObj4());

        underTest.create(TestUtil.bookDaoObj());
        underTest.create(TestUtil.bookDaoObj2());
        underTest.create(TestUtil.bookDaoObj3());
        underTest.create(TestUtil.bookDaoObj4());
        List<Books> result = underTest.findAll();
        assertThat(result).hasSize(4).containsExactlyInAnyOrder(TestUtil.bookDaoObj(),TestUtil.bookDaoObj2(),TestUtil.bookDaoObj3(),TestUtil.bookDaoObj4());
    }

    @Test
    public  void testThatUpdating(){
        authorTest.create(TestUtil.authorObj());
        var book=TestUtil.bookDaoObj();
        underTest.create(book);
        book.setTittle ( "Check__T" );
        underTest.update(book.getIsbn(),book);
        Optional<Books> books = underTest.find ( book.getIsbn ( ) );
        books.isPresent ();
        assertThat ( books.get () ).isEqualTo ( book );
    }

    @Test

    public  void testThatDeleteData(){
        authorTest.create(TestUtil.authorObj());
        var book=TestUtil.bookDaoObj();
        underTest.create(book);

        underTest.delete ( book.getIsbn () );

        Optional<Books> result = underTest.find ( book.getIsbn ( ) );
        assertThat ( result).isEmpty ();
    }

}
