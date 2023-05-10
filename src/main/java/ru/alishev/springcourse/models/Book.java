package ru.alishev.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    @NotEmpty(message = "Title should not be empty")
    private String title;
    @NotEmpty(message = "Author should not be empty")
    private String author;
    @NotEmpty(message = "Year should not be empty")
    @DateTimeFormat(pattern = "\\w{4}-\\w{2}-\\w{2}$")
    private String year;
    private Integer person_id;

    public Book() {
    }

    public Book(Integer id, String title, String author, String year, Integer person_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.person_id = person_id;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public String getYear() {
        return year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public Integer getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }

}
