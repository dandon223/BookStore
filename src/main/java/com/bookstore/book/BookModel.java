package com.bookstore.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookModel {
    private Long id;
    private String name;
    private String author;
    private int publishYear;

    public BookModel(Long id, String name, String author, int publishYear) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
    }

    public BookModel(String name, String author, int publishYear) {
        this.name = name;
        this.author = author;
        this.publishYear = publishYear;
    }
}
