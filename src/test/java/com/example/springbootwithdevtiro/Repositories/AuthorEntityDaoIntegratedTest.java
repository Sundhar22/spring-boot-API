package com.example.springbootwithdevtiro.Repositories;

import java.util.Optional;


import com.example.springbootwithdevtiro.TestUtils.TestUtil;

import com.example.springbootwithdevtiro.persistence.Repositories.AuthorRepository;
import com.example.springbootwithdevtiro.persistence.entity.AuthorEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.example.springbootwithdevtiro.TestUtils.TestUtil.authorObj;
import static com.example.springbootwithdevtiro.TestUtils.TestUtil.authorObj2;
import static com.example.springbootwithdevtiro.TestUtils.TestUtil.authorObj3;
import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityDaoIntegratedTest {

    private  final AuthorRepository underTest;

    @Autowired
    public AuthorEntityDaoIntegratedTest(AuthorRepository underTest) {
        this.underTest = underTest;
    }

    @Test
    public void testThatDataPassAndRetriveCorrectly(){
        underTest.save ( authorObj());
        Optional<AuthorEntity> result = underTest.findById ( authorObj().getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo( authorObj());

    }

    @Test
    public void testThatManyDataPassAndRetriveCorrectly(){
        underTest.save ( authorObj());
        underTest.save( authorObj2());
        underTest.save( authorObj3());
        underTest.save(TestUtil.authorObj4());
        Iterable<AuthorEntity> result = underTest.findAll ();
        assertThat(result).hasSize(4).containsExactly( authorObj(), authorObj2(), authorObj3(),TestUtil.authorObj4());

    }

    @Test
    public  void  testThatUpdateMethodWithData(){

        AuthorEntity author= authorObj();
        underTest.save(author);
        author.setName("Moh___Q");
        underTest.save (
                author

        );
        Optional<AuthorEntity> result = underTest.findById (1L);

        assertThat(result).isPresent();

        assertThat(result.get()).isEqualTo(author);
    }

    @Test
    public  void  setUnderTestDeleteData(){

        underTest.save( authorObj());
        underTest.deleteById ( authorObj().getId());
        Optional<AuthorEntity> result = underTest.findById (1L);
        System.out.println(result.isPresent());;

    }

    @Test
    public void customLessThanAge(){
        AuthorEntity author = authorObj( );
        AuthorEntity author2 = authorObj2 ( );


        underTest.save ( author );
        underTest.save (  author2);
        underTest.save ( authorObj3 () );

        Iterable<AuthorEntity> result = underTest.ageLessThan( 50);

        assertThat ( result ).hasSize ( 2 ).containsExactly ( author,author2 );
    }
    @Test
    public   void customTest(){
        AuthorEntity author = authorObj( );
        AuthorEntity author2 = authorObj2 ( );


        underTest.save ( author );
        underTest.save (  author2);
        underTest.save ( authorObj3 () );

        Iterable<AuthorEntity> result = underTest.authorAgeShouldBeLessThan(50);

        assertThat ( result ).hasSize ( 2 ).containsExactly ( author,author2 );

    }

}
