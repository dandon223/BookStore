package com.bookstore.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("books")
class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookListItem> getBooks(){
        return bookService.getBooks();
    }

    @PostMapping
    public Long addBook(@Valid @RequestBody BookRequest book) {
        return bookService.addBook(new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable Long id){
        return bookService.deleteBook(id);
    }
    @PutMapping("/{id}")
    public boolean updateBook(@PathVariable Long id,@Valid @RequestBody BookRequest book){
        return bookService.updateBook(id,new Book(book.getName(),book.getAuthor(),book.getPublishYear()));
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
