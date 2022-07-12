package com.bookstore.book;

import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ConditionalOnProperty(name = "demo.book.repository.mode", havingValue = "MOCK", matchIfMissing = true)
@Component
public class BookDataBase implements BookRepository {
  private static Long idCounter = 1L;
  private static List<BookModel> books = new ArrayList<>();

  @Override
  public List<BookModel> getBooks() {
    return books;
  }

  @Override
  public Long addBook(BookModel book) {
    book.setId(idCounter++);
    books.add(book);
    return book.getId();
  }

  @Override
  public boolean deleteBook(Long id) {
    for (int i = 0; i < books.size(); i++) {
      if (Objects.equals(books.get(i).getId(), id)) {
        books.remove(i);
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean updateBook(Long id, BookModel book) {
    for (int i = 0; i < books.size(); i++) {
      if (Objects.equals(books.get(i).getId(), id)) {
        book.setId(id);
        books.set(i, book);
        return true;
      }
    }
    return false;
  }

  @Override
  public Optional<BookModel> getBook(Long id) {
    for (BookModel book : books) {
      if (Objects.equals(book.getId(), id)) {
        return Optional.of(book);
      }
    }
    return Optional.empty();
  }
}
