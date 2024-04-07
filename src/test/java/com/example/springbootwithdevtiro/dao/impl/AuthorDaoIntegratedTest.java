package com.example.springbootwithdevtiro.dao.impl;

import java.util.List;
import java.util.Optional;

import com.example.springbootwithdevtiro.dao.impl.TestUtils.TestUtil;
import com.example.springbootwithdevtiro.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorDaoIntegratedTest {

    private  final AuthorDaoImpl underTest;

    @Autowired
    public AuthorDaoIntegratedTest(AuthorDaoImpl underTest) {
        this.underTest = underTest;
    }

    @Test

    public void testThatDataPassAndRetriveCorrectly(){
        underTest.create(TestUtil.authorObj());
        Optional<Author> result = underTest.find(TestUtil.authorObj().getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(TestUtil.authorObj());

    }

    @Test
    public void testThatManyDataPassAndRetriveCorrectly(){
        underTest.create(TestUtil.authorObj());
        underTest.create(TestUtil.authorObj2());
        underTest.create(TestUtil.authorObj3());
        underTest.create(TestUtil.authorObj4());
        List<Author> result = underTest.findAll();
        assertThat(result).hasSize(4).containsExactly(TestUtil.authorObj(),TestUtil.authorObj2(),TestUtil.authorObj3(),TestUtil.authorObj4());

    }

    @Test
    public  void  testThatUpdateMethodWithData(){

        Author author=TestUtil.authorObj();
        underTest.create(author);
        author.setName("Moh___Q");
        underTest.update(
                1L,author

        );
        Optional<Author> result = underTest.find(1L);

        result.isPresent();

        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void  setUnderTestDeleteData(){

        underTest.create(TestUtil.authorObj());
        underTest.delete(TestUtil.authorObj().getId());
        Optional<Author> result = underTest.find(1L);
        System.out.println(result.isPresent());;

    }
}
