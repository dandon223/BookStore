package com.bookstore.demo;

public class BookListItem {
    private Long id;
    private String name;

    public BookListItem(){}

    public BookListItem(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
