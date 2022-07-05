package com.bookstore.demo;

public class Book {
    private Long id;
    private String name;
    private String author;
    private int publishYear;

    public Book(){}
    public Book(String name, String author, int publishYear) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
    }

    public Book(Long id, String name, String author, int publishYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
}
