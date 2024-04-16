//package com.example.springbootwithdevtiro.dao.impl;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Optional;
//
//import com.example.springbootwithdevtiro.dao.BookDao;
//import com.example.springbootwithdevtiro.persistence.Repositories.entity.Books;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BookDaoImpl implements BookDao {
//
//    private final JdbcTemplate template;
//
//    public BookDaoImpl(final JdbcTemplate template) {
//        this.template = template;
//    }
//
//    public void create(Books book) {
//        template.update(
//                "insert into books(isbn,tittle,authorId) values (?,?,?)",
//                book.getIsbn(),book.getTittle(),book.getAuthorId()
//
//        );
//    }
//
//    @Override
//    public Optional<Books> find(String bookId) {
//        return template.query("select * from books where isbn=?",new BookRowMapper(),bookId).stream().findFirst();
//    }
//
//    @Override
//    public List<Books> findAll() {
//        return  template.query("Select * From books",new BookRowMapper());
//    }
//
//    @Override
//    public void update(String isbn, Books book) {
//
//        template.update("UPDATE books set isbn=?,tittle=?,authorId=? where isbn=?",
//                        book.getIsbn(),book.getTittle(),book.getAuthorId(),isbn
//
//                );
//
//    }
//
//    @Override
//    public void delete(String isbn) {
//        template.update("DELETE FROM books WHERE isbn=?",isbn);
//    }
//
//    public static class BookRowMapper implements RowMapper<Books>{
//
//        @Override
//        public Books mapRow(ResultSet rs, int rowNum) throws SQLException {
//            return Books.builder()
//                    .isbn(rs.getString("isbn")).authorId(rs.getLong("authorId")).tittle(rs.getString("tittle"))
//
//                        .build();
//        }
//    }
//}
