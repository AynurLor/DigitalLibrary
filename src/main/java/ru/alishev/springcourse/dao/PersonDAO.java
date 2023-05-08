package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Book;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {
    private static JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from Person",
                new BeanPropertyRowMapper<>(Person.class));
    }
    public int getId(Person people) {
        return jdbcTemplate.query("select id from Person where fullName=?",
                new Object[]{people.getFullName()},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null).getId();
    }
    public Person show(int id) {
        Person people = jdbcTemplate.query("select * from Person where id=?",
                        new Object[]{id},  new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
//        System.out.println(people);
        people.setId(getId(people));
        people.setBooks(getBooks(id));
        return people;
    }

    public List<Book> getBooks(int id) {
        return jdbcTemplate.query("select * from Book " +
                "left join Person P on P.id = Book.person_id " +
                "where person_id = ?", new Object[]{id} ,new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Person people) {
        System.out.println(people);
        jdbcTemplate.update("insert into Person(fullName, birthday) values (?, ?);",
                people.getFullName(),
                Date.valueOf(people.getBirthday()));
    }
    public void update(int id, Person updatePerson) {
        jdbcTemplate.update("update Person set fullName=?,birthday=? where id=?",
                updatePerson.getFullName(),
                Date.valueOf(updatePerson.getBirthday()),
                id);
    }
    public void delete(int id) {
        jdbcTemplate.update("delete from Person where id=?", id);
    }
}
