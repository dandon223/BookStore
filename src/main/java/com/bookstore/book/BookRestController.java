package com.bookstore.book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("books")
class BookRestController {

  private final BookService bookService;

  public BookRestController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public List<BookListItem> getBooks() {
    return bookService.getBooks();
  }

  @PostMapping
  public Long addBook(@Valid @RequestBody BookRequest book) {
    return bookService.addBook(BookMapper.INSTANCE.mapToBook(book));
  }

  @DeleteMapping("/{id}")
  public boolean deleteBook(@PathVariable Long id) {
    if(bookService.deleteBook(id))
      return true;
    else
      throw new BookNotFoundException(id);

  }

  @PutMapping("/{id}")
  public boolean updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest) {
    if(bookService.updateBook(id, BookMapper.INSTANCE.mapToBook(bookRequest)))
      return true;
    else
      throw new BookNotFoundException(id);
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
  @ResponseBody
  @ExceptionHandler(BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String bookNotFoundHandler(BookNotFoundException ex) {
    return ex.getMessage();
  }
}
