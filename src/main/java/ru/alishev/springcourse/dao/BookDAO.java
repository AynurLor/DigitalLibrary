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
        return jdbcTemplate.query("select id, title, author, year, coalesce(person_id, 0) from Book;",
                new BeanPropertyRowMapper<>(Book.class));
    }

public int getId(Book book) {
    return jdbcTemplate.queryForObject("select id from Book where title=?;",
            Integer.class, book.getTitle());
}
    public Book show(int id) {
        Book book = jdbcTemplate.query("select id, title, author, year, coalesce(person_id, 0) from Book where id=?;",
                        new Object[]{id},  new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
        assert book != null;
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
        jdbcTemplate.update("update Book set person_id=? where id=?", selectedPerson, id);
    }

    public void release(int id) {
        jdbcTemplate.update("update Book set person_id=null where id=?;", id);
    }

    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT Person.* FROM Book JOIN Person ON Book.person_id = Person.id " +
                "WHERE Book.id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }
}
