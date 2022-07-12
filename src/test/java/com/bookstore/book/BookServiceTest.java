package com.bookstore.book;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookServiceTest {

  @Autowired
  private BookService bookService;

  @Autowired
  private BookRepository bookRepository;

  @BeforeEach
  void deleteBooks() {
    List<BookModel> books = bookRepository.getBooks();
    for (BookModel bookModel : books) {
      bookRepository.deleteBook(bookModel.getId());
    }
  }

  @Test
  void shouldReturnNewBookIdAfterAddingBook() {
    Long result = bookService.addBook(new Book("Szumilas", "Jacek", 1999));
    assertThat(result).isGreaterThan(0L);
  }

  @Test
  void shouldReturnDifferentIds() {
    Long firstId = bookService.addBook(new Book("Szumilas", "Jacek", 1999));
    Long secondId = bookService.addBook(new Book("Szumi", "Marek", 2000));
    assertThat(firstId).isNotEqualTo(secondId);
  }

  @Test
  void shouldAddBookToRepository() {
    Long id = bookService.addBook(new Book("Szumilas", "Jacek", 1999));
    Optional<BookModel> bookModelOptional = bookRepository.getBook(id);
    assertThat(bookModelOptional.isPresent()).isEqualTo(true);
  }

  @Test
  void shouldReturnTrueAfterBookDelete() {
    // given
    Long bookId = bookService.addBook(new Book("Szumilas", "Jacek", 1999));
    // when
    Boolean result = bookService.deleteBook(bookId);
    // then
    assertThat(result).isEqualTo(true);
  }

  @Test
  void shouldReturnListOfBooks() {
    bookService.addBook(new Book("Narnia 1", "Author 1", 1999));
    bookService.addBook(new Book("Cyberiada", "Author 2", 2001));
    List<BookListItem> result = bookService.getBooks();
    assertThat(result.get(0).getName()).isEqualTo("Narnia 1");
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  void shouldReturnTrueAfterBookUpdate() {
    Long id = bookService.addBook(new Book("Narnia 1", "Author 1", 1999));
    Boolean result = bookService.updateBook(id, new Book("Name 1", "Author 1", 1999));
    assertThat(result).isEqualTo(true);
  }

  @Test
  void shouldUpdateBook() {
    Long id = bookService.addBook(new Book("Narnia 1", "Author 1", 1999));
    bookService.updateBook(id, new Book("Name 1", "Author 1", 1999));
    Optional<BookModel> bookModelOptional = bookRepository.getBook(id);
    assertThat(bookModelOptional.isPresent()).isEqualTo(true);
    bookModelOptional.ifPresent(bookModel -> assertThat(bookModel.getName()).isEqualTo("Name 1"));
  }

  @Test
  void shouldGetBook() {
    Long id = bookService.addBook(new Book("Narnia 1", "Author 1", 1999));
    Optional<BookListItem> bookModelOptional = bookService.getBook(id);
    assertThat(bookModelOptional.isPresent()).isEqualTo(true);
  }
}
