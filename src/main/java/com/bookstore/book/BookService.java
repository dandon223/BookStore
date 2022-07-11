package com.bookstore.book;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class BookService {

  private static final Logger logger = LogManager.getLogger(BookRestController.class);
  private final BookRepository bookDataBase;

  @Autowired
  BookService(BookRepository bookDataBase) {
    this.bookDataBase = bookDataBase;
  }

  public List<BookListItem> getBooks() {
    logger.info("Getting Books");
    List<BookModel> books = bookDataBase.getBooks();
    List<BookListItem> result = new ArrayList<>();
    for (BookModel book : books) {
      result.add(new BookListItem(book.getId(), book.getName()));
    }
    return result;
  }

  public Long addBook(Book book) {
    logger.info("Adding " + book.toString());
    return bookDataBase.addBook(
        new BookModel(book.getName(), book.getAuthor(), book.getPublishYear()));
  }

  public boolean deleteBook(Long id) {
    logger.info("Deleting book with id = " + id + ".");
    return bookDataBase.deleteBook(id);
  }

  public boolean updateBook(Long id, Book book) {
    logger.info("Updating " + book.toString() + " with id = " + id + ".");
    return bookDataBase.updateBook(id,
        new BookModel(book.getName(), book.getAuthor(), book.getPublishYear()));
  }
}
