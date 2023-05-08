package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.sql.Date;
import java.util.List;

@Component
public class BookDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from Book",
                new BeanPropertyRowMapper<>(Book.class));
    }
//    title varchar not null,
//    author varchar not null,
//    year
public int getId(Book book) {
    int id = jdbcTemplate.queryForObject("select id from Book where title=? and author=? and year=?",
            Integer.class, book.getTitle(), book.getAuthor(), Date.valueOf(book.getYear()));
    return id;
}
    //    //
    public Book show(int id) {
        Book book = jdbcTemplate.query("select * from Book where id=?",
                        new Object[]{id},  new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
        book.setId(getId(book));
        return book;
    }
    //
//    public List<Book> getBooks(int id) {
//        return jdbcTemplate.query("select * from Book " +
//                "left join Book P on P.id = Book.person_id " +
//                "where person_id = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Book.class));
//    }
    public void save(Book book) {
        jdbcTemplate.update("insert into Book(title, author, year) values (?, ?, ?);",
                book.getTitle(),
                book.getAuthor(),
                book.getYear());
    }
    public void update(int id, Book updateBook) {
        System.out.println(updateBook);
        jdbcTemplate.update("update Book set title=?, author=?, year=? where id=?;",
                updateBook.getTitle(), updateBook.getAuthor(), Date.valueOf(updateBook.getYear()), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("delete from Book where id=?", id);
    }

}
