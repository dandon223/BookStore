package com.bookstore.book;

import io.swagger.models.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
  public List<BookListItem> getBooks() {
    return bookService.getBooks();
  }

  @PostMapping
  public Long addBook(@Valid @RequestBody BookRequest book) {
    return bookService.addBook(new Book(book.getName(), book.getAuthor(), book.getPublishYear()));
  }

  @DeleteMapping("/{id}")
  public boolean deleteBook(@PathVariable Long id) {
    return bookService.deleteBook(id);
  }

  @PutMapping("/{id}")
  public boolean updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest book) {
    return bookService.updateBook(id,
        new Book(book.getName(), book.getAuthor(), book.getPublishYear()));
  }
  @GetMapping("/{id}")
  public BookListItem getBook(@PathVariable Long id) {
    return bookService.getBook(id).orElseThrow(() -> new BookNotFoundException(id));
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
