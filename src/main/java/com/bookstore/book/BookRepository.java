package com.bookstore.book;

import java.util.List;

public interface BookRepository {
    List<BookModel> getBooks();

    Long addBook(BookModel book);

    boolean deleteBook(Long id);

    boolean updateBook(Long id, BookModel book);
}
