package com.BookStore.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDataBase {
    List<Book> books;
    public BookDataBase(){
        books = initBooks();
    }

    public List<Book> getBooks() {
        return books;
    }
    public void addBook(Book book){
        books.add(book);
    }
    private List<Book> initBooks(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Narnia 1","C.S Lewis",1953));
        books.add(new Book("Cyberiada","Stanisław Lem",1965));
        return books;
    }
}
