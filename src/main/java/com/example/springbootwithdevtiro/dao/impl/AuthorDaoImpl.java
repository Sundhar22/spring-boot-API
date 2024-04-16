//package com.example.springbootwithdevtiro.dao.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import com.example.springbootwithdevtiro.dao.AuthorDao;
//import com.example.springbootwithdevtiro.persistence.Repositories.entity.Author;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class AuthorDaoImpl implements AuthorDao {
//    private final JdbcTemplate template;
//
//    public AuthorDaoImpl(final JdbcTemplate template) {
//        this.template = template;
//    }
//
//    @Override
//    public void create(Author author) {
//        template.update(
//                "insert into authors (id,name,age) values (?,?,?)",
//                author.getId(),author.getName(),author.getAge()
//        );
//    }
//
//    @Override
//    public Optional<Author> find(long authorId) {
//       List<Author> author= template.query("Select * from authors where id = ? Limit 1",
//               new AuthorRowMapper(),authorId);
//       return author.stream().findFirst();
//    }
//
//    @Override
//    public List<Author> findAll() {
//        return  template.query("Select * From authors",new AuthorRowMapper());
//    }
//
//    @Override
//    public void update(long id ,Author author) {
//        template.update("UPDATE authors set id=? ,name=?,age=?where id=?",
//                author.getId(),author.getName(),author.getAge(),id);
//    }
//
//
//
//    @Override
//    public void delete(long id) {
//        template.update("Delete From authors Where id =?",id);
//    }
//
//    public static class AuthorRowMapper implements RowMapper<Author>{
//
//         @Override
//         public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
//             return Author.builder()
//                     .id(rs.getLong("id"))
//                     .name(rs.getString("name"))
//                     .age(rs.getInt("age"))
//                     .build();
//         }
//     }
//}
