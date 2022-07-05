package com.bookstore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("books")
public class BookController {

    private final BookDataBase bookDataBase;

    @Autowired
    public BookController(BookDataBase bookDataBase) {
        this.bookDataBase = bookDataBase;
    }

    @GetMapping
    public List<BookListItem> getBooks(){
        List<Book> books = bookDataBase.getBooks();
        List<BookListItem> result = new ArrayList<>();
        for( Book book : books){
            result.add(new BookListItem(book.getId(),book.getName()));
        }
        return result;
    }

    @PostMapping
    public Long addBook(@RequestBody BookRequest book){
        return bookDataBase.addBook(new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id){
        return bookDataBase.deleteBook(id);
    }
    @PutMapping("/{id}")
    public boolean updateBook(@PathVariable Long id,@RequestBody BookRequest book){
        return bookDataBase.updateBook(id,new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }
}
