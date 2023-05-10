package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        System.out.println("index");
//        System.out.println(jdbcTemplate.queryForList("select * from Book;",
//                List.class));
        return jdbcTemplate.query("select id, title, author, year, coalesce(person_id, 0) from Book;",
                new BeanPropertyRowMapper<>(Book.class));
    }

public int getId(Book book) {
    int id = jdbcTemplate.queryForObject("select id from Book where title=?;",
            Integer.class, book.getTitle());
    return id;
}
    //    //
    public Book show(int id) {
        Book book = jdbcTemplate.query("select id, title, author, year, coalesce(person_id, 0) from Book where id=?;",
                        new Object[]{id},  new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
        book.setId(getId(book));
        return book;
    }
    public void save(Book book) {
        jdbcTemplate.update("insert into Book(title, author, year) values (?, ?, ?);",
                book.getTitle(),
                book.getAuthor(),
                Date.valueOf(book.getYear()));
    }
    public void update(int id, Book updateBook) {
        jdbcTemplate.update("update Book set title=?, author=?, year=? where id=?;",
                updateBook.getTitle(), updateBook.getAuthor(), Date.valueOf(updateBook.getYear()), id);
    }
    public void delete(int id) {
        jdbcTemplate.update("delete from Book where id=?;", id);
    }

    public void assign(int id, int selectedPerson) {
//        System.out.println(id +"| " + selectedPerson.getId());
        jdbcTemplate.update("update Book set person_id=? where id=?", selectedPerson, id);
    }

//    public Optional
    public void release(int id) {
        jdbcTemplate.update("update Book set person_id=null where id=?;", id);
    }
}
