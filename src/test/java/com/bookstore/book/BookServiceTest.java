package com.bookstore.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private JpaBookDataBase jpaBookDataBase;

  @BeforeEach
  void deleteBooks() {
    jpaBookDataBase.deleteAllInBatch();
  }

  @Test
  void shouldReturnNewBookIdAfterAddingBook() {
    Long result = bookService.addBook(newBook("Szumilas", "Jacek", 1999));
    assertThat(result).isGreaterThan(0L);
  }



  @Test
  void shouldReturnDifferentIds() {
    Long firstId = bookService.addBook(newBook("Szumilas", "Jacek", 1999));
    Long secondId = bookService.addBook(newBook("Szumi", "Marek", 2000));
    assertThat(firstId).isNotEqualTo(secondId);
  }

  @Test
  void shouldAddBookToRepository() {
    Long id = bookService.addBook(newBook("Szumilas", "Jacek", 1999));
    Optional<BookModel> bookModelOptional = jpaBookDataBase.getBook(id);
    assertThat(bookModelOptional.isPresent()).isEqualTo(true);
  }

  @Test
  void shouldReturnTrueAfterBookDelete() {
    // given
    Long bookId = bookService.addBook(newBook("Szumilas", "Jacek", 1999));
    // when
    Boolean result = bookService.deleteBook(bookId);
    // then
    assertThat(result).isEqualTo(true);
  }

  @Test
  void shouldReturnListOfBooks() {
    bookService.addBook(newBook("Narnia 1", "Author 1", 1999));
    bookService.addBook(newBook("Cyberiada", "Author 2", 2001));
    List<BookListItem> result = bookService.getBooks();
    assertThat(result.get(0).getName()).isEqualTo("Narnia 1");
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  void shouldReturnTrueAfterBookUpdate() {
    Long id = bookService.addBook(newBook("Narnia 1", "Author 1", 1999));
    Boolean result = bookService.updateBook(id, newBook("Name 1", "Author 1", 1999));
    assertThat(result).isEqualTo(true);
  }

  @Test
  void shouldUpdateBook() {
    Long id = bookService.addBook(newBook("Narnia 1", "Author 1", 1999));
    bookService.updateBook(id, newBook("Name 1", "Author 1", 1999));
    Optional<BookModel> bookModelOptional = jpaBookDataBase.getBook(id);
    assertThat(bookModelOptional.isPresent()).isTrue();
    bookModelOptional.ifPresent(bookModel -> assertThat(bookModel.getName()).isEqualTo("Name 1"));
  }

  @Test
  void shouldGetBook() {
    Long id = bookService.addBook(newBook("Narnia 1", "Author 1", 1999));
    Optional<BookListItem> bookModelOptional = bookService.getBook(id);
    assertThat(bookModelOptional.isPresent()).isEqualTo(true);
  }
  private Book newBook(String name, String author, int year) {
    return Book.builder().name(name).author(author).publishYear(year).build();
  }
}
