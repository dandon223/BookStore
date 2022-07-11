package com.bookstore.book;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @Test
  @Order(1)
  public void shouldReturnNewBookIdAfterAddingBook() throws Exception {
    Long result = bookService.addBook(new Book("Szumilas", "Jacek", 1999));
    assertThat(result).isEqualTo(1L);
  }

  @Test
  @Order(2)
  public void shouldReturnTrueAfterBookDelete() throws Exception {
    Boolean result = bookService.deleteBook(1L);
    assertThat(result).isEqualTo(true);
  }

  @Test
  @Order(3)
  public void shouldReturnListOfBooks() throws Exception {
    bookService.addBook(new Book("Narnia 1", "Author 1", 1999));
    bookService.addBook(new Book("Cyberiada", "Author 2", 2001));
    List<BookListItem> result = bookService.getBooks();
    assertThat(result.get(0).getName()).isEqualTo("Narnia 1");
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  @Order(4)
  public void shouldReturnTrueAfterBookUpdate() throws Exception {
    Boolean result = bookService.updateBook(2L, new Book("Name 1", "Author 1", 1999));
    assertThat(result).isEqualTo(true);
  }
}
