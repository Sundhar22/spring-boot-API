//package com.example.springbootwithdevtiro.dao.impl;
//
//import com.example.springbootwithdevtiro.dao.impl.AuthorDaoImpl;
//import com.example.springbootwithdevtiro.dao.impl.TestUtils.TestUtil;
//import com.example.springbootwithdevtiro.persistence.Repositories.entity.Author;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentMatchers;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class AuthorDaoImplTest {
//    @Mock
//    private JdbcTemplate template;
//    @InjectMocks
//   private   AuthorDaoImpl underTest;
//
//    @Test
//    public void testThatDataPassingCorrectly() {
//        Author obj = TestUtil.authorObj();
//        underTest.create(obj);
//
//        verify(template).update(
//                eq("insert into authors (id,name,age) values (?,?,?)"),
//                eq(obj.getId()), eq(obj.getName()), eq(obj.getAge())
//        );
//    }
//
//    @Test
//    public void  testThatDataIsFindingAuthorCorrectly(){
//        underTest.find(1L);
//        verify(template).query(
//                eq("Select * from authors where id = ? Limit 1"), ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any(),eq(1L));
//
//    }
//
//    @Test
//    public void testThatDataIsFindingAllAuthorsCorrectly(){
//        underTest.findAll();
//        verify(template)
//                .query(eq("Select * From authors")
//                        ,ArgumentMatchers.<AuthorDaoImpl.AuthorRowMapper>any());
//    }
//
//    @Test
//    public void testThatDataUpdateInDatabase(){
//        Author author = TestUtil.authorObj();
//        underTest.update(1L,author);
//        verify(template).update(
//                "UPDATE authors set id=? ,name=?,age=?where id=?",
//                author.getId()
//                    ,author.getName(),
//                    author.getAge(),
//                        1L
//        );
//    }
//    @Test
//    public void testingThatDeleteData(){
//        underTest.create(TestUtil.authorObj());
//        underTest.delete(1L);
//        verify(template).update("Delete From authors Where id =?",1L);
//
//    }
//
//
//
//}
