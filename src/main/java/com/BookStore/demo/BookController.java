package com.BookStore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    private final BookDataBase bookDataBase;

    @Autowired
    public BookController(BookDataBase bookDataBase) {
        this.bookDataBase = bookDataBase;
    }

    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookDataBase.getBooks();
    }

    @PostMapping("/add")
    public Long addBook(@RequestBody Book book){
        return bookDataBase.addBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteBook(@PathVariable Long id){
        return bookDataBase.deleteBook(id);
    }
    @PutMapping("/update/{id}")
    public boolean updateBook(@PathVariable Long id,@RequestBody Book book){
        return bookDataBase.updateBook(id,book);
    }
}
