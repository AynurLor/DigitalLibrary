package ru.alishev.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Book {
    private int id;
    private String title;
    private String author;
    @DateTimeFormat
    private Date year;
    private int person_id;

    public void setYear(Date year) {
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year.toString();
    }

    public void setYear(String year) {
        this.year = Date.valueOf(year);
    }

    public Book() {
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public Book(int id, String title, String author, String year, int person_id) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = Date.valueOf(year);
        this.person_id = person_id;
    }
}
