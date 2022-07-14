package com.bookstore.book;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
class BookService {

  private static final Logger logger = LogManager.getLogger(BookRestController.class);
  private final BookRepository bookRepository;

  @Autowired
  BookService(BookRepository bookDataBase) {
    this.bookRepository = bookDataBase;
  }

  public List<BookListItem> getBooks() {
    logger.info("Getting Books");
    List<BookModel> books = bookRepository.getBooks();
    return books.stream().map(BookMapper.INSTANCE::mapToBookListItem).collect(Collectors.toList());
  }

  public Long addBook(Book book) {
    logger.info("Adding " + book.toString());
    return bookRepository.addBook(
        BookMapper.INSTANCE.mapToBookModel(book));
  }

  public boolean deleteBook(Long id) {
    logger.info("Deleting book with id = " + id + ".");
    if(!bookRepository.deleteBook(id)){
      throw new BookNotFoundException(id);
    }
    return true;
  }

  public boolean updateBook(Long id, Book book) {
    logger.info("Updating " + book.toString() + " with id = " + id + ".");
    if(!bookRepository.updateBook(id,BookMapper.INSTANCE.mapToBookModel(book))){
      throw new BookNotFoundException(id);
    }
    return true;
  }

  public Optional<BookListItem> getBook(Long id) {
    logger.info("Getting book with id = " + id + ".");
    Optional<BookModel> bookModelOptional = bookRepository.getBook(id);
    return bookModelOptional.map(BookMapper.INSTANCE::mapToBookListItem);
  }
}
