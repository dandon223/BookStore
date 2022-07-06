package com.bookstore.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
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
    public Long addBook(@Valid @RequestBody BookRequest book) {
        return bookDataBase.addBook(new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id){
        return bookDataBase.deleteBook(id);
    }
    @PutMapping("/{id}")
    public boolean updateBook(@PathVariable Long id,@Valid @RequestBody BookRequest book){
        return bookDataBase.updateBook(id,new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }
}
