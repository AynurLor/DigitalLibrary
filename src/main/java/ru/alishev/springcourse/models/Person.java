package ru.alishev.springcourse.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
public class Person {
    private int id;
    @NotEmpty(message = "fullName should not be empty")
    @Size(min = 8, max = 50, message = "fullName should be between 8 and 30 characters")
    private String fullName;
    @DateTimeFormat(pattern = "\\w{4}-\\w{2}-\\w{2}$")
    private String birthday;
//        birthdate = Date.valueOf("2020-10-12");
    private List<Book> books = new ArrayList<>();

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Person() {
//        books.get(1)
    }

    public Person(int id, String fullName, String birthday) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", books=" + books +
                '}';
    }

    //    @NotEmpty(message = "fullName should not be empty")
//    @Size(min = 2, max = 30, message = "fullName should be between 2 and 30 characters")
//    private String fullName;
//
//    @Min(value = 0, message = "Age should be greater than 0")
//    private int age;
//
//    @NotEmpty(message = "Email should not be empty")
//    @Email(message = "Email should be valid")
//    private String email;

}
