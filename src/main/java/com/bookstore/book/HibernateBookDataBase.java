package com.bookstore.book;

import java.util.List;
import java.util.Optional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@ConditionalOnProperty(name = "demo.book.repository.mode", havingValue = "PROD")
@Repository
public interface HibernateBookDataBase extends JpaRepository<BookModel, Long>, BookRepository {
  @Override
  default List<BookModel> getBooks() {
    return this.findAll();
  }

  @Override
  default Long addBook(BookModel book) {
    return this.save(book).getId();
  }

  @Override
  default boolean deleteBook(Long id) {
    boolean result = this.existsById(id);
    if (result) {
      this.deleteById(id);
    }
    return result;
  }

  @Override
  default boolean updateBook(Long id, BookModel book) {
    Optional<BookModel> bookModel = this.findById(id);

    bookModel.ifPresent((BookModel result) -> {
      result.setName(book.getName());
      result.setAuthor(book.getAuthor());
      result.setPublishYear(book.getPublishYear());
      this.save(result);
    });
    return bookModel.isPresent();
  }

  @Override
  default Optional<BookModel> getBook(Long id) {
    return this.findById(id);
  }
}
