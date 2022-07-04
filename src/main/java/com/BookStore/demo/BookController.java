package com.BookStore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private BookDataBase bookDataBase;

    @Autowired
    public BookController(BookDataBase bookDataBase) {
        this.bookDataBase = bookDataBase;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookDataBase.getBooks();
    }

    @PostMapping("/add")
    public void addBook(@RequestBody Book book){
        bookDataBase.addBook(book);
    }
}
